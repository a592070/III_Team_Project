package asx54630.model;

import java.math.BigDecimal;
import java.util.List;

import asx54630.model.HotelBean;

public interface IHotelBeanService  {
	public HotelBean hotelDetail(BigDecimal sn);
	public HotelBean hotelHomePage(String account);
	public List<HotelBean> selectAll(String name, String region, String type);
	public HotelBean insert(HotelBean bean);
}
