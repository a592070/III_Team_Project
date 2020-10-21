package asx54630.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import asx54630.model.HotelBean;


public class HotelDAO {

	private Session session;
	
	public HotelDAO(Session session) {
		this.session = session;
	}
	
	
	public HotelBean select(int id) { //查詢單筆
		return session.get(HotelBean.class, id);
	}
	
	public List<HotelBean> selectAll(){ //查詢多筆
		Query<HotelBean> query = session.createQuery("From HotelBean", HotelBean.class);
		List<HotelBean> list = query.list();
		return list;
	}

}
