package a592070.test.service;

import a592070.pojo.RestaurantVO;
import a592070.service.TravelSetService;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.HibernateUtil;

import java.util.List;

public class TravelSetServiceTest {
    static Session session;
    static TravelSetService service;
    @BeforeClass
    public static void init(){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        service = new TravelSetService(session);
    }
    @AfterClass
    public static void close(){
        session.getTransaction().commit();
        session.close();
        HibernateUtil.closeSessionFactory();
    }

    @Test
    public void listRestaurantTest(){
        List<RestaurantVO> list = service.listRestaurant(1, 10);
        System.out.println(list.size());

        list = service.listRestaurant(2,10);
        System.out.println(list.size());
    }
}
