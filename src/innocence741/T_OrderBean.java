package innocence741;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class T_OrderBean {
	BigDecimal t_sn_order = null;
	BigDecimal order_id = null;
	hsrDO hsrDO = null;	//取得snSchedule
	BigDecimal trafficPrice = null;	//交通訂單總價格
	BigDecimal nums_days = null;	//票價張數租車天數
	String startPoint = null;	//出發地
	String destination = null;	//目的地
	Timestamp deparatureDate =null;		//出發日
	CarTypeBean carTypeBean = null;		//取得sn_carType
	String orderType = null;	//紀錄為火車票或租車
	
	public T_OrderBean() {
		
	}
	public T_OrderBean(BigDecimal t_sn_order, BigDecimal order_id, hsrDO hsrDO, BigDecimal trafficPrice, BigDecimal nums_days, String startPoint, String destination, Timestamp deparatureDate, CarTypeBean carTypeBean, String orderType) {
		super();
		this.t_sn_order = t_sn_order;
		this.order_id = order_id;
		this.hsrDO = hsrDO;
		this.trafficPrice = trafficPrice;
		this.nums_days = nums_days;
		this.startPoint = startPoint;
		this.destination = destination;
		this.deparatureDate = deparatureDate;
		this.carTypeBean = carTypeBean;
		this.orderType = orderType;
	}
	
	public BigDecimal getT_sn_order() {
		return t_sn_order;
	}
	public void setT_sn_order(BigDecimal t_sn_order) {
		this.t_sn_order = t_sn_order;
	}
	public BigDecimal getOrder_id() {
		return order_id;
	}
	public void setOrder_id(BigDecimal order_id) {
		this.order_id = order_id;
	}
	public hsrDO getHsrDO() {
		return hsrDO;
	}
	public void setHsrDO(hsrDO hsrDO) {
		this.hsrDO = hsrDO;
	}
	public BigDecimal getTrafficPrice() {
		return trafficPrice;
	}
	public void setTrafficPrice(BigDecimal trafficPrice) {
		this.trafficPrice = trafficPrice;
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
	public CarTypeBean getCarTypeBean() {
		return carTypeBean;
	}
	public void setCarTypeBean(CarTypeBean carTypeBean) {
		this.carTypeBean = carTypeBean;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	

	
	
}
