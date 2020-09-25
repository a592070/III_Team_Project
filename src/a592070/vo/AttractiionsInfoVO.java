package a592070.vo;

import java.math.BigDecimal;

public class AttractiionsInfoVO {
    private String name;
    private String address;
    private String opentime;
    private BigDecimal rating;
    private String region;
    private String area;
    private String type;

    public AttractiionsInfoVO() {
    }

    public AttractiionsInfoVO(String name, String address, String opentime, BigDecimal rating, String region, String area, String type) {
        this.name = name;
        this.address = address;
        this.opentime = opentime;
        this.rating = rating;
        this.region = region;
        this.area = area;
        this.type = type;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", opentime='" + opentime + '\'' +
                ", rating=" + rating +
                ", region='" + region + '\'' +
                ", area='" + area + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
