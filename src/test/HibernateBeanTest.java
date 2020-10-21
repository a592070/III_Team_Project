package test;

import a592070.pojo.CarVO;
import a592070.pojo.TravelEleAttractionDO;
import a592070.pojo.TravelSetDO;
import org.hibernate.Session;
import org.junit.Test;
import utils.HibernateUtil;

public class HibernateBeanTest {

    @Test
    public void testCarVO(){
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        currentSession.beginTransaction();
        CarVO carVO = currentSession.get(CarVO.class, 1);
        System.out.println(carVO);

        currentSession.getTransaction();
        currentSession.close();
        HibernateUtil.closeSessionFactory();
    }
    @Test
    public void testTravelEle(){
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        currentSession.beginTransaction();

        TravelEleAttractionDO travelEleAttractionDO = currentSession.get(TravelEleAttractionDO.class, 53);
        System.out.println(travelEleAttractionDO);


        currentSession.getTransaction().commit();
        currentSession.close();
        HibernateUtil.closeSessionFactory();
    }
    @Test
    public void testTravelInsert(){
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        currentSession.beginTransaction();

        TravelSetDO travelSetDO = currentSession.get(TravelSetDO.class, 142);
        System.out.println(travelSetDO);


        currentSession.getTransaction().commit();
        currentSession.close();
        HibernateUtil.closeSessionFactory();
    }
}
