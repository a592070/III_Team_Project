package asx54630.model;

import asx54630.model.IHotelBeanService;


import java.util.List;

import org.hibernate.Session;

import asx54630.model.HotelDAO;

public class HotelBeanService implements IHotelBeanService {

	private HotelDAO hDao;
	
	public HotelBeanService (Session session) {
		hDao = new HotelDAO(session);
	}


	
	@Override
	public List<HotelBean> selectAll() {
		
		return hDao.selectAll();
	}



	@Override
	public HotelBean select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
