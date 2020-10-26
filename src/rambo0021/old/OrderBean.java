package rambo0021.old;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderBean {
	private int orderId;
	private Date orderDate;
	private String orderDateString = "2020-10-13";
	private int r_orderId;
	private int h_orderId;
	private int t_orderId;
	
	
	public void setOrderDateString(String orderDateString) {
		this.orderDateString = orderDateString;
	}
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
		if(orderDate == null) {
			orderDate = new Date(System.currentTimeMillis());
		}
		String Date = sdf.format(orderDate);
		return Date;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getR_orderId() {
		return r_orderId;
	}
	@Override
	public String toString() {
		return "OrderBean [orderId=" + orderId + ", orderDate=" + orderDate + ", orderDateString=" + orderDateString
				+ ", r_orderId=" + r_orderId + ", h_orderId=" + h_orderId + ", t_orderId=" + t_orderId + "]";
	}
	public void setR_orderId(int r_orderId) {
		this.r_orderId = r_orderId;
	}
	public int getH_orderId() {
		return h_orderId;
	}
	public void setH_orderId(int h_orderId) {
		this.h_orderId = h_orderId;
	}
	public int getT_orderId() {
		return t_orderId;
	}
	public void setT_orderId(int t_orderId) {
		this.t_orderId = t_orderId;
	}

}
