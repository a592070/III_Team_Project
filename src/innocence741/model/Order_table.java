package innocence741.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import innocence741.model.*;

//大訂單的Bean
@Entity
@Table(name = "ORDER_TABLE")
public class Order_table {
	
	@Id
	@Column(name = "ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id; // PK
	
	@Column(name = "ORDER_DATE")
	private Timestamp order_date; // 下訂單時間，default
	
	@Column(name = "USERNAME")
	private String user; // Account資料 晚點改
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order_table", cascade = CascadeType.ALL)
	private Set<T_Order_List> t_Order_Lists;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Timestamp getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Set<T_Order_List> getT_Order_Lists() {
		return t_Order_Lists;
	}

	public void setT_Order_Lists(Set<T_Order_List> t_Order_Lists) {
		this.t_Order_Lists = t_Order_Lists;
	}

}
