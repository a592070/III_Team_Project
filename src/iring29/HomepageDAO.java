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

public class HomepageDAO {
	private Connection conn;
	private DataSource ds;
//	static List<AccountDO> list;

	public HomepageDAO() throws IOException {
		ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITH_SERVER);
	}

	public List<AccountDO> listAcc() throws SQLException  {
		List<AccountDO> list = new ArrayList<>();
		try {
			String sql = "select * from account order by 1";
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return list;
	}

//	public List<AccountDO> listAccDO(){
//		return list;
//	}
	
	// 尋找user info
	public AccountDO findUser(String username) {
		try {
//			AccountDO accDO = null;
			String password;
			BigDecimal identity;
			String email;
			byte[] picture;
			String nickname;
			Date modifyDate;
			Date register;
			String favorite;
			String attractionsId;

			System.out.println("start");
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
	
	
}
