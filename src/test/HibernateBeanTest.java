package test;

import a592070.dao.AttractionViewDAO;
import a592070.dao.TravelSetDAO;
import a592070.pojo.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import rambo0021.model.AccountBean;
import utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

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

//        TravelEleAttractionDO travelEleAttractionDO = currentSession.get(TravelEleAttractionDO.class, 87);
//        System.out.println(travelEleAttractionDO);
//        System.out.println(travelEleAttractionDO.getTravelSetDO());
        Query<TravelEleAttractionDO> query = currentSession.createQuery("from TravelEleAttractionDO where travelSetDO.sn=21", TravelEleAttractionDO.class);
        query.list().forEach(ele -> System.out.println(ele));

        currentSession.getTransaction().commit();
        currentSession.close();
        HibernateUtil.closeSessionFactory();
    }
    @Test
    public void testTravelSetSelect(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

//            TravelSetDO travelSet = session.get(TravelSetDO.class, 21);
            TravelSetDO travelSet = new TravelSetDAO(session).getTravelSetByID(21);
            System.out.println(travelSet);
//            travelSet.getTravelAttractions2().forEach(ele -> System.out.println(ele));
//            travelSet.getTravelRestaurants2().forEach(ele -> System.out.println(ele));
//            travelSet.getTravelCars2().forEach(ele -> System.out.println(ele));
//            travelSet.getTravelHotels2().forEach(ele -> System.out.println(ele));

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        HibernateUtil.closeSessionFactory();
    }
    @Test
    public void testTravelUpdate(){
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            currentSession.beginTransaction();

            TravelSetDO travelSetDO = currentSession.get(TravelSetDO.class, 204);
            System.out.println(travelSetDO);

            travelSetDO.getTravelCars2().clear();

            TravelEleCarDO eleC = new TravelEleCarDO();
            eleC.setCar(currentSession.get(CarVO.class, 5));
            eleC.setTravelSetDO(travelSetDO);
            travelSetDO.getTravelCars2().add(eleC);

            TravelEleHotelDO eleH = new TravelEleHotelDO();
            eleH.setHotel(currentSession.get(HotelVO.class, 2));
            eleH.setTravelSetDO(travelSetDO);

            travelSetDO.getTravelHotels2().add(eleH);

            currentSession.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            currentSession.getTransaction().rollback();
        }
        currentSession.close();
        HibernateUtil.closeSessionFactory();
    }
    @Test
    public void testTravelInsert(){
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            currentSession.beginTransaction();

            TravelSetDO travelSetDO = new TravelSetDO();
            travelSetDO.setCreatedUser("gaga1");
            travelSetDO.setDescription("好好玩，快樂丸");
            travelSetDO.setName("好好玩");

            TravelEleCarDO eleC = new TravelEleCarDO();
            eleC.setCar(currentSession.get(CarVO.class, 1));
//            eleC.setTravelId(travelSetDO.getSn());
            travelSetDO.getTravelCars2().add(eleC);
            eleC.setTravelSetDO(travelSetDO);

            TravelEleAttractionDO eleA = new TravelEleAttractionDO();
            eleA.setAttraction(currentSession.get(AttractionVO.class, 999));
//            eleA.setTravelId(travelSetDO.getSn());
            travelSetDO.getTravelAttractions2().add(eleA);
            eleA.setTravelSetDO(travelSetDO);

            System.out.println(travelSetDO);
//            currentSession.save(travelSetDO);

            currentSession.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            currentSession.getTransaction().rollback();
        }
        currentSession.close();
        HibernateUtil.closeSessionFactory();
    }


    @Test
    public void testDao1(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            TravelSetDAO travelSetDAO = new TravelSetDAO(session);
            TravelSetDO travelSetDO = new TravelSetDO();
            travelSetDO.setCreatedUser("gaga1");
            travelSetDO.setDescription("好好玩，快樂丸");
            travelSetDO.setName("好好玩");

            TravelEleCarDO eleC1 = new TravelEleCarDO();
            eleC1.setCar(session.get(CarVO.class, 1));
            travelSetDO.getTravelCars().add(eleC1);
            eleC1.setTravelSetDO(travelSetDO);

            TravelEleCarDO eleC2 = new TravelEleCarDO();
            eleC2.setCar(session.get(CarVO.class, 2));
            travelSetDO.getTravelCars().add(eleC2);
            eleC2.setTravelSetDO(travelSetDO);

            TravelEleAttractionDO eleA = new TravelEleAttractionDO();
            eleA.setAttraction(session.get(AttractionVO.class, 999));
            travelSetDO.getTravelAttractions().add(eleA);
            eleA.setTravelSetDO(travelSetDO);

            travelSetDAO.addTravelSet(travelSetDO);

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        HibernateUtil.closeSessionFactory();
    }
    @Test
    public void testDao2(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            TravelSetDAO travelSetDAO = new TravelSetDAO(session);

            TravelSetDO travelSet = travelSetDAO.getTravelSetByID(61);


            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        HibernateUtil.closeSessionFactory();
    }

    @Test
    public void testMultipleBag(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String hql = "select\n" +
                    "        travelsetd0_.sn as sn1_10_0_,\n" +
                    "        travelsetd0_.available as available2_10_0_,\n" +
                    "        travelsetd0_.CREATED_TIME as created_time3_10_0_,\n" +
                    "        travelsetd0_.CREATED as created4_10_0_,\n" +
                    "        travelsetd0_.description as description5_10_0_,\n" +
                    "        travelsetd0_.name as name6_10_0_,\n" +
                    "        travelsetd0_.priority as priority7_10_0_,\n" +
                    "        travelsetd0_.UPDATE_TIME as update_time8_10_0_,\n" +
                    "        travelattr1_.TRAVEL_ID as travel_id4_6_1_,\n" +
                    "        travelattr1_.sn as sn1_6_1_,\n" +
                    "        travelattr1_.sn as sn1_6_2_,\n" +
                    "        travelattr1_.A_ID as a_id3_6_2_,\n" +
                    "        travelattr1_.time as time2_6_2_,\n" +
                    "        travelattr1_.TRAVEL_ID as travel_id4_6_2_,\n" +
                    "        travelcars2_.TRAVEL_ID as travel_id4_7_3_,\n" +
                    "        travelcars2_.sn as sn1_7_3_,\n" +
                    "        travelcars2_.sn as sn1_7_4_,\n" +
                    "        travelcars2_.C_ID as c_id3_7_4_,\n" +
                    "        travelcars2_.time as time2_7_4_,\n" +
                    "        travelcars2_.TRAVEL_ID as travel_id4_7_4_,\n" +
                    "        travelhote3_.TRAVEL_ID as travel_id4_8_5_,\n" +
                    "        travelhote3_.sn as sn1_8_5_,\n" +
                    "        travelhote3_.sn as sn1_8_6_,\n" +
                    "        travelhote3_.H_ID as h_id3_8_6_,\n" +
                    "        travelhote3_.time as time2_8_6_,\n" +
                    "        travelhote3_.TRAVEL_ID as travel_id4_8_6_,\n" +
                    "        travelrest4_.TRAVEL_ID as travel_id4_9_7_,\n" +
                    "        travelrest4_.sn as sn1_9_7_,\n" +
                    "        travelrest4_.sn as sn1_9_8_,\n" +
                    "        travelrest4_.R_ID as r_id3_9_8_,\n" +
                    "        travelrest4_.time as time2_9_8_,\n" +
                    "        travelrest4_.TRAVEL_ID as travel_id4_9_8_ \n" +
                    "    from\n" +
                    "        TRAVEL_SET travelsetd0_ \n" +
                    "    left outer join\n" +
                    "        TRAVEL_ELE_A travelattr1_ \n" +
                    "            on travelsetd0_.sn=travelattr1_.TRAVEL_ID \n" +
                    "    left outer join\n" +
                    "        TRAVEL_ELE_C travelcars2_ \n" +
                    "            on travelsetd0_.sn=travelcars2_.TRAVEL_ID \n" +
                    "    left outer join\n" +
                    "        TRAVEL_ELE_H travelhote3_ \n" +
                    "            on travelsetd0_.sn=travelhote3_.TRAVEL_ID \n" +
                    "    left outer join\n" +
                    "        TRAVEL_ELE_R travelrest4_ \n" +
                    "            on travelsetd0_.sn=travelrest4_.TRAVEL_ID \n" +
                    "    where\n" +
                    "        travelsetd0_.sn=21 or travelsetd0_.sn=41";

            System.out.println(session.createSQLQuery(hql).getResultList().size());


            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        HibernateUtil.closeSessionFactory();
    }

    @Test
    public void testAccount(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            AccountBean rambo001 = session.get(AccountBean.class, "rambo001");
            InputStream input = rambo001.getPicture().getBinaryStream();
            OutputStream out = new FileOutputStream("resources/testPic.jpg");
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = input.read(buf)) != -1){
                out.write(buf, 0 ,len);
            }

            input.close();
            out.close();

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        HibernateUtil.closeSessionFactory();
    }

    @Test
    public void  testAttrView(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            AttractionViewDAO dao = new AttractionViewDAO(session);
            List<AttractionVO> list = dao.listAttractionLike(0, 100, "高雄");
            System.out.println(list);

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        session.close();
        HibernateUtil.closeSessionFactory();
    }
}
