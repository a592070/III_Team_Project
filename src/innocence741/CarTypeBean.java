package innocence741;

import java.math.BigDecimal;

public class CarTypeBean {
	private BigDecimal sn_carType;
	private String carType;
	private BigDecimal price;
	private CarRentalCompanyBean carRentalCompanyBean;	//取得sn_carRentalCompany
	
	public BigDecimal getSn_carType() {
		return sn_carType;
	}
	public void setSn_carType(BigDecimal sn_carType) {
		this.sn_carType = sn_carType;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public CarRentalCompanyBean getCarRentalCompanyBean() {
		return carRentalCompanyBean;
	}
	public void setCarRentalCompanyBean(CarRentalCompanyBean carRentalCompanyBean) {
		this.carRentalCompanyBean = carRentalCompanyBean;
	}
	
}