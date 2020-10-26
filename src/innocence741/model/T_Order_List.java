package innocence741.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pojo.OrderTableBean;

@Entity
@Table(name = "T_ORDER_LIST")
public class T_Order_List {
	@Id
	@Column(name = "SN_ORDER")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "sq_loginlog")
	@SequenceGenerator(name = "sq_loginlog", sequenceName = "SEQ_T_ORDER_LIST", allocationSize = 1)
	private BigDecimal t_sn_order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SN_SCHEDULE")
	private HighSpeedRail highSpeedRail;

	@Column(name = "TICKETPRICE")
	private BigDecimal ticketPrice;

	@Column(name = "NUMBERS_DAYS")
	private BigDecimal nums_days;

	@Column(name = "STARTPOINT")
	private String startPoint;

	@Column(name = "DESTINATION")
	private String destination;

	@Column(name = "DEPARTUREDATE")
	private Timestamp deparatureDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SN_CARTYPE")
	private CarType carType;

	@Column(name = "ORDER_TYPE")
	private String orderType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private OrderTableBean order_table; // 晚點改

	@Column(name = "NAME")
	private String customerName;

	@Column(name = "PHONE")
	private String customerPhone;

	public BigDecimal getT_sn_order() {
		return t_sn_order;
	}

	public void setT_sn_order(BigDecimal t_sn_order) {
		this.t_sn_order = t_sn_order;
	}

	public HighSpeedRail getHighSpeedRail() {
		return highSpeedRail;
	}

	public void setHighSpeedRail(HighSpeedRail highSpeedRail) {
		this.highSpeedRail = highSpeedRail;
	}

	public BigDecimal getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(BigDecimal ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public BigDecimal getNums_days() {
		return nums_days;
	}

	public void setNums_days(BigDecimal nums_days) {
		this.nums_days = nums_days;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Timestamp getDeparatureDate() {
		return deparatureDate;
	}

	public void setDeparatureDate(Timestamp deparatureDate) {
		this.deparatureDate = deparatureDate;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public OrderTableBean getOrder_table() {
		return order_table;
	}

	public void setOrder_table(OrderTableBean order_table) {
		this.order_table = order_table;
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

	

}
