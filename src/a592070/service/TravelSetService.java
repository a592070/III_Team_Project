package a592070.service;

import a592070.dao.CarViewDAO;
import a592070.dao.HotelViewDAO;
import a592070.dao.RestaurantViewDAO;
import a592070.pojo.CarVO;
import a592070.pojo.HotelVO;
import a592070.pojo.RestaurantVO;
import controller.ConnectionPool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TravelSetService {
    private CarViewDAO carViewDAO;
    private HotelViewDAO hotelViewDAO;
    private RestaurantViewDAO restaurantViewDAO;

    public TravelSetService() {
        try {
            carViewDAO = new CarViewDAO(ConnectionPool.LOADING_WITH_SERVER);
            hotelViewDAO = new HotelViewDAO(ConnectionPool.LOADING_WITH_SERVER);
            restaurantViewDAO = new RestaurantViewDAO(ConnectionPool.LOADING_WITH_SERVER);
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


}
