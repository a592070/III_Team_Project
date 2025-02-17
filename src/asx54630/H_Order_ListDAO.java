package asx54630;

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
import iring29.bean.R_OrderBean;
import pojo.OrderTableBean;

public class H_Order_ListDAO {
	private Connection conn;
	private DataSource ds;
	private PreparedStatement pstmt;

	// constructor
	public H_Order_ListDAO(int dataSourceType) throws IOException {
		ds = ConnectionPool.getDataSource(dataSourceType);
	}

	public void createOrder(OrderTableBean bean) throws SQLException {
		

		String sqlOrder_Data = "insert into ORDER_DATA (USERNAME, NAME, PHONE) values(?, ?, ?)";

		String sqlOrder_Table = "insert into ORDER_TABLE (ORDER_DATE, SN_NO) values(?, ?)";

		String sqlH_Order_List = "insert into H_ORDER_LIST (ORDER_ID, ORDER_HOTEL_ID, DOUBLE_ROOM, QUADRUPLE_ROOM, CHECK_IN, CHECK_OUT, H_PRICE) values(?, ?, ?, ?, ?, ?, ?)";

		String generatedColumn1[] = {"SN"}; //取得SN Sequence
		String generatedColumn2[] = {"ORDER_ID"}; //取得ORDER_ID Sequence
		try {
			// insert ORDER_DATA
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlOrder_Data, generatedColumn1);
			pstmt.setString(1, bean.getUser().getUserName());
//			pstmt.setString(2, bean.getCustomerName());
//			pstmt.setString(3, bean.getCustomerPhone());

			pstmt.executeQuery();
			pstmt.clearBatch();
			BigDecimal sn ; 
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			
			
			if(generatedKeys.next()) {
				sn = generatedKeys.getBigDecimal(1); //取得SN from ORDER_DATA
			}else {
				throw new RuntimeException("無法取得新增之ORDER_DATA表格的主鍵");
			}
			

			try {
				PreparedStatement pstmt2 = conn.prepareStatement(sqlOrder_Table, generatedColumn2);

					Timestamp ts = new Timestamp(System.currentTimeMillis()); //下訂單時間
					pstmt2.setTimestamp(1, ts);
					pstmt2.setBigDecimal(2, sn);
					
					pstmt2.executeQuery();
					
					BigDecimal order_id = null ;
					ResultSet generatedKeys2 = pstmt2.getGeneratedKeys();

					if(generatedKeys2.next()) {
						order_id = generatedKeys2.getBigDecimal(1); //取得ORDER_ID from ORDER_TABLE
					}
				
				Set<H_OrderBean> hotelDO = bean.getH_OrderBeans();
				try {
					PreparedStatement pstmt3 = conn.prepareStatement(sqlH_Order_List);
					
					for(H_OrderBean ele : hotelDO) {
						pstmt3.setBigDecimal(1, order_id);
						pstmt3.setBigDecimal(2, ele.getHotelDO().getSN());
						pstmt3.setBigDecimal(3, ele.getDOUBLE_ROOM());
						pstmt3.setBigDecimal(4, ele.getQUADRUPLE_ROOM());
						pstmt3.setDate(5, ele.getCHECK_IN());
						pstmt3.setDate(6, ele.getCHECK_OUT());
						pstmt3.setBigDecimal(7, ele.getH_PRICE());
						pstmt3.executeQuery();
					}
				}catch (SQLException ex) {
					ex.printStackTrace();
					throw new RuntimeException("發生SQL例外: " + ex.getMessage());
				}
				
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
