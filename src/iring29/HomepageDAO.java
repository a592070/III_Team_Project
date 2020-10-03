package iring29;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.ConnectionPool;
import pojo.AccountDO;
import pojo.AttractionsDO;

public class HomepageDAO {
	private Connection conn;
	private DataSource ds;
	private String sql;
	
	private static List<AccountDO> list;
//	private static List<HPUserInfoVO> listUserVO;
//	private static List<HPAttractionInfoVO> listAttractionVO;

	public HomepageDAO(int dataSourceType) throws IOException, SQLException {
		ds = ConnectionPool.getDataSource(dataSourceType);
		if (list == null || list.size() == 0) {
			listAccInit();
		}
	}

	//get data from DB
	public void listAccInit() throws SQLException  {
		list = new ArrayList<>();
		try {
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			sql = "select * from account";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				AccountDO accDO = new AccountDO();
				
				accDO.setUsername(rs.getString("USERNAME"));
				accDO.setPassword(rs.getString("PASSWORD"));
				accDO.setIdentity(rs.getBigDecimal("IDENTITY"));
				accDO.setEmail(rs.getString("EMAIL"));
				accDO.setPicture(rs.getBytes("PICTURE"));
				accDO.setModifyDate(rs.getDate("MODIFY_DATE"));
				accDO.setNickname(rs.getString("NICKNAME"));
				accDO.setRegister(rs.getDate("REGISTER"));
				accDO.setFavorite(rs.getString("FAVORITE"));
				accDO.setAttractionsId(rs.getString("ATTRACTIONS_ID"));

				list.add(accDO);
			}
			//test 
//			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}


	public List<AccountDO> listAccDO(){
		return list;
	}
	
	//get user info from list 
//	public List<HPUserInfoVO> listHPUserVO(){
//		listUserVO = new ArrayList<>();
//		if(list != null || list.size() != 0) {
//			for (AccountDO acc : list) {
//				HPUserInfoVO hpUserInfoVO = new HPUserInfoVO();
//				hpUserInfoVO.setUsername(acc.getUsername());
//				hpUserInfoVO.setPassword(acc.getPassword());
//				hpUserInfoVO.setEmail(acc.getEmail());
//				hpUserInfoVO.setPicture(acc.getPicture());
//				hpUserInfoVO.setNickname(acc.getNickname());
//				hpUserInfoVO.setRegister(acc.getRegister());
//				hpUserInfoVO.setFavorite(acc.getFavorite());
//				
//				listUserVO.add(hpUserInfoVO);
//			}
//		}
//		return listUserVO;
//	}

	
	
	// 尋找user info
	public AccountDO findUser(String username) throws SQLException {
		try {
			String password;
			BigDecimal identity;
			String email;
			byte[] picture;
			String nickname;
			Date modifyDate;
			Date register;
			String favorite;
			String attractionsId;

			String sql = "select * from account where username = ?";
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			AccountDO accDO = new AccountDO();
			accDO.setUsername(username);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				password = rs.getString("PASSWORD");
				identity = rs.getBigDecimal("IDENTITY");
				email = rs.getString("EMAIL");
				picture = rs.getBytes("PICTURE");
				nickname = rs.getString("NICKNAME");
				modifyDate = rs.getDate("MODIFY_DATE");
				register = rs.getDate("REGISTER");
				favorite = rs.getString("FAVORITE");
				attractionsId = rs.getString("ATTRACTIONS_ID");

				System.out.println(password);
				accDO = new AccountDO(username, password, identity, email, picture, nickname, modifyDate, register,
						favorite, attractionsId);
			}
			rs.close();
			pstmt.close();
			return accDO;

		} catch (Exception e) {
			System.err.println("尋找部門資料時發生錯誤:" + e);
			return null;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}


	//update user info
	public AccountDO UserinfoUpdate(String username) throws SQLException {
		try {
			String sql = "update account set PASSWORD = ?, EMAIL = ?, PICTURE= ?, MODIFY_DATE= ? , NICKNAME = ?" ;
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			
			
			
		}finally {
			if (conn != null) {
				conn.close();
			}
		}
		return null;

	}

	// 從ID找名稱attraction name
	public AttractionsDO findAttracion(String id) throws SQLException {
		try {
			String sql = "select name from attractions where id = ?";
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}

		}

		return null;


//	public AccountDO UserinfoUpdate(String username) {
//		return null;

//	}

	}
	
	
}
