package iring29;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import controller.ConnectionPool;
import iring29.bean.R_OrderBean;
import iring29.bean.RestaurantBean;
import pojo.AccountDO;
import pojo.OrderTableBean;
import rambo0021.AccountBean;

public class Test_iring29 {
	public static void main(String[] args) throws IOException, SQLException {

		//TEST1
//		HomepageInit hpInit = new HomepageInit();
//		AccountDO accDo = new AccountDO();
//		hpInit.searchUsername(accDo);
//		System.out.println(hpInit.password);
//		String param1 = hpInit.password;
//		System.out.println(param1);

		//TEST2
//		HomepageDAO homepageDAO = new HomepageDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
		//記得要改LOADING_WITHOUT_SERVER
//		 System.out.println(homepageDAO.listAccDO().get(0));
//homepageDAO.listAccInit();
//System.out.println(homepageDAO.findUser("test"));
		
		//TEST3 findRestaurant
//		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		RestaurantBean findRes= restaurantDAO.findRestaurant("宮原眼科醉月樓");
//		System.out.println(findRes);
		
		//TEST4 findRegion
//		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		List<RestaurantBean> findRegion = restaurantDAO.findRegion("台中");
//		System.out.println(findRegion.get(0));
//		for(RestaurantBean restaurantBean :findRegion) {
//			System.out.println(restaurantBean.getName());
//			System.out.println(restaurantBean.getType());
//			System.out.println(restaurantBean.getRegion());
//		}
		

		//TEST5 update picture
//		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		restaurantDAO.updateimg("宮原眼科醉月樓");
		
		//TEST6 DATE
//		Date date = new Date();
//		System.out.println(date);
//		//格式化日期
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.format(date));
		
		//TEST7 R_Order_ListDAO
//		Order_DateBean order_DateBean = new Order_DateBean("Irene","Chung","123456");
//		
//		Order_TableBean orderTableBean = new Order_TableBean();
//		orderTableBean.setPrice(BigDecimal.valueOf(500));
//		Timestamp date = Timestamp.valueOf(LocalDateTime.now());
//		orderTableBean.setOrder_date(date);
//		
//		new R_OderBean(new BigDecimal(123),new BigDecimal(3),date,new BigDecimal(550));
//		R_Order_ListDAO r_Order_ListDAO = new R_Order_ListDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		r_Order_ListDAO.createOrder(order_DateBean);
		
		
		//TEST  下訂單 (1. 先建立下訂者資料 2.建立大訂單 3.建立(C,H,R)小訂單 ))
		OrderTableBean bean = new OrderTableBean();
		R_OrderBean rBean = new R_OrderBean();
		AccountBean user = new AccountBean();
		user.setUserName("Irene"); //測試時要先在Account Table create username = Irene 的資料
		bean.setUser(user);
		RestaurantBean restaurantBean = new RestaurantBean();
		restaurantBean.setR_sn(BigDecimal.valueOf(126)); //valueOf(126) 為餐廳的流水號
		rBean.setRestaurantBean(restaurantBean);
		rBean.setBooking_date(Timestamp.valueOf(LocalDate.of(2020, 10, 14).atStartOfDay())); //測試先手動key欲前往餐廳的日期
		rBean.setCustomerNum(BigDecimal.valueOf(5));
		
		bean.addR_OderBean(rBean);
		bean.setCustomerName("abc");  //測試先手動key 下單者姓名
		bean.setCustomerPhone("09123456789");  //測試先手動key 下單者電話
		
		R_Order_ListDAO r_Order_ListDAO = new R_Order_ListDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
		r_Order_ListDAO.createOrder(bean);
		
		//TEST Timestamp 
//		Timestamp ts = new Timestamp(System.currentTimeMillis());  
//		String tsStr = "2011-05-09 11:49:45";  
//		try {  
//		ts = Timestamp.valueOf(tsStr);  
//		System.out.println(ts);  
//		} catch (Exception e) {  
//		e.printStackTrace();  
//		} 
		
//		R_Order_ListDAO r_Order_ListDAO = new R_Order_ListDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		BigDecimal findRid = r_Order_ListDAO.findRid("上官木桶鍋 - 萬華旗艦店");
//		System.out.println(findRid);
		

		//TEST
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
		OrderTableBean findR_Order = restaurantDAO.findR_Order(new BigDecimal(70));
		
		Set<R_OrderBean> r_OderBeans = findR_Order.getR_OderBeans();
		for (R_OrderBean roBean : r_OderBeans) {
			BigDecimal order_id = roBean.getOrder_id();
			System.out.println(order_id);
		}
		
	}
}
