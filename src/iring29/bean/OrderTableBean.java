package iring29.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import rambo0021.AccountBean;

//大訂單的Bean
public class OrderTableBean {
	
	BigDecimal order_id;	// PK
	BigDecimal price;		// H + C + R  H:Hotel; C:Car; R:Restaurant
	Timestamp order_date;	// 下訂單時間，default
	AccountBean user;		//Account資料
	String customerName; 	//
	String customerPhone;	//
	
	Set<R_OderBean> r_OderBeans;
	R_OderBean r_OderBean;    //小訂單的Bean (Restaurant)
	//C_OderBean h_OderBean;  //小訂單的Bean (hotel)
	//H_OderBean c_OderBean;  //小訂單的Bean (car)

	public OrderTableBean() {
		super();
		r_OderBeans = new HashSet<R_OderBean>();
	}
	
	public BigDecimal getOrder_id() {
		return order_id;
	}

	public void setOrder_id(BigDecimal order_id) {
		this.order_id = order_id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Timestamp getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}

	public AccountBean getUser() {
		return user;
	}

	public void setUser(AccountBean user) {
		this.user = user;
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

	public Set<R_OderBean> getR_OderBeans() {
		return r_OderBeans;
	}

	public void setR_OderBeans(Set<R_OderBean> r_OderBeans) {
		this.r_OderBeans = r_OderBeans;
	}
	public void addR_OderBean(R_OderBean rBean) {
		this.r_OderBeans.add(rBean);
	}



	
}
