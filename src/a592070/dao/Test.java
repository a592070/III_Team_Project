package a592070.dao;

import a592070.pojo.*;
import a592070.vo.AttractionsInfoVO;
import controller.ConnectionPool;
import utils.IOUtils;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {


    @org.junit.Test
    public void testDAO() throws IOException, SQLException {
        AttractionDAO attractionDAO = new AttractionDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//        AttractionDO ele = attractionDAO.getEle("name", "大棟山");
        AttractionDO ele = attractionDAO.getAttraction(100);
        System.out.println(ele);
//        System.out.println(attractionDAO.listEleLike("巧克力"));


    }

    @org.junit.Test
    public void ioUtilTest() throws IOException {
        String file = "resources/第三組分工.jpg";
        String dest = "resources/copy.jpg";
//        byte[] files = IOUtils.toByteArray(ConnectionPool.class.getClassLoader().getResourceAsStream("第三組分工.jpg"));
        byte[] files = IOUtils.toByteArray(new FileInputStream(file));

        OutputStream out = new FileOutputStream(dest);
        out.write(files);
        out.close();
    }

    @org.junit.Test
    public void travelSetDAOTest() throws IOException, SQLException {
        TravelSetDAO travelSetDAO = new TravelSetDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//        List<TravelSetDO> list = travelSetDAO.listTravelSet();
//        for (TravelSetDO travelSetDO : list) {
//            System.out.println(travelSetDO.getListTravelHotel());
//        }
//        System.out.println(list);

//        List<TravelEleRestaurantDO> rSet = travelSetDAO.getRestaurantSetByID(1);
//        System.out.println(rSet);

//        List<TravelSetDO> system = travelSetDAO.listTravelSet("system");
//        for (TravelSetDO travelSetDO : system) {
//            System.out.println(travelSetDO);
//        }

//        List<TravelEleAttractionDO> listA = travelSetDAO.getAttractionSetByID(4);
//        System.out.println(listA);
//
//        List<TravelEleCarDO> listC = travelSetDAO.getCarSetByID(4);
//        System.out.println(listC);
//
//        List<TravelEleHotelDO> listH = travelSetDAO.getHotelSetByID(4);
//        System.out.println(listH);
//
//        List<TravelEleRestaurantDO> listR = travelSetDAO.getRestaurantSetByID(4);
//        System.out.println(listR);

//        TravelSetDO travelSet = travelSetDAO.getTravelSetByID(4);
//        travelSet.setDescription("豪華浪漫溫馨之旅");
//        travelSetDAO.updateTravelSet(travelSet);
        HotelViewDAO hotelViewDAO = new HotelViewDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
        CarViewDAO carViewDAO = new CarViewDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
        RestaurantViewDAO restaurantViewDAO = new RestaurantViewDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
        AttractionDAO attractionDAO = new AttractionDAO(ConnectionPool.LOADING_WITHOUT_SERVER);

//        HotelVO ele = hotelViewDAO.getEle(3);
//        TravelEleHotelDO travelEleHotelDO = new TravelEleHotelDO();
//        travelEleHotelDO.setHotel(ele);
//        travelSet.getListTravelHotel().add(travelEleHotelDO);
//        travelSetDAO.updateTravelSet(travelSet);

//        AttractionDO ele1 = attractionDAO.getEle(501);
//        CarVO ele2 = carViewDAO.getEle(33);
//        HotelVO ele3 = hotelViewDAO.getEle(4);
//        RestaurantVO ele4 = restaurantViewDAO.getEle(77);
//
//        TravelSetDO travelSetDO = new TravelSetDO();
//        TravelEleAttractionDO travelEle1 = new TravelEleAttractionDO();
//        TravelEleCarDO travelEle2 = new TravelEleCarDO();
//        TravelEleHotelDO travelEle3 = new TravelEleHotelDO();
//        TravelEleRestaurantDO travelEle4 = new TravelEleRestaurantDO();
//
//        travelEle1.setAttraction(ele1);
//        travelEle2.setCar(ele2);
//        travelEle3.setHotel(ele3);
//        travelEle4.setRestaurant(ele4);
//
//        travelSetDO.addAttraction(travelEle1);
//        travelSetDO.addCar(travelEle2);
//        travelSetDO.addHotel(travelEle3);
//        travelSetDO.addRestaurant(travelEle4);
//
//        travelSetDO.setDescription("美麗新世界體驗行");
//        travelSetDO.setCreatedUser("gaga");
//
//        boolean b = travelSetDAO.addTravelSet(travelSetDO);
//        System.out.println(b);

        List<TravelSetDO> list = travelSetDAO.listTravelSet("gaga");
        list.forEach(ele -> System.out.println(ele));
    }

    @org.junit.Test
    public void someTest(){
        List<String> list = new ArrayList<>();
        for (String s : list) {
            System.out.println(s);
        }
    }

    @org.junit.Test
    public void carViewTest() throws IOException, SQLException {
        CarViewDAO carViewDAO = new CarViewDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//        List<CarVO> list = carViewDAO.listEle();
//        System.out.println(list);

        CarVO ele = carViewDAO.getEle(10);
        System.out.println(ele);

        ele = carViewDAO.getEle("price", 2380);
        System.out.println(ele);

        List<CarVO> list = carViewDAO.listEleByRownum(10, 25);
        System.out.println(list);
    }

    @org.junit.Test
    public void hotelViewTest() throws IOException, SQLException {
        HotelViewDAO hotelViewDAO = new HotelViewDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
        HotelVO ele = hotelViewDAO.getEle(5);
        System.out.println(ele);

        ele = hotelViewDAO.getEle("name", "錦棧商旅");
        System.out.println(ele);

        List<HotelVO> list = hotelViewDAO.listEleByRownum(2, 10);
        System.out.println(list);

        list = hotelViewDAO.listEle();
        System.out.println(list);
    }

    @org.junit.Test
    public void restaurantViewTest() throws IOException, SQLException {
        RestaurantViewDAO restaurantViewDAO = new RestaurantViewDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
        RestaurantVO ele = restaurantViewDAO.getEle(33);
        System.out.println(ele);

        ele = restaurantViewDAO.getEle("name", "山鯨燒肉");
        System.out.println(ele);

        List<RestaurantVO> list = restaurantViewDAO.listEleByRownum(11, 13);
        System.out.println(list);
        System.out.println(list.size());

        list = restaurantViewDAO.listEle();
        System.out.println(list.size());
    }
}
