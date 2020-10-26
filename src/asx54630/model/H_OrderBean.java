package asx54630.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "H_ORDER_LIST")
public class H_OrderBean {

	private BigDecimal SN_ORDER;
	private BigDecimal ORDER_ID;
	private HotelBean hotelBean;
    private BigDecimal DOUBLE_ROOM;
    private BigDecimal QUADRUPLE_ROOM;                      
    private Date CHECK_IN;
    private Date CHECK_OUT;
    private BigDecimal H_PRICE;
    
    @Id @Column(name="SN_ORDER")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    public BigDecimal getSN_ORDER() {
		return SN_ORDER;
	}
	public void setSN_ORDER(BigDecimal sN_ORDER) {
		SN_ORDER = sN_ORDER;
	}
	
	@Transient
	public BigDecimal getORDER_ID() {
		return ORDER_ID;
	}
	public void setORDER_ID(BigDecimal oRDER_ID) {
		ORDER_ID = oRDER_ID;
	}
	@Column(name = "")
	public HotelBean getHotelBean() {
		return hotelBean;
	}
	public void setHotelBean(HotelBean hotelBean) {
		this.hotelBean = hotelBean;
	}
	@Column(name = "DOUBLE_ROOM")
	public BigDecimal getDOUBLE_ROOM() {
		return DOUBLE_ROOM;
	}
	public void setDOUBLE_ROOM(BigDecimal dOUBLE_ROOM) {
		DOUBLE_ROOM = dOUBLE_ROOM;
	}
	@Column(name = "QUADRUPLE_ROOM")
	public BigDecimal getQUADRUPLE_ROOM() {
		return QUADRUPLE_ROOM;
	}
	public void setQUADRUPLE_ROOM(BigDecimal qUADRUPLE_ROOM) {
		QUADRUPLE_ROOM = qUADRUPLE_ROOM;
	}
	@Column(name = "CHECK_IN")
	public Date getCHECK_IN() {
		return CHECK_IN;
	}
	public void setCHECK_IN(Date cHECK_IN) {
		CHECK_IN = cHECK_IN;
	}
	@Column(name = "CHECK_OUT")
	public Date getCHECK_OUT() {
		return CHECK_OUT;
	}
	public void setCHECK_OUT(Date cHECK_OUT) {
		CHECK_OUT = cHECK_OUT;
	}
	public BigDecimal getH_PRICE() {
		if(this.H_PRICE == null) {
		BigDecimal doublePrice = BigDecimal.ZERO;
		if(hotelBean.getDOUBLE_ROOM() != null) doublePrice = this.DOUBLE_ROOM.multiply(hotelBean.getDOUBLE_ROOM());
		BigDecimal quadruplePrice = BigDecimal.ZERO;
		if(hotelBean.getQUADRUPLE_ROOM() != null) quadruplePrice = this.QUADRUPLE_ROOM.multiply(hotelBean.getQUADRUPLE_ROOM());
		
		this.H_PRICE = doublePrice.add(quadruplePrice);
		}
		return this.H_PRICE;
	}
	public void setH_PRICE(BigDecimal h_PRICE) {
		this.H_PRICE = h_PRICE;
	}
}
