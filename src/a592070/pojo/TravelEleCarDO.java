package a592070.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "TRAVEL_ELE_C")
public class TravelEleCarDO {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sn;

    private Timestamp time;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_ID", referencedColumnName = "SN")
    private CarVO car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAVEL_ID", referencedColumnName = "SN")
    private TravelSetDO travelSetDO;

    public TravelEleCarDO() {
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
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
