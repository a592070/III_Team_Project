package a592070.dao;

import a592070.pojo.*;
import controller.ConnectionPool;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelSetDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;
    private Session session;

    public TravelSetDAO(int connType) throws IOException {
        this.ds = ConnectionPool.getDataSource(connType);
    }
    public TravelSetDAO(Session session) {
        this.session = session;
    }

    public List<TravelSetDO> listTravelSet() {
        return listTravelSet("", 1);
    }
    public List<TravelSetDO> listTravelSet(String created) {
        return listTravelSet(created, 1);
    }
    public List<TravelSetDO> listTravelSet(String created, int available){
        String hql = "from TravelSetDO where createdUser=? and available=? order by priority desc ";
        Query<TravelSetDO> query = session.createQuery(hql, TravelSetDO.class);
        query.setParameter(0, created);
        query.setParameter(1, available);

        List<TravelSetDO> list = query.list();
        list.forEach(ele ->{
            ele.setTravelAttractions(getAttractionSetByID(ele.getSn()));
            ele.setTravelCars(getCarSetByID(ele.getSn()));
            ele.setTravelHotels(getHotelSetByID(ele.getSn()));
            ele.setTravelRestaurants(getRestaurantSetByID(ele.getSn()));
        });

        return list;
    }

    public TravelSetDO getTravelSetByID(int id) {
        return getTravelSetByID(id, 1);
    }
    public TravelSetDO getTravelSetByID(int id, int available){
        String hql = "from TravelSetDO where sn=? and available=? order by sn";
        Query<TravelSetDO> query = session.createQuery(hql, TravelSetDO.class);
        query.setParameter(0, id);
        query.setParameter(1, available);

        TravelSetDO travelSetDO = query.uniqueResultOptional().orElse(null);
        if(travelSetDO != null){
            travelSetDO.setTravelAttractions(getAttractionSetByID(id));
            travelSetDO.setTravelCars(getCarSetByID(id));
            travelSetDO.setTravelHotels(getHotelSetByID(id));
            travelSetDO.setTravelRestaurants(getRestaurantSetByID(id));
        }

        return travelSetDO;
    }
    public List<TravelEleAttractionDO> getAttractionSetByID(int id){
        String hql = "from TravelEleAttractionDO where travelSetDO.id = ?";
        Query<TravelEleAttractionDO> query = session.createQuery(hql, TravelEleAttractionDO.class);
        query.setParameter(0, id);

        return query.list();
    }
    public List<TravelEleCarDO> getCarSetByID(int id) {
        String hql = "from TravelEleCarDO where travelSetDO.id = ?";
        Query<TravelEleCarDO> query = session.createQuery(hql, TravelEleCarDO.class);
        query.setParameter(0, id);

        return query.list();
    }
    public List<TravelEleHotelDO> getHotelSetByID(int id) {
        String hql = "from TravelEleHotelDO where travelSetDO.id = ?";
        Query<TravelEleHotelDO> query = session.createQuery(hql, TravelEleHotelDO.class);
        query.setParameter(0, id);

        return query.list();
    }
    public List<TravelEleRestaurantDO> getRestaurantSetByID(int id) {
        String hql = "from TravelEleRestaurantDO where travelSetDO.id = ?";
        Query<TravelEleRestaurantDO> query = session.createQuery(hql, TravelEleRestaurantDO.class);
        query.setParameter(0, id);

        return query.list();
    }


    public boolean addTravelSet(TravelSetDO travelSetDO){
        session.save(travelSetDO);
        return true;
    }

    private void addTravelEleA(TravelSetDO travelSetDO) {

    }
    private void addTravelEleC(TravelSetDO travelSetDO) {

    }
    private void addTravelEleH(TravelSetDO travelSetDO) {

    }
    private void addTravelEleR(TravelSetDO travelSetDO) {

    }

    public boolean updateTravelSet(TravelSetDO travelSetDO) {
        session.merge(travelSetDO);
        return true;
    }

    public boolean setTravelSetUnavailable(int sn) {
        TravelSetDO travelSetDO = session.get(TravelSetDO.class, sn);
        travelSetDO.setAvailable(0);
        return true;
    }

}
