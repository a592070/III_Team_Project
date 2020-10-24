package iring29.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import rambo0021.model.AccountBean;

@Entity
@Table(name = "RESTAURANT")
public class RestaurantBean {

	private BigDecimal r_sn;
	private String name;
	private String address;
	private String opentime;
	private String description;
	private String transportation;
	private String type;
	private BigDecimal rating;
	private String region;
	private String picture;
	private String serviceinfo;
	private String booking_id;
	private String account;
	private AccountBean accountBean;
	private Set<R_OrderBean> r_OrderBeans = new HashSet<R_OrderBean>();

	// constructor
	public RestaurantBean() {

	}

	// constructor
	public RestaurantBean(String name, String type, String region) {
		this.name = name;
		this.type = type;
		this.region = region;
	}
	// constructor

	public RestaurantBean(BigDecimal r_sn, String name, String address, String opentime, String description,
			String transportation, String type, BigDecimal rating, String region, String picture, String serviceinfo,
			String booking_id, String account) {
		super();
		this.r_sn = r_sn;
		this.name = name;
		this.address = address;
		this.opentime = opentime;
		this.description = description;
		this.transportation = transportation;
		this.type = type;
		this.rating = rating;
		this.region = region;
		this.picture = picture;
		this.serviceinfo = serviceinfo;
		this.booking_id = booking_id;
		this.account = account;
	}

	@Id
	@Column(name = "R_SN")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESTAURANT_SEQ")
	@SequenceGenerator(name = "RESTAURANT_SEQ", sequenceName = "RESTAURANT_SEQ", allocationSize = 1)
	public BigDecimal getR_sn() {
		return r_sn;
	}

	public void setR_sn(BigDecimal r_sn) {
		this.r_sn = r_sn;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "OPENTIME")
	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TRANSPORTATION")
	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "RATING")
	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	@Column(name = "REGION")
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "PICTURE")
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(name = "SERVICEINFO")
	public String getServiceinfo() {
		return serviceinfo;
	}

	public void setServiceinfo(String serviceinfo) {
		this.serviceinfo = serviceinfo;
	}

	@Column(name = "BOOKING_ID")
	public String getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}

	@Transient
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ACCOUNT", referencedColumnName="USERNAME")
//	public AccountBean getAccountBean() {
//		return accountBean;
//	}
//	public void setAccountBean(AccountBean accountBean) {
//		this.accountBean = accountBean;
//	}
//	
//	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantBean", cascade = CascadeType.ALL)
	public Set<R_OrderBean> getR_OrderBeans() {
		return r_OrderBeans;
	}
	public void setR_OrderBeans(Set<R_OrderBean> r_OrderBeans) {
		this.r_OrderBeans = r_OrderBeans;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RestaurantBean [name=");
		builder.append(name);
		builder.append(", address=");
		builder.append(address);
		builder.append(", opentime=");
		builder.append(opentime);
		builder.append(", description=");
		builder.append(description);
		builder.append(", transportation=");
		builder.append(transportation);
		builder.append(", type=");
		builder.append(type);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", region=");
		builder.append(region);
		builder.append(", picture=");
		builder.append(picture);
		builder.append(", serviceinfo=");
		builder.append(serviceinfo);
		builder.append(", booking_id=");
		builder.append(booking_id);
		builder.append(", account=");
//		builder.append(account);
		builder.append("]");
		return builder.toString();
	}

}
