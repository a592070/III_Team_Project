package a592070.pojo;

import java.sql.Timestamp;

public class TravelEleAttractionDO {
    private int sn;
    private int travelId;
    private Timestamp time;
    private AttractionDO attraction;

    public TravelEleAttractionDO() {
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

    public AttractionDO getAttraction() {
        return attraction;
    }

    public void setAttraction(AttractionDO attraction) {
        this.attraction = attraction;
    }
}
