package a592070.test.dao;

import a592070.dao.RestaurantViewDAO;
import a592070.pojo.RestaurantVO;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.HibernateUtil;

import java.util.List;

public class RestaurantViewDAOTest {
    static RestaurantViewDAO dao;
    static Session session;

    @BeforeClass
    public static void init(){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        dao = new RestaurantViewDAO(session);
    }
    @AfterClass
    public static void close(){
        session.getTransaction().commit();
        session.close();
        HibernateUtil.closeSessionFactory();
    }

    @Test
    public void getEleTest(){
        RestaurantVO ele = dao.getEle(66);
        System.out.println(ele);
    }
    @Test
    public void listEleTest(){
//        List<RestaurantVO> list = dao.listEle();
        List<RestaurantVO> list = dao.listEle("中");
        System.out.println(list);
    }
    @Test
    public void listEleByRownumTest(){
//        List<RestaurantVO> list = dao.listEleByRownum(5,10);
        List<RestaurantVO> list = dao.listEleByRownum(0,10);
        System.out.println(list.size());
        list = dao.listEleByRownum(11,10);
        System.out.println(list.size());
    }
    @Test
    public void getRestaurantRegionSizeTest(){
        int i = dao.getRestaurantRegionSize("中");
        System.out.println(i);
    }
}
