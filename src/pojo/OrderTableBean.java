package pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import asx54630.model.H_OrderBean;
//import innocence741.T_OrderBean;
import innocence741.model.T_Order_List;
import iring29.model.R_OrderBean;
import rambo0021.model.AccountBean;

//大訂單的Bean
@Entity
@Table(name="ORDER_TABLE")
public class OrderTableBean {

	@Id
	@Column(name = "ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer order_id; // PK

	@Column(name = "ORDER_DATE")
	Timestamp order_date; // 下訂單時間，default

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")//還沒註冊
	AccountBean user; // Account資料

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderTableBean", cascade = CascadeType.ALL)
	Set<R_OrderBean> r_OrderBeans;

	@Transient
	R_OrderBean r_OrdrerBean; // 小訂單的Bean (Restaurant)


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order_table", cascade = CascadeType.ALL)
//	@Transient
	Set<T_Order_List> t_Order_Lists;

	@Transient
	T_Order_List t_Order_List;  //小訂單的Bean (Traffic)


	@Transient
	Set<H_OrderBean> h_OrderBeans;
	@Transient
	H_OrderBean h_OrderBean;

	public OrderTableBean() {
		super();
		r_OrderBeans = new HashSet<R_OrderBean>();
		t_Order_Lists = new HashSet<T_Order_List>();
		h_OrderBeans = new HashSet<H_OrderBean>();
	}
	
	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
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

	public void addR_OrderBean(R_OrderBean rBean) {
		this.r_OrderBeans.add(rBean);
	}



	public Set<H_OrderBean> getH_OrderBeans() {
		return h_OrderBeans;
	}

	public void setH_OrderBeans(Set<H_OrderBean> h_OrderBeans) {
		this.h_OrderBeans = h_OrderBeans;
	}

	public void addH_OrderBean(H_OrderBean hBean) {
		this.h_OrderBeans.add(hBean);
	}


	public Set<T_Order_List> getT_Order_Lists() {
		return t_Order_Lists;
	}

	public void setT_Order_Lists(Set<T_Order_List> t_OrderBeans) {
		this.t_Order_Lists = t_OrderBeans;
	}

	public void addT_Order_Lists(T_Order_List tBean) {
		this.t_Order_Lists.add(tBean);
	}

}
