package iring29;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.ConnectionPool;
import iring29.bean.RestaurantBean;

public class RestaurantDAO {
	private Connection conn;
	private DataSource ds;
	private String sql;
	private PreparedStatement pstmt;
	
	//constructor
	public RestaurantDAO(int dataSourceType) throws IOException {
		ds = ConnectionPool.getDataSource(dataSourceType);
	}
	
	//find specific restaurant
	public RestaurantBean findRestaurant(String name) throws SQLException {
		try {
		sql = "select * from restaurant where name like ?";
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%"+ name+"%");
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
			String picture = rs.getString("PICTURE");
			String serviceinfo = rs.getString("SERVICEINFO");
			String booking_id = rs.getString("BOOKING_ID");
			
			restaurantdata = new RestaurantBean(name, address, opentime, description, transportation,
					type, rating, region, picture, serviceinfo,booking_id);
		}
		rs.close();
		pstmt.close();
		return restaurantdata;
		
		}catch (Exception e) {
			System.err.println("尋找資料時發生錯誤:" + e);
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}
	
	//find specific region
	public List<RestaurantBean> findRegion(String region) throws SQLException {
		try {
			sql = "select name, region, type from restaurant where region = ?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, region);
			ResultSet rs = pstmt.executeQuery();
			pstmt.clearBatch();
			ArrayList<RestaurantBean> restaurantList = new ArrayList<>();
			while(rs.next()) {
				RestaurantBean restaurantdata = new RestaurantBean();
				restaurantdata.setName(rs.getString("NAME"));
				restaurantdata.setRegion(rs.getString("REGION"));
				restaurantdata.setType(rs.getString("TYPE"));
				
				restaurantList.add(restaurantdata);
			}
			rs.close();
			pstmt.close();
			return restaurantList;
		}catch (Exception e) {
			System.err.println("尋找資料時發生錯誤:" + e);
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
	}
	
	//update img
	public void updateimg(String url, String id) throws SQLException, FileNotFoundException {
		try {
		sql = "update restaurant set picture = ? where id = ?";
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, url);
		pstmt.setString(2, id);
		ResultSet rs = pstmt.executeQuery();
		System.out.println("start");
		conn.commit();
		pstmt.clearBatch();
		}catch (Exception e) {
			System.err.println("更新資料時發生錯誤:" + e);
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		
	}
	
	//delete restaurant
	public boolean deleteRestaurant(String id) throws SQLException {
		try {
		sql = "delete restaurant where r_id = ?";
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		pstmt.clearBatch();
		conn.commit();
		
		System.out.println("delete");
		rs.close();
		pstmt.close();
		}catch (Exception e) {
			System.err.println("刪除資料時發生錯誤:" + e);
			conn.rollback();
		
		}finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;
		
	}
}
