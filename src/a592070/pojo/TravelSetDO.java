package a592070.pojo;

import utils.StringUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TRAVEL_SET")
public class TravelSetDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sn;
    @Column(name = "CREATED")
    private String createdUser;
    private String description;
    private int priority;
    @Column(name = "CREATED_TIME")
    private Timestamp createdTime;
    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;
    private String name;
    private int available;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "travelSetDO", cascade = CascadeType.ALL)
    private List<TravelEleCarDO> listTravelCar = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "travelSetDO", cascade = CascadeType.ALL)
    private List<TravelEleHotelDO> listTravelHotel = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "travelSetDO", cascade = CascadeType.ALL)
    private List<TravelEleRestaurantDO> listTravelRestaurant = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "travelSetDO", cascade = CascadeType.ALL)
    private List<TravelEleAttractionDO> listTravelAttraction = new ArrayList<>();

    public TravelSetDO() {
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getName() {
        if(StringUtil.isEmpty(name)) return "我的旅程";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getDescription() {
        if(StringUtil.isEmpty(description)) return "...";
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Timestamp getCreatedTime() {
        if(createdTime == null) return new Timestamp(System.currentTimeMillis());
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
//        if(createdTime == null) this.createdTime = new Timestamp(System.currentTimeMillis());
        this.createdTime = createdTime;
    }

    public Timestamp getUpdateTime() {
        if(updateTime == null) return new Timestamp(System.currentTimeMillis());
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public List<TravelEleCarDO> getListTravelCar() {
        if(listTravelCar == null) listTravelCar = new ArrayList<>();
        return listTravelCar;
    }

    public void setListTravelCar(List<TravelEleCarDO> listTravelCar) {
        this.listTravelCar = listTravelCar;
    }

    public List<TravelEleHotelDO> getListTravelHotel() {
        if(listTravelHotel == null) listTravelHotel = new ArrayList<>();
        return listTravelHotel;
    }

    public void setListTravelHotel(List<TravelEleHotelDO> listTravelHotel) {
        this.listTravelHotel = listTravelHotel;
    }

    public List<TravelEleRestaurantDO> getListTravelRestaurant() {
        if(listTravelRestaurant == null) listTravelRestaurant = new ArrayList<>();
        return listTravelRestaurant;
    }

    public void setListTravelRestaurant(List<TravelEleRestaurantDO> listTravelRestaurant) {
        this.listTravelRestaurant = listTravelRestaurant;
    }

    public List<TravelEleAttractionDO> getListTravelAttraction() {
        if(listTravelAttraction == null) listTravelAttraction = new ArrayList<>();
        return listTravelAttraction;
    }

    public void setListTravelAttraction(List<TravelEleAttractionDO> listTravelAttraction) {
        this.listTravelAttraction = listTravelAttraction;
    }

    public void addAttraction(TravelEleAttractionDO travelEleAttractionDO){
        getListTravelAttraction().add(travelEleAttractionDO);
    }
    public void addCar(TravelEleCarDO travelEleCarDO){
        getListTravelCar().add(travelEleCarDO);
    }
    public void addHotel(TravelEleHotelDO travelEleHotelDO){
        getListTravelHotel().add(travelEleHotelDO);
    }
    public void addRestaurant(TravelEleRestaurantDO travelEleRestaurantDO){
        getListTravelRestaurant().add(travelEleRestaurantDO);
    }

    @Override
    public String toString() {
        return "TravelSetDO{" +
                "sn=" + sn +
                ", createdUser='" + createdUser + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", listTravelCar=" + listTravelCar +
                ", listTravelHotel=" + listTravelHotel +
                ", listTravelRestaurant=" + listTravelRestaurant +
                ", listTravelAttraction=" + listTravelAttraction +
                '}';
    }
}
