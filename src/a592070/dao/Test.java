package a592070.dao;

import a592070.pojo.AttractionDO;
import a592070.vo.AttractionsInfoVO;
import controller.ConnectionPool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void testListInfoVO(){
        try {
            List<AttractionsInfoVO> attractionsInfoVOS = new AttractionsDAO(ConnectionPool.LOADING_WITHOUT_SERVER).listInfoVO();
            System.out.println(attractionsInfoVOS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @org.junit.Test
    public void testDAO() throws IOException, SQLException {
        AttractionDAO attractionDAO = new AttractionDAO();
//        AttractionDO ele = attractionDAO.getEle("name", "大棟山");
//        System.out.println(ele);
        System.out.println(attractionDAO.listEleLike("巧克力"));
    }
}
