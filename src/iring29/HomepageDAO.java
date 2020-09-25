package iring29;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import pojo.AccountDO;

public class HomepageDAO {

	private Connection conn;

	
	public HomepageDAO(Connection conn) {
		this.conn = conn;
	}

	// 尋找user info
	public AccountDO findUser(String username) {
		try {
			AccountDO accDO = null;
			String password;
			BigDecimal identity;
			String email;
			byte[] picture;
			String nickname;
			Date modifyDate;
			Date register;
			String favorite;
			String attractionsId;
			
			Statement stmt = conn.createStatement();
//			String sql = "select * from account where username = \'" + username+"\'";	
			//select * from account where username ='username';
			//select * from account where username =username;
			String sql = "select * from account where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			System.out.println(sql);

			ResultSet rs = pstmt.executeQuery(sql);

			if (rs.next()) {
				password = rs.getString("password");
				identity = rs.getBigDecimal("password");
				email = rs.getString("email");
				picture = rs.getBytes("picture");
				nickname = rs.getString("nickname");
				modifyDate = rs.getDate("modifydate");
				register = rs.getDate("register");
				favorite = rs.getString("favorite");
				attractionsId = rs.getString("attractionsId");
				
				
				accDO = new AccountDO(username,password, identity, email, picture, nickname, modifyDate, register, favorite, attractionsId);
			}

		} catch (Exception e) {
			System.err.println("尋找部門資料時發生錯誤:" + e);
			
		}
		return null;
	}
}
