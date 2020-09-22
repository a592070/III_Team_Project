package pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;

@JsonDeserialize(using = AttractionsDOJsonDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttractionsDO{
    // type 1 is scenic_spot
    // type 3 is restaurant
    // type 4 is hotel

    private String id;
    private String name;
    private String description;
    private String address;
    private String tel;
    private BigDecimal px;
    private BigDecimal py;
    private String openTime;			// for type 1 3
    private String travelingInfo;       // for type1
    private String totalNumberRooms;    // for type4
    private String serviceInfo;         // for type4
    private String region;              // FK
    private int type;                   // FK

    public AttractionsDO(String id, String name, String description, String address, String region, String tel, String openTime, BigDecimal px, BigDecimal py, String travelingInfo, String totalNumberRooms, String serviceInfo, int type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.region = region;
        this.tel = tel;
        this.openTime = openTime;
        this.px = px;
        this.py = py;
        this.travelingInfo = travelingInfo;
        this.totalNumberRooms = totalNumberRooms;
        this.serviceInfo = serviceInfo;
        this.type = type;
    }

    public AttractionsDO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getTotalNumberRooms() {
        return totalNumberRooms;
    }

    public void setTotalNumberRooms(String totalNumberRooms) {
        this.totalNumberRooms = totalNumberRooms;
    }

    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTravelingInfo() {
        return travelingInfo;
    }

    public void setTravelingInfo(String travelingInfo) {
        this.travelingInfo = travelingInfo;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", region='" + region + '\'' +
                ", tel='" + tel + '\'' +
                ", openTime='" + openTime + '\'' +
                ", px=" + px +
                ", py=" + py +
                ", travelingInfo='" + travelingInfo + '\'' +
                ", totalNumberRooms='" + totalNumberRooms + '\'' +
                ", serviceInfo='" + serviceInfo + '\'' +
                ", type=" + type +
                '}';
    }
}
