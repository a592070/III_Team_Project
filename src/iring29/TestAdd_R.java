package iring29;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import controller.ConnectionPool;
import iring29.model.RestaurantBean;
import iring29.model.RestaurantDAO;

public class TestAdd_R {

	public static void main(String[] args) throws SQLException, IOException {
		
		RestaurantBean rBean = new RestaurantBean();
		rBean.setName("AB"); //餐廳名稱
		rBean.setAddress("test"); //餐廳地址
		rBean.setOpentime("test"); //餐廳營業時間
		rBean.setDescription("test"); //餐廳描述
		rBean.setTransportation("test"); //餐廳交通方式
		rBean.setType("test"); //餐廳類型
		rBean.setRating(BigDecimal.ZERO); //rating初始值設為0
		rBean.setPicture("test"); //餐廳照片，url格式
		rBean.setServiceinfo("test"); //餐廳用餐訊息
		rBean.setAccount("Irene"); // session中的username(account)
		
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
		restaurantDAO.createRestaurant(rBean);
	}

}
