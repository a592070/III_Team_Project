package asx54630.model;

import asx54630.model.IHotelBeanService;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;

import asx54630.model.HotelDAO;

public class HotelBeanService implements IHotelBeanService {

	private HotelDAO hDao;

	public HotelBeanService(Session session) {
		hDao = new HotelDAO(session);
	}

	@Override
	public List<HotelBean> selectAll(String name, String region, String type) {

		return hDao.selectAll(name, region, type);
	}

	@Override
	public HotelBean hotelDetail(BigDecimal sn) {
		return hDao.hotelDetail(sn);
	}

	@Override
	public HotelBean hotelHomePage(String account) {
		return hDao.hotelHomePage(account);
	}

	@Override
	public HotelBean insert(HotelBean bean) {
		return hDao.insert(bean);
	}

}