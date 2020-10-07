package a592070.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import utils.StringUtil;

import java.math.BigDecimal;

@JsonDeserialize(using = AttractionJsonDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttractionDO {
    private int id;
    private String name;
    private String toldescribe;
    private String description;
    private String tel;
    private String address;
    private BigDecimal px;
    private BigDecimal py;
    private String openTime;
    private String picture;
    private String ticketInfo;
    private String travellingInfo;
    private String keywords;
    private String remarks;
    private BigDecimal rating;
    private String region;

    public AttractionDO() {
    }

    @Override
    public String toString() {
        return "AttractionDO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", toldescribe='" + toldescribe + '\'' +
                ", description='" + description + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", px=" + px +
                ", py=" + py +
                ", openTime='" + openTime + '\'' +
                ", ticketInfo='" + ticketInfo + '\'' +
                ", travelingInfo='" + travellingInfo + '\'' +
                ", keywords='" + keywords + '\'' +
                ", remarks='" + remarks + '\'' +
                ", rating=" + rating +
                ", region='" + region + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToldescribe() {
        return toldescribe;
    }

    public void setToldescribe(String toldescribe) {
        if(StringUtil.isEmpty(toldescribe)) toldescribe=null;
        this.toldescribe = toldescribe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(StringUtil.isEmpty(description)) description=null;
        this.description = description;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        if(StringUtil.isEmpty(tel)) tel=null;
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(StringUtil.isEmpty(address)) address=null;
        this.address = address;
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

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        if(StringUtil.isEmpty(openTime)) openTime=null;
        this.openTime = openTime;
    }

    public String getTicketInfo() {
        return ticketInfo;
    }

    public void setTicketInfo(String ticketInfo) {
        if(StringUtil.isEmpty(ticketInfo)) ticketInfo=null;
        this.ticketInfo = ticketInfo;
    }

    public String getTravellingInfo() {
        return travellingInfo;
    }

    public void setTravellingInfo(String travellingInfo) {
        if(StringUtil.isEmpty(travellingInfo)) travellingInfo =null;
        this.travellingInfo = travellingInfo;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        if(StringUtil.isEmpty(keywords)) keywords=null;
        this.keywords = keywords;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        if(StringUtil.isEmpty(remarks)) remarks=null;
        this.remarks = remarks;
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
        if(StringUtil.isEmpty(region)) region=null;
        this.region = region;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        if(StringUtil.isEmpty(picture)) picture=null;
        this.picture = picture;
    }
}
