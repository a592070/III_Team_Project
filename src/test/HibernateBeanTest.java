package test;

import a592070.dao.TravelSetDAO;
import a592070.pojo.*;
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
    public void testTravelSetSelect(){
        Session currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            currentSession.beginTransaction();

//            TravelSetDO travelSetDO = currentSession.get(TravelSetDO.class, 142);
            TravelSetDO travelSet = new TravelSetDAO(currentSession).getTravelSetByID(142);
            System.out.println(travelSet);

            currentSession.getTransaction().commit();
        }catch (Exception e){
            currentSession.getTransaction().rollback();
        }
        currentSession.close();
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
}
