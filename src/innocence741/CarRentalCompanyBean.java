package innocence741;

import java.math.BigDecimal;

public class CarRentalCompanyBean {
	private BigDecimal sn_carRentalCompany;
	private String companyName;
	private String address;
	private String description;
	private String openHours;
	private String telphoneNum;
	private String companyAccount;
	
	public BigDecimal getSn_carRentalCompany() {
		return sn_carRentalCompany;
	}
	public void setSn_carRentalCompany(BigDecimal sn_carRentalCompany) {
		this.sn_carRentalCompany = sn_carRentalCompany;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String discription) {
		this.description = discription;
	}
	public String getOpenHours() {
		return openHours;
	}
	public void setOpenHours(String openHours) {
		this.openHours = openHours;
	}
	public String getTelphoneNum() {
		return telphoneNum;
	}
	public void setTelphoneNum(String telphoneNum) {
		this.telphoneNum = telphoneNum;
	}
	public String getCompanyAccount() {
		return companyAccount;
	}
	public void setCompanyAccount(String commanyAccount) {
		this.companyAccount = commanyAccount;
	}
	
}
