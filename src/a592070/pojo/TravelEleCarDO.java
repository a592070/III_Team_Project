package a592070.pojo;

import java.sql.Timestamp;

public class TravelEleCarDO {
    private int sn;
    private int travelId;
    private Timestamp time;
    private CarVO car;

    public TravelEleCarDO() {
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

    public CarVO getCar() {
        return car;
    }

    public void setCar(CarVO car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "TravelEleCarDO{" +
                "sn=" + sn +
                ", travelId=" + travelId +
                ", time=" + time +
                ", car=" + car +
                '}';
    }
}
