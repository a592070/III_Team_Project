package a592070.pojo;

import java.sql.Timestamp;

public class TravelEleRestaurantDO {
    private int sn;
    private int travelId;
    private Timestamp time;
    private RestaurantVO restaurant;

    public TravelEleRestaurantDO() {
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public RestaurantVO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantVO restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "TravelEleRestaurantDO{" +
                "sn=" + sn +
                ", travelId=" + travelId +
                ", time=" + time +
                ", restaurant=" + restaurant +
                '}';
    }
}
