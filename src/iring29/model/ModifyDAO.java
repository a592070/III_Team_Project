package iring29.model;

import java.math.BigDecimal;
import org.hibernate.Session;


public class ModifyDAO {
	private Session session;

	public ModifyDAO(Session session) {
		this.session = session;
	}

	// modify location
	public boolean R_Address(String address, String transportation, BigDecimal r_sn) {
		RestaurantBean result = session.get(RestaurantBean.class, r_sn);
		if (result != null) {
			result.setAddress(address);
			result.setTransportation(transportation);
			return true;
		} else {
			return false;
		}
	}

	// modify Type
	public boolean R_Type(String serviceinfo, String type, BigDecimal r_sn) {
		RestaurantBean result = session.get(RestaurantBean.class, r_sn);
		if (result != null) {
			result.setServiceinfo(serviceinfo);
			result.setType(type);
			return true;
		} else {
			return false;
		}
	}

	// modify Info
	public boolean R_Info(String opentime, String description, BigDecimal r_sn) {
		RestaurantBean result = session.get(RestaurantBean.class, r_sn);
		if (result != null) {
			result.setOpentime(opentime);
			result.setDescription(description);
			return true;
		} else {
			return false;
		}

	}

	// modify Img
	public boolean R_Img(String picture, BigDecimal r_sn) {
		RestaurantBean result = session.get(RestaurantBean.class, r_sn);
		if (result != null) {
			result.setPicture(picture);
			return true;
		}else {
			return false;
		}
	}
		
}
