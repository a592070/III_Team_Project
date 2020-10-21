package test;

import a592070.pojo.CarVO;
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
}
