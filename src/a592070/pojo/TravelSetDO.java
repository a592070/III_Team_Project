package a592070.pojo;

import java.util.List;

public class TravelSetDO {
    private int sn;
    private String createdUser;

    List<TravelEleCarDO> listTravelCar;
    List<TravelEleHotelDO> listTravelHotel;
    List<TravelEleRestaurantDO> listTravelRestaurant;
    List<TravelEleAttractionDO> listTravelAttraction;

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
        return listTravelCar;
    }

    public void setListTravelCar(List<TravelEleCarDO> listTravelCar) {
        this.listTravelCar = listTravelCar;
    }

    public List<TravelEleHotelDO> getListTravelHotel() {
        return listTravelHotel;
    }

    public void setListTravelHotel(List<TravelEleHotelDO> listTravelHotel) {
        this.listTravelHotel = listTravelHotel;
    }

    public List<TravelEleRestaurantDO> getListTravelRestaurant() {
        return listTravelRestaurant;
    }

    public void setListTravelRestaurant(List<TravelEleRestaurantDO> listTravelRestaurant) {
        this.listTravelRestaurant = listTravelRestaurant;
    }

    public List<TravelEleAttractionDO> getListTravelAttraction() {
        return listTravelAttraction;
    }

    public void setListTravelAttraction(List<TravelEleAttractionDO> listTravelAttraction) {
        this.listTravelAttraction = listTravelAttraction;
    }
}
