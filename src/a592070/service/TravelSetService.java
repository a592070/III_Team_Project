package a592070.service;

import a592070.dao.*;
import a592070.pojo.*;
import controller.ConnectionPool;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TravelSetService {
    private CarViewDAO carViewDAO;
    private HotelViewDAO hotelViewDAO;
    private RestaurantViewDAO restaurantViewDAO;
    private AttractionDAO attractionDAO;
    private TravelSetDAO travelSetDAO;

    private Session session;

    public TravelSetService(Session session) {
        carViewDAO = new CarViewDAO(session);
        hotelViewDAO = new HotelViewDAO(session);
        restaurantViewDAO = new RestaurantViewDAO(session);
        attractionDAO = new AttractionDAO(session);
        travelSetDAO = new TravelSetDAO(session);
    }

    public TravelSetService() {
        try {
            carViewDAO = new CarViewDAO(ConnectionPool.LOADING_WITH_SERVER);
            hotelViewDAO = new HotelViewDAO(ConnectionPool.LOADING_WITH_SERVER);
            restaurantViewDAO = new RestaurantViewDAO(ConnectionPool.LOADING_WITH_SERVER);
            attractionDAO = new AttractionDAO(ConnectionPool.LOADING_WITH_SERVER);
            travelSetDAO = new TravelSetDAO(ConnectionPool.LOADING_WITH_SERVER);
        } catch (IOException e) {
            new RuntimeException("DAO 初始化錯誤\n"+e).printStackTrace();
        }
    }

    public List<CarVO> listCarVO() {
        return carViewDAO.listEle();
    }
    public List<HotelVO> listHotel() {
        return hotelViewDAO.listEle();
    }


    public List<RestaurantVO> listRestaurant(String region){
        return restaurantViewDAO.listEle(region);
    }

    public List<RestaurantVO> listRestaurant() {
        return listRestaurant(null);
    }

    public List<AttractionVO> listAttraction(int currentPage, int pageSize){
        return listAttraction(currentPage, pageSize, null);
    }

    public List<AttractionVO> listAttraction(int currentPage, int pageSize, String region){
        int start = pageSize*(currentPage-1)+1;
        int end = pageSize*currentPage;
        List<AttractionVO> list = new ArrayList<>();

        List<AttractionDO> listDO = attractionDAO.listAttractionByRownum(start, end, region);
        listDO.forEach(ele ->{
            AttractionVO vo = new AttractionVO();
            vo.setSn(ele.getSn());
            vo.setName(ele.getName());
            vo.setPicture(ele.getPicture());
            vo.setAddress(ele.getAddress());
            vo.setTicketInfo(ele.getTicketInfo());
            list.add(vo);
        });

        return list;
    }
    public int getTotalSize(){
        return attractionDAO.getSize();
    }
    public int getRegionLimitSize(String region){
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
