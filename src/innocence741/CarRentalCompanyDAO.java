package innocence741;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	public void signUPCarRentalCompany(CarTypeBean bean) throws SQLException {
		
		String sqlCARRENTALCOMPANY = "insert into CARRENTALCOMPANY (NAME_COMPANY, ADDRESS, DESCRIPTION, OPENHOURS, TEL, COMPANY_ACCOUNT) values(?, ?, ?, ?, ?, ?)";

		String sqlCARTYPE = "insert into CARTYPE (CARTYPE, PRICE, SN_RENTCARCOMPANY) values(?, ?, ?)";
		
		String generatedColumn1[] = {"SN_RENTALCOMPANY"};
		String generatedColumn2[] = {"SN_CARTYPE"}; 
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlCARRENTALCOMPANY, generatedColumn1);
			pstmt.setString(1, bean.getCarRentalCompanyBean().getCompanyName());
			pstmt.setString(2, bean.getCarRentalCompanyBean().getAddress());
			pstmt.setString(3, bean.getCarRentalCompanyBean().getDescription());
			pstmt.setString(4, bean.getCarRentalCompanyBean().getOpenHours());
			pstmt.setString(5, bean.getCarRentalCompanyBean().getTelphoneNum());
			pstmt.setString(6, bean.getCarRentalCompanyBean().getCompanyAccount());

			pstmt.executeQuery();
			pstmt.clearBatch();
			BigDecimal sn_rentalcompany ; 
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			
			
			if(generatedKeys.next()) {
				sn_rentalcompany = generatedKeys.getBigDecimal(1); //取得SN from ORDER_DATA
			}else {
				throw new RuntimeException("無法取得新增之ORDER_DATA表格的主鍵");
			}
			

			try {
				PreparedStatement pstmt2 = conn.prepareStatement(sqlCARTYPE, generatedColumn2);

				pstmt2.setString(1, bean.getCarType());  
				pstmt2.setBigDecimal(2, bean.getPrice());
				pstmt2.setBigDecimal(3, sn_rentalcompany);  
									
				pstmt2.executeQuery();
					
				
			}catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("發生SQL例外: " + ex.getMessage());
			}
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
	
}
