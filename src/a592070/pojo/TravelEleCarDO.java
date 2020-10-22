package a592070.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TRAVEL_ELE_C")
public class TravelEleCarDO {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sn;

    private Timestamp time;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_ID", referencedColumnName = "SN_CARTYPE")
    private CarVO car;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAVEL_ID")
    private TravelSetDO travelSetDO;

    public TravelEleCarDO() {
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
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

    public TravelSetDO getTravelSetDO() {
        return travelSetDO;
    }

    public void setTravelSetDO(TravelSetDO travelSetDO) {
        this.travelSetDO = travelSetDO;
    }

    @Override
    public String toString() {
        return "TravelEleCarDO{" +
                "sn=" + sn +
                ", time=" + time +
                ", car=" + car +
                '}';
    }
}
