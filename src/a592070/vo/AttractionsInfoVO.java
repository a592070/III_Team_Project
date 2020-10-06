package a592070.vo;

import utils.StringUtil;

import java.math.BigDecimal;

public class AttractionsInfoVO {
    private String name;
    private String address;
    private String opentime;
    private BigDecimal rating;
    private String region;
    private String area;
    private String type;

    public AttractionsInfoVO() {
    }

    public AttractionsInfoVO(String name, String address, String opentime, BigDecimal rating, String region, String area, String type) {
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
        if(StringUtil.isEmpty(address)){
            this.address = "暫時沒有資料";
        }else{
            this.address = address;
        }
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        if(StringUtil.isEmpty(opentime)){
            this.opentime = "暫時沒有資料";
        }else{
            this.opentime = opentime;
        }
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
        if(StringUtil.isEmpty(region)){
            this.region = "暫時沒有資料";
        }else{
            this.region = region;
        }
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        if(StringUtil.isEmpty(area)){
            this.area = "暫時沒有資料";
        }else if("Northern".toUpperCase().equals(area.toUpperCase())){
            this.area = "北部";
        }else if("Central".toUpperCase().equals(area.toUpperCase())){
            this.area = "中部";
        }else if("Southern".toUpperCase().equals(area.toUpperCase())){
            this.area = "南部";
        }else if("Outer".toUpperCase().equals(area.toUpperCase())){
            this.area = "外島";
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(StringUtil.isEmpty(type)){
            this.type = "暫時沒有資料";
        }else if("scenic_spot".toUpperCase().equals(type.toUpperCase())){
            this.type = "景點";
        }else if("restaurant".toUpperCase().equals(type.toUpperCase())){
            this.type = "餐廳";
        }else if("hotel".toUpperCase().equals(type.toUpperCase())){
            this.type = "旅館";
        }
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
