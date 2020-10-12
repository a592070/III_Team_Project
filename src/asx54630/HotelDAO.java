package asx54630;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import a592070.pojo.AttractionDO;
import controller.ConnectionPool;
import iring29.bean.RestaurantBean;

public class HotelDAO {

	private DataSource ds;
    private static Connection conn;
    private String sql;
    private static PreparedStatement predStmt;
    private ResultSet rs;

    public HotelDAO(int connType) throws IOException {
        ds = ConnectionPool.getDataSource(connType);
    }

    public List<HotelDO> hSearch(String keyword,String regionkeywd,String typekeywd) throws SQLException {
        try {
          conn = ds.getConnection();
          predStmt = conn.prepareStatement("SELECT * FROM HOTEL WHERE NAME like ? and REGION like ? and TYPE like ?  ");

          predStmt.setString(1, "%" + keyword + "%");
          predStmt.setString(2, "%" + regionkeywd + "%");
//          predStmt.setString(3, "%" + keyword + "%");
          predStmt.setString(3, "%" + typekeywd + "%");
    	  ResultSet rs = predStmt.executeQuery();
    	  ArrayList<HotelDO> hotelList = new ArrayList<>();
    	  while (rs.next()) {
    		  HotelDO hoteldo = new HotelDO();
				BigDecimal id = rs.getBigDecimal("SN");
				hoteldo.setSN(id);
				String name = rs.getString("NAME");
				hoteldo.setNAME(name);
				String region = rs.getString("REGION");
				hoteldo.setREGION(region);
				String address = rs.getString("ADDRESS");
				hoteldo.setADDRESS(address);
				String tel = rs.getString("TEL");
				hoteldo.setTEL(tel);
				BigDecimal dbroom = rs.getBigDecimal("DOUBLE_ROOM");
				hoteldo.setDOUBLE_ROOM(dbroom);
				BigDecimal quadroom = rs.getBigDecimal("QUADRUPLE_ROOM");
				hoteldo.setQUADRUPLE_ROOM(quadroom);
				String description = rs.getString("DESCRIPTION");
				hoteldo.setDESCRIPTION(description);
				String opentime = rs.getString("OPENTIME");
				hoteldo.setOPENTIME(opentime);
				String type = rs.getString("TYPE");
				hoteldo.setTYPE(type);
				BigDecimal rating = rs.getBigDecimal("RATING");
				hoteldo.setRATING(rating);
				String account = rs.getString("ACCOUNT");
				hoteldo.setACCOUNT(account);
				hotelList.add(hoteldo);
    	  }
    	  rs.close();
    	  predStmt.close();
    	  return hotelList;
    	  
        } catch (Exception e) {
    	    System.err.println("尋找住宿資料時發生錯誤:" + e);
    	    return null;
        }finally {
			if (conn != null) {
				conn.close();
			}
        }
    }
    
    public List<HotelDO> DetailSearch(String detailname) throws SQLException {
        try {
          conn = ds.getConnection();
          predStmt = conn.prepareStatement("SELECT * FROM HOTEL WHERE NAME like ?");

          predStmt.setString(1, "%" + detailname + "%");
    	  ResultSet rs = predStmt.executeQuery();
    	  ArrayList<HotelDO> detailList = new ArrayList<>();
    	  while (rs.next()) {
    		  HotelDO hoteldo = new HotelDO();
				BigDecimal id = rs.getBigDecimal("SN");
				hoteldo.setSN(id);
				String name = rs.getString("NAME");
				hoteldo.setNAME(name);
				String region = rs.getString("REGION");
				hoteldo.setREGION(region);
				String address = rs.getString("ADDRESS");
				hoteldo.setADDRESS(address);
				String tel = rs.getString("TEL");
				hoteldo.setTEL(tel);
				BigDecimal dbroom = rs.getBigDecimal("DOUBLE_ROOM");
				hoteldo.setDOUBLE_ROOM(dbroom);
				BigDecimal quadroom = rs.getBigDecimal("QUADRUPLE_ROOM");
				hoteldo.setQUADRUPLE_ROOM(quadroom);
				String description = rs.getString("DESCRIPTION");
				hoteldo.setDESCRIPTION(description);
				String opentime = rs.getString("OPENTIME");
				hoteldo.setOPENTIME(opentime);
				String type = rs.getString("TYPE");
				hoteldo.setTYPE(type);
				BigDecimal rating = rs.getBigDecimal("RATING");
				hoteldo.setRATING(rating);
				String account = rs.getString("ACCOUNT");
				hoteldo.setACCOUNT(account);
				detailList.add(hoteldo);
    	  }
    	  rs.close();
    	  predStmt.close();
    	  return detailList;
    	  
        } catch (Exception e) {
    	    System.err.println("尋找住宿資料時發生錯誤:" + e);
    	    return null;
        }finally {
			if (conn != null) {
				conn.close();
			}
        }
    }
    
    
    
    public void createHotel(HotelDO hotDO) throws SQLException {
		try {
			sql = "insert into hotel (name, region, address, tel, double_room, quadruple_room, description, opentime, type, rating, account) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?)";
			conn = ds.getConnection();
			predStmt = conn.prepareStatement(sql);
			predStmt.setString(1, hotDO.getNAME()); // 飯店名稱
			predStmt.setString(2, hotDO.getREGION()); // 飯店所在地區
			predStmt.setString(3, hotDO.getADDRESS()); // 飯店地址
			predStmt.setString(4, hotDO.getTEL()); // 飯店電話
			predStmt.setBigDecimal(5, hotDO.getDOUBLE_ROOM()); // 雙人房價格
			predStmt.setBigDecimal(6, hotDO.getQUADRUPLE_ROOM()); // 四人房價格
			predStmt.setString(7, hotDO.getDESCRIPTION()); // 飯店簡介
			predStmt.setString(8, hotDO.getOPENTIME()); // 飯店營業時間
			predStmt.setString(9, hotDO.getTYPE()); // 飯店類型 (飯店、民宿、汽車旅館)
			predStmt.setBigDecimal(10, new BigDecimal(BigInteger.ONE)); // rating初始值設為0，不讓使用者填
			predStmt.setString(11, hotDO.getACCOUNT()); // 使用者username
			ResultSet rs = predStmt.executeQuery();
			predStmt.clearBatch();
			conn.commit();

			rs.close();
			predStmt.close();
		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
}