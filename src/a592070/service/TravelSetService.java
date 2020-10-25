package a592070.service;

import a592070.dao.*;
import a592070.pojo.*;
import org.hibernate.Session;

import java.util.List;

public class TravelSetService {
    private CarViewDAO carViewDAO;
    private HotelViewDAO hotelViewDAO;
    private RestaurantViewDAO restaurantViewDAO;
    private AttractionViewDAO attractionDAO;
    private TravelSetDAO travelSetDAO;

    private Session session;

    public TravelSetService() {
    }

    public TravelSetService(Session session) {
        carViewDAO = new CarViewDAO(session);
        hotelViewDAO = new HotelViewDAO(session);
        restaurantViewDAO = new RestaurantViewDAO(session);
        attractionDAO = new AttractionViewDAO(session);
        travelSetDAO = new TravelSetDAO(session);
    }
    public void setSession(Session session){
        carViewDAO = new CarViewDAO(session);
        hotelViewDAO = new HotelViewDAO(session);
        restaurantViewDAO = new RestaurantViewDAO(session);
        attractionDAO = new AttractionViewDAO(session);
        travelSetDAO = new TravelSetDAO(session);
    }

    public List<CarVO> listCarVO() {
        return carViewDAO.listEle();
    }
    public List<HotelVO> listHotel() {
        return hotelViewDAO.listEle();
    }

    public int getRestaurantSize(){
        return restaurantViewDAO.getSize();
    }
    public int getRestaurantSize(String region){
        return restaurantViewDAO.getRestaurantRegionSize(region);
    }
    public List<RestaurantVO> listRestaurant(int currentPage, int pageSize, String region){
        int start = pageSize*(currentPage-1)+1;
        return restaurantViewDAO.listEleByRownum(start, pageSize, region);
    }

    public List<RestaurantVO> listRestaurant(int currentPage, int pageSize) {
        return listRestaurant(currentPage, pageSize, "");
    }

    public List<AttractionVO> listAttraction(int currentPage, int pageSize){
        return listAttraction(currentPage, pageSize, "");
    }

    public List<AttractionVO> listAttraction(int currentPage, int pageSize, String region){
        int start = pageSize*(currentPage-1)+1;

        List<AttractionVO> listDO = attractionDAO.listAttractionByRownum(start, pageSize, region);
        return listDO;
    }
    public int getAttractionSize(){
        return attractionDAO.getSize();
    }
    public int getAttractionSize(String region){
        return attractionDAO.getAttractionRegionSize(region);
    }

    public List<TravelSetDO> listTravelSet(String username){
        return travelSetDAO.listTravelSet(username);
    }
    public TravelSetDO getTravelSet(int sn){
        return travelSetDAO.getTravelSetByID(sn);
    }

    public boolean addTravelSet(TravelSetDO travelSetDO){
        return travelSetDAO.addTravelSet(travelSetDO);
    }
    public boolean updateTravelSet(TravelSetDO travelSetDO){
        boolean flag = false;

        if(travelSetDAO.getTravelSetByID(travelSetDO.getSn()) == null){
            flag = travelSetDAO.addTravelSet(travelSetDO);
        }else{
            flag = travelSetDAO.updateTravelSet(travelSetDO);
        }

        return flag;
    }

    public boolean removeTravelSet(int sn){
        return travelSetDAO.setTravelSetUnavailable(sn);
    }
}
