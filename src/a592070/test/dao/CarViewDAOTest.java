package a592070.test.dao;

import a592070.dao.CarViewDAO;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.HibernateUtil;

public class CarViewDAOTest {
    static Session session;
    static CarViewDAO dao;
    @BeforeClass
    public static void init(){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        dao = new CarViewDAO(session);
    }
    @AfterClass
    public static void close(){
        session.getTransaction().commit();
        session.close();
        HibernateUtil.closeSessionFactory();
    }

    @Test
    public void getEleTest(){

    }
    @Test
    public void listEleTest(){

    }
    @Test
    public void listEleByRownumTest(){

    }
}
