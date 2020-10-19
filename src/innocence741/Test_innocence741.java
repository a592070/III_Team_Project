package innocence741;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.ConnectionPool;
import iring29.R_Order_ListDAO;
import iring29.bean.RestaurantBean;
import pojo.AccountDO;
import pojo.OrderTableBean;
import rambo0021.model.AccountBean;

public class Test_innocence741 {
	
	
	public static void main(String[] args) throws IOException, SQLException {
		
		
//		/*orderTest*/
//		OrderTableBean bean = new OrderTableBean();
//		T_OrderBean tBean = new T_OrderBean();
//		AccountBean user = new AccountBean();
//		hsrDO hsrDO = new hsrDO();
//		hsrDO.setSnSchedule(101); //假設訂購sn101的車票
//		tBean.setHsrDO(hsrDO);
//		tBean.setTrafficPrice(BigDecimal.valueOf(1000));
//		tBean.setNums_days(BigDecimal.valueOf(1));
//		tBean.setStartPoint("banqiao");
//		tBean.setDestination("nangang");
//		tBean.setDeparatureDate(Timestamp.valueOf(LocalDate.of(2020, 10, 14).atStartOfDay()));
//		tBean.setOrderType("0");
//		tBean.setCustomerName("asaseq");
//		tBean.setCustomerPhone("0808004564");
//		//System.out.println(user.getEmail()==null);
//		//System.out.println(user.getUserName());
//		
//		user.setUserName("abab");	//假裝訂購人為innocence
//		bean.setUser(user);	//假裝訂購人為innocence
//		bean.addT_OderBean(tBean);
////		bean.setCustomerName("abc");  //測試先手動key 下單者姓名
////		bean.setCustomerPhone("09123456789");  //測試先手動key 下單者電話
//		int[] rec = new int[1];
//		T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		t_Order_ListDAO.createOrder(bean,rec);
		
//		------------------------------------------------------
		
//		/*carRentalCompany測試*/
//		CarRentalCompanyBean carRentalCompanyBean = new CarRentalCompanyBean();
////		CarTypeBean carTypeBean = new CarTypeBean();
////		AccountBean user = new AccountBean();
//		
//		carRentalCompanyBean.setCompanyName("hahaQQ");
//		carRentalCompanyBean.setAddress("cantfindmeQQ");
//		carRentalCompanyBean.setDescription(null);
//		carRentalCompanyBean.setOpenHours("alwaysOPenQQ");
//		carRentalCompanyBean.setTelphoneNum("0800000123QQ");
//		carRentalCompanyBean.setCompanyAccount("306478QQ");
////		carTypeBean.setCarType("mmm");
////		carTypeBean.setPrice(BigDecimal.valueOf(9527));
//		
////		carTypeBean.setCarRentalCompanyBean(carRentalCompanyBean);
//
//		
//		CarRentalCompanyDAO carRentalCompanyDAO = new CarRentalCompanyDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		carRentalCompanyDAO.signUPCarRentalCompany(carRentalCompanyBean);
		
//		------------------------------------------------------
		
//		/*getSN_RENTALCOMPANY測試*/
//		CarRentalCompanyBean carRentalCompanyBean = new CarRentalCompanyBean();
//		carRentalCompanyBean.setCompanyAccount("306478QQ");
//		CarRentalCompanyDAO carRentalCompanyDAO = new CarRentalCompanyDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		carRentalCompanyDAO.getSN_RENTALCOMPANY(carRentalCompanyBean);
//		System.out.println(carRentalCompanyBean.getSn_carRentalCompany());
		
//		------------------------------------------------------
		
//		/*addCarType測試*/
//		CarRentalCompanyBean carRentalCompanyBean = new CarRentalCompanyBean();
//		carRentalCompanyBean.setCompanyAccount("306478QQ");	//先假裝CompanyAccount為306478QQ
//		CarRentalCompanyDAO carRentalCompanyDAO = new CarRentalCompanyDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		carRentalCompanyDAO.getSN_RENTALCOMPANY(carRentalCompanyBean);
//		
//		CarTypeBean carTypeBean = new CarTypeBean();
//		carTypeBean.setCarRentalCompanyBean(carRentalCompanyBean);	//將取得的SN放入carTypeBean中
//		System.out.println(carTypeBean.getCarRentalCompanyBean().getSn_carRentalCompany());
//		carTypeBean.setCarType("三輪車");
//		carTypeBean.setPrice(BigDecimal.valueOf(306478));
//		carRentalCompanyDAO = new CarRentalCompanyDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		carRentalCompanyDAO.addCarType(carTypeBean);
		
		
//		------------------------------------------------------
//		/*orderTest分開版本*/
//		OrderTableBean bean = new OrderTableBean();
//		T_OrderBean tBean = new T_OrderBean();
//		AccountBean user = new AccountBean();
//		hsrDO hsrDO = new hsrDO();
//		hsrDO.setSnSchedule(101); //假設訂購sn101的車票
//		tBean.setHsrDO(hsrDO);
//		tBean.setTrafficPrice(BigDecimal.valueOf(1000));
//		tBean.setNums_days(BigDecimal.valueOf(1));
//		tBean.setStartPoint("banqiao");
//		tBean.setDestination("nangang");
//		tBean.setDeparatureDate(Timestamp.valueOf(LocalDate.of(2020, 10, 14).atStartOfDay()));
//		tBean.setOrderType("0");
//		tBean.setCustomerName("asaseq");
//		tBean.setCustomerPhone("0808004564");
//		//System.out.println(user.getEmail()==null);
//		//System.out.println(user.getUserName());
//		
//		user.setUserName("abab");	//假裝訂購人為innocence
//		bean.setUser(user);	//假裝訂購人為innocence
//		bean.addT_OderBean(tBean);
////		bean.setCustomerName("abc");  //測試先手動key 下單者姓名
////		bean.setCustomerPhone("09123456789");  //測試先手動key 下單者電話
//		int[] rcd = new int[1];
//		String[] getOrder_id = new String[1];
//		T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		t_Order_ListDAO.createOrderTable(bean, rcd, getOrder_id);
////		System.out.println("order_id= " + order_id[0]);
//		BigDecimal order_id = new BigDecimal(getOrder_id[0]);
////		System.out.println(order_id);
//		tBean.setOrder_id(order_id);
//		t_Order_ListDAO = new T_Order_ListDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
////		Set<T_OrderBean> traffic = bean.getT_OderBeans();
////		for(T_OrderBean ele : traffic ) {
////			System.out.println("order_id= " + ele.getOrder_id());
////			System.out.println("SnSchedule= " + ele.getHsrDO().getSnSchedule());
////			System.out.println("TrafficPrice= " + ele.getTrafficPrice());
////			System.out.println("Nums_days= " + ele.getNums_days());
////			System.out.println("StartPoint= " + ele.getStartPoint());
////			System.out.println("Destination= " + ele.getDestination());
////			System.out.println("OrderType= " + ele.getOrderType());
////			System.out.println("CustomerName= " + ele.getCustomerName());
////			System.out.println("CustomerPhone= " + ele.getCustomerPhone());
////			System.out.println("T_sn_order= " + ele.getT_sn_order());
////
////		}
//		t_Order_ListDAO.createT_Order_List(bean, rcd);
		
		
//		------------------------------------------------------
//		/*historicalOrderInfo*/
////		OrderTableBean bean = new OrderTableBean();
////		T_OrderBean tBean = new T_OrderBean();
////		AccountBean user = new AccountBean();
////		hsrDO hsrDO = new hsrDO();
////		ArrayList<OrderTableBean> orderTableBeans = new ArrayList<>();
//		ArrayList<ArrayList> combineArrayList = new ArrayList<>();
//		T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		t_Order_ListDAO.searchHistoricalOrder(combineArrayList, "abab");
//		System.out.println("combineArrayList.size= " + combineArrayList.size());
//		
//        ObjectMapper objectMapper = new ObjectMapper();
//        String ujson = objectMapper.writeValueAsString(combineArrayList);
//		JsonNode jsonNode = objectMapper.readTree(ujson);
//		System.out.println(jsonNode.path(0).size());
//		JsonNode node = jsonNode.path(1).path(1).path("order_id");
//		System.out.println(node);
//        System.out.println(ujson+"\n");

        


//		for(OrderTableBean ele : orderTableBeans) {
//			Set<T_OrderBean> traffic = ele.getT_OderBeans();
//			System.out.print("Order_id= "+ele.getOrder_id() + "\t");
//			System.out.print("Order_date= " + ele.getOrder_date() + "\t");
//			System.out.print("UserName= " + ele.getUser().getUserName() + "\t");
//			for(T_OrderBean tEleBean : traffic) {
//				System.out.print("T_sn_order= " + tEleBean.getT_sn_order() + "\t");
//				System.out.print("SnSchedule= " + tEleBean.getHsrDO().getSnSchedule() + "\t");
//				System.out.println("Sn_carType= " + tEleBean.getCarTypeBean().getSn_carType());
//			}
//		}
//		------------------------------------------------------

	}

}
