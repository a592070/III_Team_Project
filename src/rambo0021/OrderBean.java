package rambo0021;

import java.text.SimpleDateFormat;
import java.util.Date;

class OrderBean {
	private int orderId;
	private Date orderDate;
	private String orderDateString;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public String getOrderDateString() {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		String Date = sdf.format(orderDate);
		return Date;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}
