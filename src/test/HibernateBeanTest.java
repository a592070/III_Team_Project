package test;

import a592070.dao.TravelSetDAO;
import a592070.pojo.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
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

//            TravelSetDO travelSetDO = currentSession.get(TravelSetDO.class, 142);
            TravelSetDO travelSet = new TravelSetDAO(session).getTravelSetByID(21);
            System.out.println(travelSet.getSn());
            travelSet.getTravelAttractions().forEach(ele -> System.out.println(ele));
            travelSet.getTravelRestaurants().forEach(ele -> System.out.println(ele));
            travelSet.getTravelCars().forEach(ele -> System.out.println(ele));
            travelSet.getTravelHotels().forEach(ele -> System.out.println(ele));

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

            travelSetDO.getTravelCars().clear();

            TravelEleCarDO eleC = new TravelEleCarDO();
            eleC.setCar(currentSession.get(CarVO.class, 3));
            eleC.setTravelSetDO(travelSetDO);
            travelSetDO.getTravelCars().add(eleC);

            TravelEleHotelDO eleH = new TravelEleHotelDO();
            eleH.setHotel(currentSession.get(HotelVO.class, 3));
            eleH.setTravelSetDO(travelSetDO);

            travelSetDO.getTravelHotels().add(eleH);

            currentSession.getTransaction().commit();
        }catch (Exception e){
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
            travelSetDO.getTravelCars().add(eleC);
            eleC.setTravelSetDO(travelSetDO);

            TravelEleAttractionDO eleA = new TravelEleAttractionDO();
            eleA.setAttraction(currentSession.get(AttractionDO.class, 999));
//            eleA.setTravelId(travelSetDO.getSn());
            travelSetDO.getTravelAttractions().add(eleA);
            eleA.setTravelSetDO(travelSetDO);

            System.out.println(travelSetDO);
            currentSession.save(travelSetDO);

            currentSession.getTransaction().commit();
        }catch (Exception e){
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
            eleA.setAttraction(session.get(AttractionDO.class, 999));
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
}
