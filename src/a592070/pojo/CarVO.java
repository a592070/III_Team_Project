package a592070.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@JsonSerialize(using = CarVOJsonSerializer.class)
@JsonDeserialize(using = CarVOJsonDeserializer.class)
@Entity
@Table(name = "CARTYPE")
@SecondaryTable(name = "CARRENTALCOMPANY",
        pkJoinColumns = {@PrimaryKeyJoinColumn(name = "sn_rentalcompany")})
public class CarVO {
    @Id@Column(name = "SN_CARTYPE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sn;
    @Column(name = "CARTYPE")
    private String carType;
    private int price;
    @Column(name = "NAME_COMPANY", table = "CARRENTALCOMPANY")
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
