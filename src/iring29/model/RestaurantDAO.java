package iring29.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class RestaurantDAO {

	private Session session;

	public RestaurantDAO(Session session) {
		this.session = session;
	}

	// find specific restaurant by restaurant name
	public RestaurantBean findRestaurant(String name) {
		Query query = session.createQuery("from RestaurantBean where name = ?0");
		query.setParameter(0, name);
		RestaurantBean obj = (RestaurantBean) query.uniqueResult();
		return obj;
	}

	// insert data for Restaurant
	public RestaurantBean createRestauran(RestaurantBean rBean) {
		RestaurantBean result = session.get(RestaurantBean.class, rBean.getR_sn());

		if (result == null) {
			session.save(rBean);
			return rBean;
		}
		return null;
	}
	
	// find specific region
		public List<RestaurantBean> findRegion(String region){
			Query<RestaurantBean> query = session.createQuery("select name, region, type from RestaurantBean where region = ?0", RestaurantBean.class);
			query.setParameter(0, region);
			List<RestaurantBean> rBeans = query.list();
			return rBeans;
			
		}
	

}
