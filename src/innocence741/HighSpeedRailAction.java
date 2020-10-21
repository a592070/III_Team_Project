package innocence741;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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
	
	
	public static void main(String[] args) throws SQLException {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		HighSpeedRailDAO highSpeedRailDAO = new HighSpeedRailDAO(session);
//		HighSpeedRailAction action1 = new HighSpeedRailAction();
//		action1.processQueryAll();
		
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();
	}

}
