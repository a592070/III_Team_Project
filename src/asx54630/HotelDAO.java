package asx54630;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

				PreparedStatement pstmt = connection.prepareStatement("select * from HOTEL");
				ResultSet rs = pstmt.executeQuery();) {
			

			while (rs.next()) {
				//把每一筆資料轉成TABLE1物件
				HotelDO hoteldo = new HotelDO();
				//UNO
				BigDecimal id = rs.getBigDecimal("SN");
				hoteldo.setSN(id);
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
				BigDecimal dbroom = rs.getBigDecimal("DOUBLE_ROOM");
				hoteldo.setDOUBLE_ROOM(dbroom);
				//WEB
				BigDecimal quadroom = rs.getBigDecimal("QUADRUPLE_ROOM");
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
				String account = rs.getString("ACCOUNT");
				hoteldo.setACCOUNT(account);
				midlist.add(hoteldo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return midlist;
	}

public void NAME(String[] args) {
		
		try (Connection connection = getDataSource().getConnection();) {
		
			Scanner sc = new Scanner(System.in);
			PreparedStatement pstmt = connection.prepareStatement("select * from HOTEL where sn=?");
			System.out.println("請輸入代碼 ");
        	int i = sc.nextInt();
        	pstmt.setInt(1, i);
        	int result = pstmt.executeUpdate();
        	if (result>0) {				
        		ResultSet rs = pstmt.executeQuery();
        		if(rs.next()) {
        			System.out.println("飯店編號: "+rs.getBigDecimal("SN")+"\n"
        					+"飯店名稱: "+rs.getString("NAME")+"\n"
        					+"地區: "+rs.getString("REGION")+"\n"
        					+"地址: "+rs.getString("ADDRESS")+"\n"
        					+"電話: "+rs.getString("TEL")+"\n"
        					+"雙人房價格: "+rs.getBigDecimal("DOUBLE_ROOM")+"\n"
        					+"四人房價格: "+rs.getBigDecimal("QUADRUPLE_ROOM")+"\n"
        					+"簡介: "+rs.getString("DESCRIPTION")+"\n"
        					+"營業時間: "+rs.getString("OPENTIME")+"\n"
        					+"住宿類型: "+rs.getString("TYPE")+"\n"
        					+"評價: "+rs.getBigDecimal("RATING")+"\n"
        					+"所屬業者: "+rs.getString("ACCOUNT"));
        		}
			}else {
				System.out.println("查無此飯店!");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }

}