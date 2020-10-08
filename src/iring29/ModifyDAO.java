package iring29;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import controller.ConnectionPool;

public class ModifyDAO {
	private Connection conn;
	private DataSource ds;
	private PreparedStatement pstmt;
	private String sql;
	
	// constructor
	public ModifyDAO(int dataSourceType) throws IOException {
		ds = ConnectionPool.getDataSource(dataSourceType);
	}
	
	//modify
	public boolean R_Address(String address, String r_sn) throws SQLException {
		sql = "update restaurant set address = ? where r_sn = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, address);
			pstmt.setString(2, r_sn);
			pstmt.executeQuery();
			pstmt.clearBatch();
			conn.commit();
			return true;
		}catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();
			return false;

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	
}
