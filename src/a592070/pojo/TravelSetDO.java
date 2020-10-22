package a592070.pojo;

import utils.StringUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "travelSetDO", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Transient
//    private Set<TravelEleCarDO> travelCars = new LinkedHashSet<>();
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "travelSetDO", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Transient
//    private Set<TravelEleHotelDO> travelHotels = new LinkedHashSet<>();
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "travelSetDO", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Transient
//    private Set<TravelEleRestaurantDO> travelRestaurants = new LinkedHashSet<>();
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "travelSetDO", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Transient
//    private Set<TravelEleAttractionDO> travelAttractions = new LinkedHashSet<>();


//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "travelSetDO", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<TravelEleCarDO> travelCars = new ArrayList<>();
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "travelSetDO", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<TravelEleHotelDO> travelHotels = new ArrayList<>();
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "travelSetDO", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<TravelEleRestaurantDO> travelRestaurants = new ArrayList<>();
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "travelSetDO", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<TravelEleAttractionDO> travelAttractions = new ArrayList<>();

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

    public List<TravelEleCarDO> getTravelCars() {
        return travelCars;
    }

    public void setTravelCars(List<TravelEleCarDO> travelCars) {
        this.travelCars = travelCars;
    }

    public List<TravelEleHotelDO> getTravelHotels() {
        return travelHotels;
    }

    public void setTravelHotels(List<TravelEleHotelDO> travelHotels) {
        this.travelHotels = travelHotels;
    }

    public List<TravelEleRestaurantDO> getTravelRestaurants() {
        return travelRestaurants;
    }

    public void setTravelRestaurants(List<TravelEleRestaurantDO> travelRestaurants) {
        this.travelRestaurants = travelRestaurants;
    }

    public List<TravelEleAttractionDO> getTravelAttractions() {
        return travelAttractions;
    }

    public void setTravelAttractions(List<TravelEleAttractionDO> travelAttractions) {
        this.travelAttractions = travelAttractions;
    }

    @Override
    public String toString() {
        return "TravelSetDO{" +
                "sn=" + sn +
                ", createdUser='" + createdUser + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                ", name='" + name + '\'' +
                ", available=" + available +
                ", travelCars=" + travelCars +
                ", travelHotels=" + travelHotels +
                ", travelRestaurants=" + travelRestaurants +
                ", travelAttractions=" + travelAttractions +
                '}';
    }
}
