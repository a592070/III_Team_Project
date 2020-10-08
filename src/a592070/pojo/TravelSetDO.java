package a592070.pojo;

import java.util.ArrayList;
import java.util.List;

public class TravelSetDO {
    private int sn;
    private String createdUser;
    private String description;
    private int priority;

    private List<TravelEleCarDO> listTravelCar;
    private List<TravelEleHotelDO> listTravelHotel;
    private List<TravelEleRestaurantDO> listTravelRestaurant;
    private List<TravelEleAttractionDO> listTravelAttraction;

    public TravelSetDO() {
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
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

    public String getDescription() {
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
