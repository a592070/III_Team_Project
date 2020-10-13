package rambo0021;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Imuseless {

	public static void main(String[] args) {
		AccountBean account = new AccountBean();
		OrderDAO orderDAO = new OrderDAO(); 
		List<OrderBean> list =null;
		account.setUserName("Irene");
		list=orderDAO.selectOrder(account);
		for (OrderBean order : list) {
		    System.out.println(order.getOrderDateString());
	        System.out.println(order.getOrderId());	
		}
		System.out.println(list.toString());
	}

}
