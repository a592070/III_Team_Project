package a592070.pojo;

import java.math.BigDecimal;

public class RestaurantDO {
    String rID;
    String name;
    String address;
    String opentime;
    String description;
    String transportation;
    String type;
    BigDecimal rating;
    String region;
    String tel;
    String picture;
    String serviceinfo;
    String booking_id;

    public RestaurantDO() {
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getRId() {
        return rID;
    }

    public void setRId(String rID) {
        this.rID = rID;
    }

}
