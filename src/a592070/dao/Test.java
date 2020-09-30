package a592070.dao;

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
}
