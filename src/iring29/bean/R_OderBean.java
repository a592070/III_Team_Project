package iring29.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

//小訂單的Bean (R_OrderBean)
public class R_OderBean {


	Timestamp booking_date;

	BigDecimal customerNum;
	BigDecimal deposit = BigDecimal.valueOf(500); //固定每筆訂餐廳的訂金為500
	RestaurantBean restaurantBean;
	
	public R_OderBean() {
		
	}

	public R_OderBean(Timestamp booking_date, BigDecimal customerNum, BigDecimal deposit,
			RestaurantBean restaurantBean) {
		super();
		this.booking_date = booking_date;
		this.customerNum = customerNum;
		this.deposit = deposit;
		this.restaurantBean = restaurantBean;
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

	
}
