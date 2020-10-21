package asx54630.model;

import java.util.List;

import asx54630.model.HotelBean;

public interface IHotelBeanService  {
	public HotelBean select(int id);
	public List<HotelBean> selectAll();
}
