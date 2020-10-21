package iring29.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utils.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		RestaurantDAO rDAO = new RestaurantDAO(session);
		RestaurantBean rBean = rDAO.findRestaurant("山鯨燒肉");
		System.out.println(rBean.getAddress());
		
		
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();

	}

}
