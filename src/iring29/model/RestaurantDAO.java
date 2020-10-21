package iring29.model;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class RestaurantDAO {
	
	private Session session;

	public RestaurantDAO(Session session) {
		this.session = session;
	}
	
	// find specific restaurant by restaurant name
		public RestaurantBean findRestaurant(String name) {
			 Query query = session.createQuery("from restaurant where name ?");
			 query.setParameter(0, name);
			 RestaurantBean obj = (RestaurantBean)query.uniqueResult();
			 return obj;
		}

}
