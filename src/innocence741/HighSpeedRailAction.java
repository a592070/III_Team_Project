package innocence741;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import innocence741.model.HighSpeedRail;
import innocence741.model.HighSpeedRailDAO;
import utils.HibernateUtil;

public class HighSpeedRailAction {
	
	private static Session session;


	public void processQueryAll() {
		
		Query<HighSpeedRail> query = session.createQuery("From HighSpeedRail",HighSpeedRail.class);
		List<HighSpeedRail> hsrlist = query.list();
		
		for(HighSpeedRail hBean:hsrlist) {
			System.out.println(hBean.getSnSchedule() + ":" + hBean.getIdHSR());
		}
	}
	
	
	public static void main(String[] args) throws SQLException, JsonProcessingException {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		String startPoint = "Nangang";
		String destination = "Miaoli";
		String departureTime = "7:00";
		
		
		HighSpeedRailDAO highSpeedRailDAO = new HighSpeedRailDAO(session);
		List<HighSpeedRail> list;
    	int price = 0;
    	highSpeedRailDAO.searchHSR(startPoint, destination, departureTime);
		list = highSpeedRailDAO.listHsrDO();
		price = highSpeedRailDAO.ticketPrice(startPoint, destination);
		ObjectMapper objectMapper = new ObjectMapper();
        String ujson = objectMapper.writeValueAsString(list);

        
        ujson = "[" + ujson + ",{\"price\" : " + price + "}]";
        System.out.println(ujson+"\n");
        System.out.println(list.size());
		
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
	}

}
