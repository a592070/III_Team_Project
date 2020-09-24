package iring29;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import controller.ConnectionPool;
import pojo.AccountDO;


public class HomepageInit {

	private String sql;
//	DataSource ds;
	
	public void searchUsername(AccountDO accDo) throws IOException{
		DataSource ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
		try {
			Connection conn = ds.getConnection();
			sql = "select * from account where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//輸入username
			pstmt.setString(1, accDo.getUsername()); 
			ResultSet rs = pstmt.executeQuery();
			
			//select username對應的資訊
			while(rs.next()) {
				System.out.println("username = " + rs.getString("username"));
				System.out.println("password = " + rs.getString("password"));
				System.out.println("identity = " + rs.getBigDecimal("identity"));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//close DB
		}
	}

	
	
}
