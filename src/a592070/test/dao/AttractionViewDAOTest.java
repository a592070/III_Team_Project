package a592070.test.dao;

import a592070.dao.AttractionViewDAO;
import a592070.pojo.AttractionVO;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.HibernateUtil;

import java.util.List;

public class AttractionViewDAOTest {
    static Session session;
    static AttractionViewDAO dao;
    @BeforeClass
    public static void init(){
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        dao = new AttractionViewDAO(session);
    }
    @AfterClass
    public static void close(){
        session.getTransaction().commit();
        session.close();
        HibernateUtil.closeSessionFactory();
    }

    @Test
    public void getSizeTest(){
        int size = dao.getSize();
        System.out.println(size);
    }
    @Test
    public void getAttractionTest(){
        AttractionVO attraction = dao.getAttraction(33);
        System.out.println(attraction);
    }
    @Test
    public void listAttractionLikeTest(){
        List<AttractionVO> list = dao.listAttractionLike(5, 10, "臺中");
        System.out.println(list);
    }
    @Test
    public void getAttractionKeyWordsSizeTest(){
        int i = dao.getAttractionKeyWordsSize("北");
        System.out.println(i);
    }
    @Test
    public void getAttractionRegionSizeTest(){
        int i = dao.getAttractionRegionSize("北");
        System.out.println(i);
    }
    @Test
    public void listAttractionByRownumTest(){
        List<AttractionVO> list = dao.listAttractionByRownum(5, 10, "臺中");
        System.out.println(list);
    }

}
