package iring29;

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

public class R_Order_ListDAO {
	private Connection conn;
	private DataSource ds;
	private PreparedStatement pstmt;

	// constructor
	public R_Order_ListDAO(int dataSourceType) throws IOException {
		ds = ConnectionPool.getDataSource(dataSourceType);
	}

	
	//create order
	public void createOrder(OrderTableBean bean) throws SQLException {
		

		String sqlOrder_Data = "insert into ORDER_DATA (USERNAME, NAME, PHONE) values(?, ?, ?)";

		String sqlOrder_Table = "insert into ORDER_TABLE (ORDER_DATE, SN_NO) values(?, ?)";

		String sqlR_Order_LiString = "insert into R_ORDER_LIST (ORDER_ID, R_SN, CUSTOMER_NUM, BOOK_TIME, DEPOSIT) values(?, ?, ?, ?, ?)";

		String generatedColumn1[] = {"SN"}; //取得SN Sequence
		String generatedColumn2[] = {"ORDER_ID"}; //取得ORDER_ID Sequence
		try {
			// insert ORDER_DATA
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlOrder_Data, generatedColumn1);
			pstmt.setString(1, bean.getUser().getUserName()); //取得userbean裡面的username
			pstmt.setString(2, bean.getCustomerName());  //取得下單時輸入的名字
			pstmt.setString(3, bean.getCustomerPhone()); //取得下單時輸入的電話

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
					pstmt2.setBigDecimal(2, sn);  //SN from ORDER_DATA
					
					pstmt2.executeQuery();
					
					BigDecimal order_id = null ;
					ResultSet generatedKeys2 = pstmt2.getGeneratedKeys();

					if(generatedKeys2.next()) {
						order_id = generatedKeys2.getBigDecimal(1); //取得ORDER_ID from ORDER_TABLE
					}
				
				Set<R_OrderBean> restaurants = bean.getR_OderBeans();
				try {
					PreparedStatement pstmt3 = conn.prepareStatement(sqlR_Order_LiString);
					
					for(R_OrderBean ele : restaurants) {
						pstmt3.setBigDecimal(1, order_id); //ORDER_ID from ORDER_TABLE
						pstmt3.setBigDecimal(2, ele.getRestaurantBean().getR_sn()); //取得RestaurantBean的R_SN(餐廳序號)
						pstmt3.setBigDecimal(3, ele.getCustomerNum()); //訂位人數
						Timestamp ts2 = new Timestamp(ele.getBooking_date().getTime()); //設定要去餐廳的訂位日期
						pstmt3.setTimestamp(4, ts2); //要去餐廳的訂位日期
						pstmt3.setBigDecimal(5, ele.getDeposit()); //訂餐廳訂金
						
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
	
	//find r_order
	public OrderTableBean findR_order_List(){
		return null;
		
	}

}
