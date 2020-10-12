package a592070.pojo;

import java.sql.Timestamp;

public class TravelEleHotelDO {
    private int sn;
    private int travelId;
    private Timestamp time;
    private HotelVO hotel;

    public TravelEleHotelDO() {
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

    public HotelVO getHotel() {
        return hotel;
    }

    public void setHotel(HotelVO hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "TravelEleHotelDO{" +
                "sn=" + sn +
                ", travelId=" + travelId +
                ", time=" + time +
                ", hotel=" + hotel +
                '}';
    }
}
