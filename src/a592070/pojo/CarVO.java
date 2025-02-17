package a592070.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CarVOJsonSerializer.class)
@JsonDeserialize(using = CarVOJsonDeserializer.class)
public class CarVO {
    private int sn;
    private String carType;
    private int price;
    private String company;

    public CarVO() {
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "CarVO{" +
                "sn=" + sn +
                ", carType='" + carType + '\'' +
                ", price=" + price +
                ", company='" + company + '\'' +
                '}';
    }
}
