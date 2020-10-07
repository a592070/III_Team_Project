package iring29.bean;

import java.math.BigDecimal;

public class RestaurantBean {

	BigDecimal r_sn;
	String name;
	String address;
	String opentime;
	String description;
	String transportation;
	String type;
	BigDecimal rating;
	String region;
	String picture; 
	String serviceinfo;
	String booking_id;
	String account;

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
	public RestaurantBean(String name, String address, String opentime, String description, String transportation,
			String type, BigDecimal rating, String region, String picture, String serviceinfo,
			String booking_id, String account) {
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
	

	public BigDecimal getR_sn() {
		return r_sn;
	}
	public void setR_sn(BigDecimal r_sn) {
		this.r_sn = r_sn;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getServiceinfo() {
		return serviceinfo;
	}

	public void setServiceinfo(String serviceinfo) {
		this.serviceinfo = serviceinfo;
	}

	public String getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
		builder.append(account);
		builder.append("]");
		return builder.toString();
	}
	


}
