package iring29;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import controller.ConnectionPool;
import iring29.bean.RestaurantBean;

public class RestaurantDAO {
	private Connection conn;
	private DataSource ds;
	private String sql;
	
	//constructor
	public RestaurantDAO(int dataSourceType) throws IOException {
		ds = ConnectionPool.getDataSource(dataSourceType);
	}
	
	//find specific restaurant
	public RestaurantBean findRestaurant(String name) throws SQLException {
		try {
		sql = "select * from restaurant where name = ?";
		conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		pstmt.clearBatch();
		RestaurantBean restaurantdata = new RestaurantBean();
		while(rs.next()) {
			name = rs.getString("NAME");
			String address = rs.getString("ADDRESS");
			String opentime = rs.getString("OPENTIME");
			String description = rs.getString("DESCRIPTION");
			String transportation = rs.getString("TRANSPORTATION");
			String type = rs.getString("TYPE");
			BigDecimal rating = rs.getBigDecimal("RATING");
			String region = rs.getString("REGION");
			String tel= rs.getString("TEL");
			byte[] picture = rs.getBytes("PICTURE");
			String serviceinfo = rs.getString("SERVICEINFO");
			String booking_id = rs.getString("BOOKING_ID");
			
			restaurantdata = new RestaurantBean(name, address, opentime, description, transportation,
					type, rating, region, tel, picture, serviceinfo,booking_id);
		}
		rs.close();
		pstmt.close();
		return restaurantdata;
		
		}catch (Exception e) {
			System.err.println("尋找部門資料時發生錯誤:" + e);
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}
	
	
	public RestaurantBean findRegion(String region) {
		return null;
		
	}
	
	
}
