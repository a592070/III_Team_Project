package iring29.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import controller.ConnectionPool;

public class ModifyDAO_Original {
	private Connection conn;
	private DataSource ds;
	private PreparedStatement pstmt;
	private String sql;
	
	// constructor
	public ModifyDAO_Original(int dataSourceType) throws IOException {
		ds = ConnectionPool.getDataSource(dataSourceType);
	}
	
	//modify location
	public boolean R_Address(String address, String transportation, BigDecimal r_sn) throws SQLException {
		sql = "update restaurant set address = ?, transportation = ? where r_sn = ? ";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, address);
			pstmt.setString(2, transportation);
			pstmt.setBigDecimal(3, r_sn);
			pstmt.executeQuery();
			pstmt.clearBatch();
			conn.commit();
			pstmt.close();
			return true;
		}catch (Exception e) {
			System.err.println("修改資料時發生錯誤:" + e);
			conn.rollback();
			return false;

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	//modify Type
		public boolean R_Type(String serviceinfo, String type, BigDecimal r_sn) throws SQLException {
			sql = "update restaurant set serviceinfo = ?, type= ? where r_sn = ? ";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, serviceinfo);
				pstmt.setString(2, type);
				pstmt.setBigDecimal(3, r_sn);
				pstmt.executeQuery();
				pstmt.clearBatch();
				conn.commit();
				pstmt.close();
				return true;
			}catch (Exception e) {
				System.err.println("修改資料時發生錯誤:" + e);
				conn.rollback();
				return false;

			} finally {
				if (conn != null) {
					conn.close();
				}
			}
		}
	
		//modify Info
				public boolean R_Info(String opentime, String description, BigDecimal r_sn) throws SQLException {
					sql = "update restaurant set opentime = ?, description= ? where r_sn = ? ";
					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, opentime);
						pstmt.setString(2, description);
						pstmt.setBigDecimal(3, r_sn);
						pstmt.executeQuery();
						pstmt.clearBatch();
						conn.commit();
						pstmt.close();
						return true;
					}catch (Exception e) {
						System.err.println("修改資料時發生錯誤:" + e);
						conn.rollback();
						return false;

					} finally {
						if (conn != null) {
							conn.close();
						}
					}
				}
				
				//modify Img
				public boolean R_Img(String picture, BigDecimal r_sn) throws SQLException {
					sql = "update restaurant set picture= ? where r_sn = ? ";
					try {
						conn = ds.getConnection();
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, picture);
						pstmt.setBigDecimal(2, r_sn);
						pstmt.executeQuery();
						pstmt.clearBatch();
						conn.commit();
						pstmt.close();
						return true;
					}catch (Exception e) {
						System.err.println("修改資料時發生錯誤:" + e);
						conn.rollback();
						return false;

					} finally {
						if (conn != null) {
							conn.close();
						}
					}
				}
	
}
