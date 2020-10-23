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
    private Session session;

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
        String hql = "from TravelSetDO where createdUser=?1 and available=?2 order by priority desc ";
        Query<TravelSetDO> query = session.createQuery(hql, TravelSetDO.class);
        query.setParameter(1, created);
        query.setParameter(2, available);

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
//        session.beginTransaction();
        String hql = "from TravelSetDO where sn=?1 and available=?2 order by sn";
        Query<TravelSetDO> query = session.createQuery(hql, TravelSetDO.class);
        query.setParameter(1, id);
        query.setParameter(2, available);

        TravelSetDO travelSetDO = query.uniqueResultOptional().orElse(null);
        if(travelSetDO != null){
            travelSetDO.setTravelAttractions(getAttractionSetByID(id));
            travelSetDO.setTravelCars(getCarSetByID(id));
            travelSetDO.setTravelHotels(getHotelSetByID(id));
            travelSetDO.setTravelRestaurants(getRestaurantSetByID(id));
        }
//        session.getTransaction().commit();
        return travelSetDO;
    }
    public List<TravelEleAttractionDO> getAttractionSetByID(int id){
        String hql = "from TravelEleAttractionDO where travelSetDO.sn = ?1";
        Query<TravelEleAttractionDO> query = session.createQuery(hql, TravelEleAttractionDO.class);
        query.setParameter(1, id);

        return query.list();
    }
    public List<TravelEleCarDO> getCarSetByID(int id) {
        String hql = "from TravelEleCarDO where travelSetDO.id = ?1";
        Query<TravelEleCarDO> query = session.createQuery(hql, TravelEleCarDO.class);
        query.setParameter(1, id);

        return query.list();
    }
    public List<TravelEleHotelDO> getHotelSetByID(int id) {
        String hql = "from TravelEleHotelDO where travelSetDO.id = ?1";
        Query<TravelEleHotelDO> query = session.createQuery(hql, TravelEleHotelDO.class);
        query.setParameter(1, id);

        return query.list();
    }
    public List<TravelEleRestaurantDO> getRestaurantSetByID(int id) {
        String hql = "from TravelEleRestaurantDO where travelSetDO.id = ?1";
        Query<TravelEleRestaurantDO> query = session.createQuery(hql, TravelEleRestaurantDO.class);
        query.setParameter(1, id);

        return query.list();
    }


    public boolean addTravelSet(TravelSetDO travelSetDO){
        boolean flag = false;
        try {
            travelSetDO.setAvailable(1);
            session.save(travelSetDO);
            addTravelEleA(travelSetDO);
            addTravelEleC(travelSetDO);
            addTravelEleH(travelSetDO);
            addTravelEleR(travelSetDO);

            flag = true;
        }catch (Exception e){
            throw e;
        }
        return flag;
    }

    private void addTravelEleA(TravelSetDO travelSetDO) {
        for (TravelEleAttractionDO ele : travelSetDO.getTravelAttractions()) {
            session.save(ele);
        }
    }
    private void delTravelEleA(TravelSetDO travelSetDO){
        String hql = "delete TravelEleAttractionDO where travelSetDO.sn = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, travelSetDO.getSn());
        query.executeUpdate();
    }

    private void addTravelEleC(TravelSetDO travelSetDO) {
        String hql = "delete TravelEleCarDO where travelSetDO.sn = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, travelSetDO.getSn());
        query.executeUpdate();

        for (TravelEleCarDO ele : travelSetDO.getTravelCars()) {
            session.save(ele);
        }
    }
    private void delTravelEleC(TravelSetDO travelSetDO) {
        String hql = "delete TravelEleCarDO where travelSetDO.sn = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, travelSetDO.getSn());
        query.executeUpdate();
    }

    private void addTravelEleH(TravelSetDO travelSetDO) {
        String hql = "delete TravelEleHotelDO where travelSetDO.sn = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, travelSetDO.getSn());
        query.executeUpdate();

        for (TravelEleHotelDO ele : travelSetDO.getTravelHotels()) {
            session.save(ele);
        }
    }
    private void delTravelEleH(TravelSetDO travelSetDO) {
        String hql = "delete TravelEleHotelDO where travelSetDO.sn = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, travelSetDO.getSn());
        query.executeUpdate();
    }

    private void addTravelEleR(TravelSetDO travelSetDO) {
        String hql = "delete TravelEleRestaurantDO where travelSetDO.sn = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, travelSetDO.getSn());
        query.executeUpdate();

        for (TravelEleRestaurantDO ele : travelSetDO.getTravelRestaurants()) {
            session.save(ele);
        }
    }
    private void delTravelEleR(TravelSetDO travelSetDO) {
        String hql = "delete TravelEleRestaurantDO where travelSetDO.sn = ?1";
        Query query = session.createQuery(hql);
        query.setParameter(1, travelSetDO.getSn());
        query.executeUpdate();
    }

    public boolean updateTravelSet(TravelSetDO travelSetDO) {
        boolean flag = false;
        try {
            travelSetDO.setAvailable(1);
            session.merge(travelSetDO);

            delTravelEleA(travelSetDO);
            delTravelEleC(travelSetDO);
            delTravelEleH(travelSetDO);
            delTravelEleR(travelSetDO);

            addTravelEleA(travelSetDO);
            addTravelEleC(travelSetDO);
            addTravelEleH(travelSetDO);
            addTravelEleR(travelSetDO);
            flag = true;
        }catch (Exception e){
            throw e;
        }
        return flag;
    }

    public boolean setTravelSetUnavailable(int sn) {
        boolean flag = false;
        try {
            TravelSetDO travelSetDO = session.get(TravelSetDO.class, sn);
            travelSetDO.setAvailable(0);

            flag = true;
        }catch (Exception e){
            throw e;
        }
        return flag;
    }
}
