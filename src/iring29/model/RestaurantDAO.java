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
	public int numRestaurant(String name) {
		Query<Integer> query = session.createQuery("select CAST(count(*) as int) from RestaurantBean where name like ?0", Integer.class);
		System.out.println("start numRestaurant");
		query.setParameter(0, "%" + name + "%");

		/** change by a592070
		Query<Long> query = session.createQuery("select count(*) from RestaurantBean where name like ?1", Long.class);
		query.setParameter(1, "%" + name + "%");
		*/
		return query.uniqueResult().intValue();

	}

	// find multiple restaurant by restaurant name
	public List<RestaurantBean> findMulti_R(String name) {
		Query query = session.createQuery("from RestaurantBean where name like ?0");
		System.out.println("start findMulti_R");
		query.setParameter(0, "%" + name + "%");

		/** change by a592070
		Query<RestaurantBean> query = session.createQuery("from RestaurantBean where name like ?1", RestaurantBean.class);
		query.setParameter(1, "%" + name + "%");
		*/

		List<RestaurantBean> rBeans = query.list();
		return rBeans;
	}

	// find multiple restaurant by restaurant name & region
	public List<RestaurantBean> findMulti_Name_Region(String name, String region) {
		Query query = session.createQuery("from RestaurantBean where name like ?0 and region = ?1");
		System.out.println("findMulti_Name_Region");
		query.setParameter(0, "%" + name + "%");
		query.setParameter(1, region);

		/** change by a592070
		Query<RestaurantBean> query = session.createQuery("from RestaurantBean where name like ?1 and region = ?2", RestaurantBean.class);
		query.setParameter(1, "%" + name + "%");
		query.setParameter(2, region);
		 */

		List<RestaurantBean> rBeans = query.getResultList();
		return rBeans;

	}

	// find specific restaurant by restaurant name
	public RestaurantBean findRestaurant(String name) {
		Query query = session.createQuery("from RestaurantBean where name = ?0");
		System.out.println("start findRestaurant");
		query.setParameter(0, name);
		RestaurantBean obj = (RestaurantBean) query.uniqueResult();
		return obj;
		/** change by a592070
		 Query<RestaurantBean> query = session.createQuery("from RestaurantBean where name = ?1", RestaurantBean.class);
		 query.setParameter(1, name);
		 return query.uniqueResult();
		 */
	}

	// find specific region
	public List<RestaurantBean> findRegion(String region) {
		Query query = session.createQuery("from RestaurantBean where region = ?0");
		System.out.println("start findRegion");
		query.setParameter(0, region);
		List<RestaurantBean> rBeans = query.list();
		return rBeans;
		/** change by a592070
		Query<RestaurantBean> query = session.createQuery("from RestaurantBean where region = ?1", RestaurantBean.class);
		query.setParameter(1, region);
		return query.list();
		 */
	}

	// ok but not sure why
	// insert data for Restaurant
	public RestaurantBean createRestauran(RestaurantBean rBean) {
//		RestaurantBean result = session.get(RestaurantBean.class, rBean.getR_sn());

//		if (result == null) {
//			session.save(rBean);
//			return rBean;
//		}
		try {
			session.save(rBean);
			System.out.println("start createR");
			return rBean;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// set RestaurantBean for display HP
	public RestaurantBean Restaurant_HP(String username) {
		Query query = session.createQuery("from RestaurantBean where account = ?0");
		System.out.println("start Restaurant_HP");
		query.setParameter(0, username);
		RestaurantBean rBean = (RestaurantBean) query.uniqueResult();
		return rBean;
		/** change by a592070
		Query<RestaurantBean> query = session.createQuery("from RestaurantBean where accountBean.userName = ?1", RestaurantBean.class);
		query.setParameter(1, username);
		return query.uniqueResult();
		*/
	}

}
