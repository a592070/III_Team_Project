package a592070.service;

import a592070.dao.*;
import a592070.pojo.*;
import controller.ConnectionPool;

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
        List<CarVO> list = null;
        try {
            list = carViewDAO.listEle();
        } catch (SQLException e) {
            new RuntimeException("carViewDAO listEle()錯誤\n"+e).printStackTrace();
        }
        return list;
    }
    public List<HotelVO> listHotel() {
        List<HotelVO> list = null;
        try {
            list = hotelViewDAO.listEle();
        } catch (SQLException e) {
            new RuntimeException("hotelViewDAO listEle()錯誤\n"+e).printStackTrace();
        }
        return list;
    }
    public List<RestaurantVO> listRestaurant() {
        List<RestaurantVO> list = null;
        try {
            list = restaurantViewDAO.listEle();
        } catch (SQLException e) {
            new RuntimeException("restaurantViewDAO listEle()錯誤\n"+e).printStackTrace();
        }
        return list;
    }

    public List<AttractionVO> listAttraction(int currentPage, int pageSize){
        return listAttraction(currentPage, pageSize, null);
    }

    public List<AttractionVO> listAttraction(int currentPage, int pageSize, String region){
        int start = pageSize*(currentPage-1)+1;
        int end = pageSize*currentPage;
        List<AttractionVO> list = new ArrayList<>();
        try {
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
        } catch (SQLException e) {
            new RuntimeException("AttractionDAO listAttractionByRownum()錯誤\n"+e).printStackTrace();
        }
        return list;
    }
    public int getTotalSize(){
        int size=0;
        try {
            size = attractionDAO.getSize();
        } catch (SQLException e) {
            new RuntimeException("AttractionDAO getSize()錯誤\n"+e).printStackTrace();
        }
        return size;
    }
    public int getRegionLimitSize(String region){
        int size = 0;
        try {
            size = attractionDAO.getAttractionRegionSize(region);
        } catch (SQLException e) {
            new RuntimeException("AttractionDAO getAttractionRegionSize()錯誤\n"+e).printStackTrace();
        }
        return size;
    }

    public List<TravelSetDO> listTravelSet(String username){
        List<TravelSetDO> list = new ArrayList<>();
        try {
            list = travelSetDAO.listTravelSet(username);

        } catch (SQLException e) {
            new RuntimeException("travelSetDAO listTravelSet()錯誤\n"+e).printStackTrace();
        }
        return list;
    }
    public TravelSetDO getTravelSet(int sn){
        TravelSetDO travelSet = null;
        try {
            travelSet = travelSetDAO.getTravelSetByID(sn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travelSet;
    }
}
