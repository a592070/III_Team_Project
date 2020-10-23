package pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import asx54630.H_OrderBean;
import innocence741.T_OrderBean;
import innocence741.model.T_Order_List;
import iring29.model.R_OrderBean;
import rambo0021.model.AccountBean;

//大訂單的Bean
public class OrderTableBean {

	@Id
	@Column(name = "ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	BigDecimal order_id; // PK
//	BigDecimal price;		// H + C + R  H:Hotel; C:Car; R:Restaurant

	@Column(name = "ORDER_DATE")
	Timestamp order_date; // 下訂單時間，default

//	@						//還沒註冊
	AccountBean user; // Account資料
//	String customerName; 	//下單時填入的姓名
//	String customerPhone;	//下單時填入的電話

	Set<R_OrderBean> r_OrderBeans;
	R_OrderBean r_OdrerBean; // 小訂單的Bean (Restaurant)

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order_table", cascade = CascadeType.ALL)
	Set<T_Order_List> t_OrderBeans;
//	T_OrderBean T_OderBean;  //小訂單的Bean (Traffic)
	H_OrderBean h_OderBean; // 小訂單的Bean (hotel)
	Set<H_OrderBean> h_OrderBeans;

	public OrderTableBean() {
		super();
		r_OrderBeans = new HashSet<R_OrderBean>();
		t_OrderBeans = new HashSet<T_Order_List>();

	}

	public BigDecimal getOrder_id() {
		return order_id;
	}

	public void setOrder_id(BigDecimal order_id) {
		this.order_id = order_id;
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

	public Set<R_OrderBean> getR_OrderBeans() {
		return r_OrderBeans;
	}

	public void setR_OrderBeans(Set<R_OrderBean> r_OrderBeans) {
		this.r_OrderBeans = r_OrderBeans;
	}

	public R_OrderBean getR_OdrerBean() {
		return r_OdrerBean;
	}

	public void setR_OdrerBean(R_OrderBean r_OdrerBean) {
		this.r_OdrerBean = r_OdrerBean;
	}

	public Set<T_Order_List> getT_OrderBeans() {
		return t_OrderBeans;
	}

	public void setT_OrderBeans(Set<T_Order_List> t_OrderBeans) {
		this.t_OrderBeans = t_OrderBeans;
	}

	public H_OrderBean getH_OderBean() {
		return h_OderBean;
	}

	public void setH_OderBean(H_OrderBean h_OderBean) {
		this.h_OderBean = h_OderBean;
	}

	public Set<H_OrderBean> getH_OrderBeans() {
		return h_OrderBeans;
	}

	public void setH_OrderBeans(Set<H_OrderBean> h_OrderBeans) {
		this.h_OrderBeans = h_OrderBeans;
	}

}
