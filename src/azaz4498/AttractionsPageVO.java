package azaz4498;

import java.math.BigDecimal;

public class AttractionsPageVO {
	private String name;
	private String description;
	private String address;
	private String tel;
	private BigDecimal px;
	private BigDecimal py;
	private String opentime;
	private String traveling_info;
	private String total_number_rooms;
	private String service_info;
	private BigDecimal rating;
	private String region;
	
	

	public AttractionsPageVO() {
	}
	

	public AttractionsPageVO(String name, String description, String address, String tel, BigDecimal px, BigDecimal py,
			String opentime, String traveling_info, String total_number_rooms, String service_info, BigDecimal rating,
			String region) {
		
		this.name = name;
		this.description = description;
		this.address = address;
		this.tel = tel;
		this.px = px;
		this.py = py;
		this.opentime = opentime;
		this.traveling_info = traveling_info;
		this.total_number_rooms = total_number_rooms;
		this.service_info = service_info;
		this.rating = rating;
		this.region = region;
	}





	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public BigDecimal getPx() {
		return px;
	}



	public void setPx(BigDecimal px) {
		this.px = px;
	}



	public BigDecimal getPy() {
		return py;
	}



	public void setPy(BigDecimal py) {
		this.py = py;
	}



	public String getOpentime() {
		return opentime;
	}



	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}



	public String getTraveling_info() {
		return traveling_info;
	}



	public void setTraveling_info(String traveling_info) {
		this.traveling_info = traveling_info;
	}



	public String getTotal_number_rooms() {
		return total_number_rooms;
	}



	public void setTotal_number_rooms(String total_number_rooms) {
		this.total_number_rooms = total_number_rooms;
	}



	public String getService_info() {
		return service_info;
	}



	public void setService_info(String service_info) {
		this.service_info = service_info;
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


	@Override
	public String toString() {
		return "AttractionsPageVO [name=" + name + ", description=" + description + ", address=" + address + ", tel="
				+ tel + ", px=" + px + ", py=" + py + ", opentime=" + opentime + ", traveling_info=" + traveling_info
				+ ", total_number_rooms=" + total_number_rooms + ", service_info=" + service_info + ", rating=" + rating
				+ ", region=" + region + "]"+"\n";
	}
	
	

	

	
}
