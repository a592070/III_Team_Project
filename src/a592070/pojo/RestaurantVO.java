package a592070.pojo;

import java.math.BigDecimal;

public class RestaurantVO {
    private int sn;
    private String name;
    private String type;
    private String picture;
    private String address;
    private String description;
    private BigDecimal rating;

    public RestaurantVO() {
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RestaurantVO{" +
                "sn=" + sn +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", picture='" + picture + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
