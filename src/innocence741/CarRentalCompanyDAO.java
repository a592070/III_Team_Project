package innocence741;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Set;

import javax.sql.DataSource;

import controller.ConnectionPool;
import pojo.OrderTableBean;

public class CarRentalCompanyDAO {
	
	private Connection conn;
	private DataSource ds;
	private PreparedStatement pstmt;

	// constructor
	public CarRentalCompanyDAO(int dataSourceType) throws IOException {
		ds = ConnectionPool.getDataSource(dataSourceType);
	}
	
	public void signUPCarRentalCompany(CarRentalCompanyBean bean) throws SQLException {
		
		String sqlCARRENTALCOMPANY = "insert into CARRENTALCOMPANY (NAME_COMPANY, ADDRESS, DESCRIPTION, OPENHOURS, TEL, COMPANY_ACCOUNT) values(?, ?, ?, ?, ?, ?)";

//		String sqlCARTYPE = "insert into CARTYPE (CARTYPE, PRICE, SN_RENTCARCOMPANY) values(?, ?, ?)";
//		
//		String generatedColumn1[] = {"SN_RENTALCOMPANY"};
//		String generatedColumn2[] = {"SN_CARTYPE"}; 
		
		try {
			
			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sqlCARRENTALCOMPANY, generatedColumn1);
			pstmt = conn.prepareStatement(sqlCARRENTALCOMPANY);
			pstmt.setString(1, bean.getCompanyName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getOpenHours());
			pstmt.setString(5, bean.getTelphoneNum());
			pstmt.setString(6, bean.getCompanyAccount());

			pstmt.executeQuery();
			pstmt.clearBatch();
//			BigDecimal sn_rentalcompany ; 
//			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			
			
//			if(generatedKeys.next()) {
//				sn_rentalcompany = generatedKeys.getBigDecimal(1); //取得SN from ORDER_DATA
//			}else {
//				throw new RuntimeException("無法取得新增之ORDER_DATA表格的主鍵");
//			}
			

//			try {
//				PreparedStatement pstmt2 = conn.prepareStatement(sqlCARTYPE, generatedColumn2);
//
//				pstmt2.setString(1, bean.getCarType());  
//				pstmt2.setBigDecimal(2, bean.getPrice());
//				pstmt2.setBigDecimal(3, sn_rentalcompany);  
//									
//				pstmt2.executeQuery();
//					
//				
//			}catch (SQLException ex) {
//				ex.printStackTrace();
//				throw new RuntimeException("發生SQL例外: " + ex.getMessage());
//			}
			conn.commit();


		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		
		
		
	}
	
	
	public void getSN_RENTALCOMPANY(CarRentalCompanyBean carBean) throws SQLException {
		try {
			String companyAccount = carBean.getCompanyAccount();
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select * from CARRENTALCOMPANY where COMPANY_ACCOUNT=" + "\'" +companyAccount + "\'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				 carBean.setSn_carRentalCompany(rs.getBigDecimal("SN_RENTALCOMPANY"));
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		 
	}
	
	
	public void addCarType(CarTypeBean carTypeBean) throws SQLException {
		String sql = "insert into CARTYPE (CARTYPE, PRICE, SN_RENTCARCOMPANY) values(?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carTypeBean.getCarType());  
			pstmt.setBigDecimal(2, carTypeBean.getPrice());
			pstmt.setBigDecimal(3, carTypeBean.getCarRentalCompanyBean().getSn_carRentalCompany()); 
			pstmt.executeQuery();
			pstmt.clearBatch();			
			
			conn.commit();

		}catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}
	

	
}
