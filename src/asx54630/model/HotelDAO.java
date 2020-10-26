package asx54630.model;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import asx54630.model.HotelBean;



public class HotelDAO {

	private Session session;
	
	public HotelDAO(Session session) {
		this.session = session;
	}
	
	
	public HotelBean hotelDetail(BigDecimal sn) { //查詢飯店詳細資料
		return session.get(HotelBean.class, sn);
	}
	
	public HotelBean hotelHomePage(String account) { //查詢飯店業者擁有的資料
		return session.get(HotelBean.class, account);
	}
	
	public List<HotelBean> selectAll(String name, String region, String type){ //查詢多筆
		Query<HotelBean> query = session.createQuery("From HotelBean WHERE NAME like ?0 and REGION like ?1 and TYPE like ?2", HotelBean.class);
		query.setParameter(0, "%" + name + "%");
		query.setParameter(1, "%" + region + "%");
		query.setParameter(2, "%" + type + "%");
		List<HotelBean> list = query.list();
		return list;
	}
	
	public HotelBean insert(HotelBean bean) { //新增
		HotelBean result = session.get(HotelBean.class, bean.getSN());
		
		if (result == null) {
			session.save(bean);
			return bean;
		}
		return null;
	}

}
