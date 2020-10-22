package iring29.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class RestaurantDAO {
	private Session session;

	public RestaurantDAO(Session session) {
		this.session = session;
	}

	// search how many Restaurant
	public Long numRestaurant(String name) {
		Query query = session.createQuery("select count(*) from RestaurantBean where name like ?0");
		query.setParameter(0, "%" + name + "%");
		Long count = (Long)query.getSingleResult(); //得到Long
		return count;
		
	}
	
	// find multiple restaurant by restaurant name
		public List<RestaurantBean> findMulti_R(String name){
			Query query = session.createQuery("from RestaurantBean where name like ?0");
			query.setParameter(0, "%" + name + "%");
			List<RestaurantBean> rBeans = query.list();
			return rBeans;
		}

		// find multiple restaurant by restaurant name & region
		public List<RestaurantBean> findMulti_Name_Region(String name, String region) {
			Query query = session.createQuery("from RestaurantBean where name like ?0 and region = ?1");
			query.setParameter(0, "%" + name + "%");
			query.setParameter(1, region);
			List<RestaurantBean> rBeans = query.getResultList();
			return rBeans;

		}
		
	// find specific restaurant by restaurant name
	public RestaurantBean findRestaurant(String name) {
		Query query = session.createQuery("from RestaurantBean where name = ?0");
		query.setParameter(0, name);
		RestaurantBean obj = (RestaurantBean) query.uniqueResult();
		return obj;
	}
	
	// find specific region
		public List<RestaurantBean> findRegion(String region){
			Query query = session.createQuery("from RestaurantBean where region = ?0");
			query.setParameter(0, region);
			List <RestaurantBean> rBeans = query.list();
			return rBeans;
		}

	// fail
	// insert data for Restaurant
	public RestaurantBean createRestauran(RestaurantBean rBean) {
		RestaurantBean result = session.get(RestaurantBean.class, rBean.getR_sn());

		if (result == null) {
			session.save(rBean);
			return rBean;
		}
		return null;
	}
	
	//not yet try
	//set RestaurantBean for display HP
		public RestaurantBean Restaurant_HP(String username) {
			Query query = session.createQuery("from RestaurantBean where account = ?0");
			query.setParameter(0, username);
			RestaurantBean rBean = (RestaurantBean)query.uniqueResult();
			return rBean;
			
		}

	

}
