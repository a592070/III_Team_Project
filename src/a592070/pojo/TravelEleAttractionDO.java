package a592070.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TRAVEL_ELE_A")
public class TravelEleAttractionDO {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sn;
    @Column(name = "TRAVEL_ID")
    private int travelId;
    private Timestamp time;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "A_ID", referencedColumnName = "A_SN")
    private AttractionDO attraction;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "TRAVEL_ID")
    private TravelSetDO travelSetDO;

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

//    public TravelSetDO getTravelSetDO() {
//        return travelSetDO;
//    }
//
//    public void setTravelSetDO(TravelSetDO travelSetDO) {
//        this.travelSetDO = travelSetDO;
//    }

    @Override
    public String toString() {
        return "TravelEleAttractionDO{" +
                "sn=" + sn +
                ", travelId=" + travelId +
                ", time=" + time +
                ", attraction=" + attraction +
                '}';
    }
}
