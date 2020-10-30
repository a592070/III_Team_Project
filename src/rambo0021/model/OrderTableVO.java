package rambo0021.model;

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
@Table(name="ORDERTABLEVIEW")
public class OrderTableVO {

	
	private BigDecimal order_id; // PK

	
	private Timestamp order_date; // 下訂單時間，default

	private String userName;
	@Id
	@Column(name = "ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public BigDecimal getOrder_id() {
		return order_id;
	}

	public void setOrder_id(BigDecimal order_id) {
		this.order_id = order_id;
	}
	@Column(name = "ORDER_DATE")
	public Timestamp getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
    @Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
}
