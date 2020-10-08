package innocence741;

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

import javax.sql.DataSource;

import controller.ConnectionPool;
import iring29.R_Order_ListDAO;
import iring29.bean.RestaurantBean;
import pojo.AccountDO;
import pojo.OrderTableBean;
import rambo0021.AccountBean;

public class Test_innocence741 {
	
	
	public static void main(String[] args) throws IOException, SQLException {
		OrderTableBean bean = new OrderTableBean();
		T_OrderBean tBean = new T_OrderBean();
		AccountBean user = new AccountBean();
		hsrDO hsrDO = new hsrDO();
		hsrDO.setSnSchedule(101); //假設訂購sn101的車票
		tBean.setHsrDO(hsrDO);
		tBean.setTrafficPrice(BigDecimal.valueOf(1000));
		tBean.setNums_days(BigDecimal.valueOf(1));
		tBean.setStartPoint("banqiao");
		tBean.setDestination("nangang");
		tBean.setDeparatureDate(Timestamp.valueOf(LocalDate.of(2020, 10, 14).atStartOfDay()));
		tBean.setOrderType("0");
		//System.out.println(user.getEmail()==null);
		//System.out.println(user.getUserName());
		
		user.setUserName("innocence");
		bean.setUser(user);	//假設訂購人為innocence
		bean.addT_OderBean(tBean);
		bean.setCustomerName("abc");  //測試先手動key 下單者姓名
		bean.setCustomerPhone("09123456789");  //測試先手動key 下單者電話
		
		T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
		t_Order_ListDAO.createOrder(bean);
	}

}
