package iring29.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

//小訂單的Bean (R_OrderBean)
public class R_OrderBean {

	BigDecimal r_sn_order;
	BigDecimal order_id;
	Timestamp booking_date; //訂位時間(前往用餐時間)

	BigDecimal customerNum;  //訂位人數
	BigDecimal deposit = BigDecimal.valueOf(500); //固定每筆訂餐廳的訂金為500
	RestaurantBean restaurantBean;
	
	String customerName; 	//下單時填入的姓名
	String customerPhone;	//下單時填入的電話
	
	public R_OrderBean() {
		
	}

	
	public R_OrderBean(BigDecimal r_sn_order, BigDecimal order_id, Timestamp booking_date, BigDecimal customerNum,
			BigDecimal deposit, RestaurantBean restaurantBean, String customerName, String customerPhone) {
		super();
		this.r_sn_order = r_sn_order;
		this.order_id = order_id;
		this.booking_date = booking_date;
		this.customerNum = customerNum;
		this.deposit = deposit;
		this.restaurantBean = restaurantBean;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerPhone() {
		return customerPhone;
	}


	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}


	public BigDecimal getR_sn_order() {
		return r_sn_order;
	}

	public void setR_sn_order(BigDecimal r_sn_order) {
		this.r_sn_order = r_sn_order;
	}

	public BigDecimal getOrder_id() {
		return order_id;
	}

	public void setOrder_id(BigDecimal order_id) {
		this.order_id = order_id;
	}

	public Timestamp getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(Timestamp booking_date) {
		this.booking_date = booking_date;
	}

	public BigDecimal getCustomerNum() {
		return customerNum;
	}

	//防呆，當訂位人數小於0，設訂位人數為1
	public void setCustomerNum(BigDecimal customerNum) {
		if(customerNum.compareTo(BigDecimal.ONE) <= 0) customerNum = BigDecimal.ONE;
		this.customerNum = customerNum;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public RestaurantBean getRestaurantBean() {
		return restaurantBean;
	}

	public void setRestaurantBean(RestaurantBean restaurantBean) {
		this.restaurantBean = restaurantBean;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("R_OrderBean [r_sn_order=");
		builder.append(r_sn_order);
		builder.append(", order_id=");
		builder.append(order_id);
		builder.append(", booking_date=");
		builder.append(booking_date);
		builder.append(", customerNum=");
		builder.append(customerNum);
		builder.append(", deposit=");
		builder.append(deposit);
		builder.append(", restaurantBean=");
		builder.append(restaurantBean);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", customerPhone=");
		builder.append(customerPhone);
		builder.append("]");
		return builder.toString();
	}

	
}
