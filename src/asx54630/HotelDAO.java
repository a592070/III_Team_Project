package asx54630;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import controller.ConnectionPool;

public class HotelDAO {

	private DataSource dataSource;

	public DataSource getDataSource() throws IOException, SQLException {
		DataSource ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
		Connection conn = ds.getConnection();
		return ds;
	}

	public List<HotelDO> listHotelDO() throws IOException {

		List<HotelDO> midlist = new ArrayList<>();
		try (Connection connection = getDataSource().getConnection();
			 Statement stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery("select * from HOTEL");) {

			while (rs.next()) {
				//把每一筆資料轉成TABLE1物件
				HotelDO hoteldo = new HotelDO();
				//UNO
				String id = rs.getString("HOTEL_ID");
				hoteldo.setHOTEL_ID(id);
				//UNAME
				String name = rs.getString("NAME");
				hoteldo.setNAME(name);
				//PUB_PRI 
				String region = rs.getString("REGION");
				hoteldo.setREGION(region);
				//COUNTIES
				String address = rs.getString("ADDRESS");
				hoteldo.setADDRESS(address);
				//ADDRESS
				String tel = rs.getString("TEL");
				hoteldo.setTEL(tel);
				//PHONE
				String dbroom = rs.getString("DOUBLE_ROOM");
				hoteldo.setDOUBLE_ROOM(dbroom);
				//WEB
				String quadroom = rs.getString("QUADRUPLE_ROOM");
				hoteldo.setQUADRUPLE_ROOM(quadroom);
				//SYSTEM
				String description = rs.getString("DESCRIPTION");
				hoteldo.setDESCRIPTION(description);
				String opentime = rs.getString("OPENTIME");
				hoteldo.setOPENTIME(opentime);
				String type = rs.getString("TYPE");
				hoteldo.setTYPE(type);
				BigDecimal rating = rs.getBigDecimal("RATING");
				hoteldo.setRATING(rating);
				midlist.add(hoteldo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return midlist;
	}

	//TODO
	public void NAME(String[] args) {

		try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "Tpoic1",
				"Topic1");) {


			PreparedStatement pstmt = connection.prepareStatement("select * from HOTEL where HOTEL_ID=?");
			pstmt.setString(1, "");
			int result = pstmt.executeUpdate();
			if (result > 0) {
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					System.out.println("代碼: " + rs.getInt("UNO") + "\n"
							+ "校名: " + rs.getString("UNAME") + "\n"
							+ "公/私立: " + rs.getString("PUB_PRI") + "\n"
							+ "縣市: " + rs.getString("COUNTIES") + "\n"
							+ "地址: " + rs.getString("ADDRESS") + "\n"
							+ "電話: " + rs.getString("PHONE") + "\n"
							+ "網站: " + rs.getString("WEB") + "\n"
							+ "體系別: " + rs.getString("SYSTEM"));
				}
			} else {
				System.out.println("查無此學校!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}