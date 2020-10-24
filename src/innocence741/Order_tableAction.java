package innocence741;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import rambo0021.model.AccountBean;
import innocence741.model.CarType;
import innocence741.model.HighSpeedRail;
import pojo.OrderTableBean;
import innocence741.model.T_Order_List;
import innocence741.model.T_Order_ListDAO;
import utils.HibernateUtil;

public class Order_tableAction {
	private static Session session;

	
	
	public static void main(String[] args) throws SQLException {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();		
		session = factory.getCurrentSession();
		session.beginTransaction();
//------------------------------------------------------------------------
//		Order_table order_table = new Order_table();
//		Set<T_Order_List> t_Order_Lists = new HashSet<T_Order_List>();
//		T_Order_List t_Order_List;
//		
//		t_Order_List = new T_Order_List();
//		t_Order_List.setCustomerName("kmqwk");
//		t_Order_List.setCustomerPhone("090");
//		CarType carType = session.get(CarType.class, 121);
//		t_Order_List.setCarType(carType);
//		t_Order_Lists.add(t_Order_List);
//
//		
//		t_Order_List = new T_Order_List();
//		t_Order_List.setCustomerName("ssewss");
//		t_Order_List.setCustomerPhone("0800");
//		carType = session.get(CarType.class, 122);
//		t_Order_List.setCarType(carType);
//		t_Order_Lists.add(t_Order_List);
//		
//		order_table.setT_Order_Lists(t_Order_Lists);
//		session.save(order_table);

		
//------------------------------------------------------------------------

		/*HighSpeedRail T_OrderServlet*/
		Set<T_Order_List> t_Order_Lists = new HashSet<T_Order_List>();
		OrderTableBean order_table = new OrderTableBean();
		T_Order_List t_Order_List =new T_Order_List();
		
		Timestamp ts = new Timestamp(System.currentTimeMillis()); //下訂單時間
		
		AccountBean user = session.get(AccountBean.class, "abab");
//		AccountBean user = new AccountBean();
//		Query query =session.createQuery("from AccountBean where userName = 'abab'");
//		AccountBean user = (AccountBean)query.uniqueResult();
//		user.setUserName("ipip");

		HighSpeedRail highSpeedRail = session.get(HighSpeedRail.class, BigDecimal.valueOf(120)); //之後要換Integer.parseInt(snSchedule)
		order_table.setUser(user);	//假裝user是ipip
		order_table.setOrder_date(ts);
		t_Order_List.setHighSpeedRail(highSpeedRail);
		t_Order_List.setTicketPrice(BigDecimal.valueOf(1500));	//之後要換BigDecimal.valueOf(trafficPrice)
		t_Order_List.setNums_days(BigDecimal.valueOf(Integer.parseInt("5")));	//之後要換BigDecimal.valueOf(Integer.parseInt(nums_days))
		t_Order_List.setStartPoint("天堂");	//之後要換startPoint
		t_Order_List.setDestination("哈哈");	//之後要換destination
		t_Order_List.setDeparatureDate(Timestamp.valueOf(LocalDate.of(2020, 11, 19).atStartOfDay()));	//之後要換Timestamp.valueOf(LocalDate.of(departureDate[0], departureDate[1], departureDate[2]).atStartOfDay())
		t_Order_List.setOrderType("1");	//之後要換orderType
		t_Order_List.setOrder_table(order_table);
		t_Order_List.setCustomerName("7788");	//之後要換customerName
		t_Order_List.setCustomerPhone("8871");	//之後要換customerPhone
		
		t_Order_Lists.add(t_Order_List);	//把t_Order_list裝進Set中
		order_table.setT_Order_Lists(t_Order_Lists);	//one to many
		
		T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(session);
		boolean flag = t_Order_ListDAO.createOrderTable(order_table);
		System.out.println(flag);
		if(flag == false) {
			session.getTransaction().rollback();
			throw new SQLException("就說你錯了吧");
		}
		
//------------------------------------------------------------------------

		
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
		
	}

}
