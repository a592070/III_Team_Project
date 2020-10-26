package iring29.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pojo.OrderTableBean;
import rambo0021.model.AccountBean;
import utils.HibernateUtil;

public class Test {

	public static void main(String[] args) {
//		SessionFactory factory = HibernateUtil.getSessionFactory();
//
//		Session session = factory.getCurrentSession();
//
//		session.beginTransaction();
//
//		RestaurantDAO rDAO = new RestaurantDAO(session);
		
//		int count = rDAO.numRestaurant("牛");
//		System.out.println(count);

//		List<RestaurantBean> rBeans = rDAO.findMulti_R("牛");
//		for(RestaurantBean r : rBeans) {
//			System.out.println(r.getName());
//		}

//		RestaurantBean rBean = rDAO.findRestaurant("山鯨燒肉");
//		System.out.println(rBean.getAddress());

//		List<RestaurantBean> rBeans = rDAO.findMulti_Name_Region("牛", "台南");
//		for (RestaurantBean r : rBeans) {
//			System.out.println(r.getName());
//		}

//		List<RestaurantBean> rBeans = rDAO.findRegion("台中");
//		for(RestaurantBean r : rBeans) {
//			System.out.println(r.getName());
//			System.out.println(r.getRegion());
//			System.out.println(r.getType());
//		}
		
//		RestaurantBean rBean = new RestaurantBean();
//		rBean.setName("Test");
//		rBean.setAddress("Test Address");
//		rDAO.createRestauran(rBean); //java.lang.IllegalArgumentException: id to load is required for loading

//		RestaurantBean rBean = rDAO.Restaurant_HP("iii");
//		System.out.println(rBean.getName());
		
//		R_Order_ListDAO rOrderDAO = new R_Order_ListDAO(session);
		
//		R_OrderBean rOrderBean = rOrderDAO.UserOrderList(BigDecimal.valueOf(3));
//		System.out.println(rOrderBean.getCustomerName());
//		
//		R_OrderBean test = rOrderDAO.findR_order_List(BigDecimal.valueOf(93)); //java.lang.IllegalArgumentException: org.hibernate.QueryException: 
//		System.out.println(test.getCustomerName());
		
//		OrderTableBean rTBean = rOrderDAO.findR_Order(BigDecimal.valueOf(93));
//		Set<R_OrderBean> rBeans = rTBean.getR_OrderBeans();
//		for(R_OrderBean rBean : rBeans) {
//			System.out.println(rBean.getCustomerNum());
//		}
		
//		OrderTableBean otBean = new OrderTableBean();
//		HashSet<R_OrderBean> roBeans = new HashSet<R_OrderBean>();
//		R_OrderBean roBean = new R_OrderBean();
//		AccountBean aBean = new AccountBean();
//		aBean.setUserName("iii");
//		roBean.setCustomerName("hi2");
//		roBean.setCustomerNum(BigDecimal.valueOf(3));
//		roBean.setCustomerPhone("0919033123");
//		roBean.setOrderTableBean(otBean);
//		otBean.setUser(aBean);
////		otBean.setOrder_id(otBean.getOrder_id());
////		otBean.addR_OrderBean(roBean);
//		roBeans.add(roBean);
//		otBean.setR_OrderBeans(roBeans);
//		rOrderDAO.createOrder(otBean);
		
//		ModifyDAO mDAO = new ModifyDAO(session);
//		boolean rAdd = mDAO.R_Address("台中市", null, BigDecimal.valueOf(71));
//		System.out.println(rAdd);
		
		
//		session.getTransaction().commit();
//		HibernateUtil.closeSessionFactory();

	}

}
