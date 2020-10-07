package a592070.dao;

import a592070.pojo.AttractionDO;
import a592070.pojo.TravelEleRestaurantDO;
import a592070.pojo.TravelSetDO;
import a592070.vo.AttractionsInfoVO;
import controller.ConnectionPool;
import utils.IOUtils;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void testListInfoVO(){
        try {
            List<AttractionsInfoVO> attractionsInfoVOS = new AttractionsDAO(ConnectionPool.LOADING_WITHOUT_SERVER).listInfoVO();
            System.out.println(attractionsInfoVOS);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testDAO() throws IOException, SQLException {
        AttractionDAO attractionDAO = new AttractionDAO();
//        AttractionDO ele = attractionDAO.getEle("name", "大棟山");
//        System.out.println(ele);
        System.out.println(attractionDAO.listEleLike("巧克力"));


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
        TravelSetDAO travelSetDAO = new TravelSetDAO();
//        List<TravelSetDO> list = travelSetDAO.listTravelSet();
//        for (TravelSetDO travelSetDO : list) {
//            System.out.println(travelSetDO.getListTravelHotel());
//        }
//        System.out.println(list);

        List<TravelEleRestaurantDO> rSet = travelSetDAO.getRestaurantSetByID(1);
        System.out.println(rSet);
    }
}
