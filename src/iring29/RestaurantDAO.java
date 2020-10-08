package iring29;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.ConnectionPool;
import iring29.bean.R_OrderBean;
import iring29.bean.RestaurantBean;
import pojo.OrderTableBean;

public class RestaurantDAO {
	private Connection conn;
	private DataSource ds;
	private String sql;
	private PreparedStatement pstmt;

	// constructor
	public RestaurantDAO(int dataSourceType) throws IOException {
		ds = ConnectionPool.getDataSource(dataSourceType);
	}

	// find specific restaurant by restaurant name
	public RestaurantBean findRestaurant(String name) throws SQLException {
//		String sql_count = "select count(*) where name like ?"; 
		sql = "select * from restaurant where name like ? ";		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			pstmt.clearBatch();
			RestaurantBean restaurantdata = new RestaurantBean();
			while (rs.next()) {
				BigDecimal r_sn = rs.getBigDecimal("R_SN");
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
				String account = rs.getString("ACCOUNT");

				restaurantdata = new RestaurantBean(r_sn, name, address, opentime, description, transportation, type,
						rating, region, picture, serviceinfo, booking_id, account);
			}
			rs.close();
			pstmt.close();
			return restaurantdata;

		} catch (Exception e) {
			System.err.println("尋找資料時發生錯誤:" + e);
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	// find specific region
	public List<RestaurantBean> findRegion(String region) throws SQLException {
		try {
			sql = "select name, region, type from restaurant where region = ?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, region);
			ResultSet rs = pstmt.executeQuery();
			pstmt.clearBatch();
			ArrayList<RestaurantBean> restaurantList = new ArrayList<>();
			while (rs.next()) {
				RestaurantBean restaurantdata = new RestaurantBean();
				restaurantdata.setName(rs.getString("NAME"));
				restaurantdata.setRegion(rs.getString("REGION"));
				restaurantdata.setType(rs.getString("TYPE"));

				restaurantList.add(restaurantdata);
			}
			rs.close();
			pstmt.close();
			return restaurantList;
		} catch (Exception e) {
			System.err.println("尋找資料時發生錯誤:" + e);
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	// update img
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
		} catch (Exception e) {
			System.err.println("更新資料時發生錯誤:" + e);
			conn.rollback();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	// delete restaurant
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
		} catch (Exception e) {
			System.err.println("刪除資料時發生錯誤:" + e);
			conn.rollback();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return true;

	}

	// insert data for Restaurant
	public void createRestaurant(RestaurantBean ResBean) throws SQLException {
		try {
			sql = "insert into RESTAURANT (NAME,ADDRESS,OPENTIME,DESCRIPTION,TRANSPORTATION,TYPE,RATING,REGION,PICTURE,SERVICEINFO,BOOKING_ID,ACCOUNT) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ResBean.getName()); // 餐廳名稱
			pstmt.setString(2, ResBean.getAddress()); // 餐廳地址
			pstmt.setString(3, ResBean.getOpentime()); // 餐廳營業時間
			pstmt.setString(4, ResBean.getDescription()); // 餐廳描述
			pstmt.setString(5, ResBean.getTransportation()); // 餐廳交通方式
			pstmt.setString(6, ResBean.getType()); // 餐廳類型
			pstmt.setBigDecimal(7, ResBean.getRating()); // rating初始值設為0，不讓使用者填
			pstmt.setString(8, ResBean.getRegion()); // 餐廳所在地區
			pstmt.setString(9, ResBean.getPicture()); // 餐廳照片，url格式
			pstmt.setString(10, ResBean.getServiceinfo()); // 餐廳用餐訊息
			pstmt.setString(11, ""); // booking_id不讓使用者填，設null
			pstmt.setString(12, ResBean.getAccount()); // 使用者username
			ResultSet rs = pstmt.executeQuery();
			pstmt.clearBatch();
			conn.commit();

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public RestaurantBean Restaurant_HP(String username) throws SQLException {
		try {
			sql = "select * from restaurant where account = ? ";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			pstmt.clearBatch();
			RestaurantBean r_data = new RestaurantBean();
			while (rs.next()) {
				BigDecimal r_sn = rs.getBigDecimal("R_SN");
				String name = rs.getString("NAME");
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

				r_data = new RestaurantBean(r_sn, name, address, opentime, description, transportation, type, rating,
						region, picture, serviceinfo, booking_id, username);
			}
			rs.close();
			pstmt.close();
			return r_data;

		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();
			return null;

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public OrderTableBean findR_Order(BigDecimal r_sn) throws SQLException {
		sql = "select * from R_ORDER_LIST where R_SN = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setBigDecimal(1, r_sn);
			ResultSet rs = pstmt.executeQuery();
			pstmt.clearBatch();
			OrderTableBean otBean = new OrderTableBean();
			
			while (rs.next()) {
				BigDecimal r_sn_order = rs.getBigDecimal("R_SN_ORDER");
				BigDecimal orderID = rs.getBigDecimal("ORDER_ID");
				BigDecimal cus_num = rs.getBigDecimal("CUSTOMER_NUM");
				Timestamp ts = rs.getTimestamp("BOOK_TIME");
				BigDecimal deposit = rs.getBigDecimal("DEPOSIT");
				R_OrderBean roBean = new R_OrderBean(r_sn_order, orderID, ts, cus_num, deposit, null);
				otBean.addR_OderBean(roBean);
			}
			return otBean;
			
		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();
			return null;

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
