package iring29.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;

import controller.ConnectionPool;
import pojo.OrderTableBean;

public class R_Order_ListDAO {
	private Session session;

	// constructor
	public R_Order_ListDAO(Session session) {
		this.session = session;
	}
	//not yet try
	// create order
	public void createOrder(OrderTableBean otbean) {
		OrderTableBean result1 = session.get(OrderTableBean.class, otbean.getOrder_id());
		if(result1 == null) {
			session.save(otbean);
//			session.get(R_OrderBean.class, otbean.getR_OrderBeans());
		}
		
	}
	// fail
	// find r_order
	public R_OrderBean findR_order_List(BigDecimal r_sn)  {
		//select * from r_order_list where order_id = (select max(order_id) from r_order_list where r_sn = (select r_sn from restaurant where r_sn = 93));
		Query<R_OrderBean> query = session.createQuery("from R_OrderBean where order_id = (select max(order_id) from R_OrderBean where r_sn = (select r_sn from RestaurantBean where r_sn =?0))", R_OrderBean.class);
		query.setParameter(0, r_sn);
		R_OrderBean rBean = query.uniqueResult();
		return rBean;
		
	}
	//not yet try
	// Delete Order
	public boolean cancelR_Order(BigDecimal r_sn_order) {
		R_OrderBean result = session.get(R_OrderBean.class, r_sn_order);
		if(result != null) {
			session.delete(result);
			return true;
		}
		return false;

	}

	// set OrderTableBean for display HP
	public OrderTableBean findR_Order(BigDecimal r_sn) {
		Query<R_OrderBean> query = session.createQuery("from R_OrderBean where R_SN = ?0 order by 1", R_OrderBean.class);
		query.setParameter(0, r_sn);
		List<R_OrderBean> rOrderBeans = query.list();
		Set<R_OrderBean> set = new HashSet<>(rOrderBeans);
		OrderTableBean orderTableBean = new OrderTableBean();
		orderTableBean.setR_OrderBeans(set);
		return orderTableBean;
		
	}

	// user find order info
	public R_OrderBean UserOrderList(BigDecimal r_sn_order) {
		Query<R_OrderBean> query1 = session.createQuery("from R_OrderBean where r_sn_order = ?0", R_OrderBean.class);
		query1.setParameter(0, r_sn_order);
		R_OrderBean rOBean = query1.uniqueResult();
		System.out.println(rOBean.getCustomerName());
		System.out.println(rOBean.getRestaurantBean().getR_sn());
		Query<RestaurantBean> query2 = session.createQuery("from RestaurantBean where R_SN = ?0", RestaurantBean.class);
		query2.setParameter(0, rOBean.getRestaurantBean().getR_sn());
		RestaurantBean rBean = query2.uniqueResult();
		rOBean.setRestaurantBean(rBean);
		return rOBean;
		

	}

	// find booking name, phone
//	public OrderTableBean BookData(BigDecimal order_id) {
//		String sql = "select name, phone from order_data where sn = (select sn_no from order_table where order_id = ?)";
//
//	}
}
