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
import iring29.bean.R_OrderBean;
import pojo.OrderTableBean;

public class T_Order_ListDAO {

	
	private Connection conn;
	private DataSource ds;
	private PreparedStatement pstmt;

	// constructor
	public T_Order_ListDAO(int dataSourceType) throws IOException {
		ds = ConnectionPool.getDataSource(dataSourceType);
	}
	
	public void createOrder(OrderTableBean bean) throws SQLException {
		
		String sqlOrder_Data = "insert into ORDER_DATA (USERNAME, NAME, PHONE) values(?, ?, ?)";

		String sqlOrder_Table = "insert into ORDER_TABLE (ORDER_DATE, SN_NO) values(?, ?)";

		String sqlT_Order_LiString = "insert into T_ORDER_LIST (ORDER_ID, SN_SCHEDULE, TICKETPRICE, NUMBERS_DAYS, STARTPOINT, DESTINATION, DEPARTUREDATE, ORDER_TYPE) values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		String generatedColumn1[] = {"SN"}; //取得SN Sequence
		String generatedColumn2[] = {"ORDER_ID"}; //取得ORDER_ID Sequence
		
		try {
			// insert ORDER_DATA
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlOrder_Data, generatedColumn1);
			pstmt.setString(1, bean.getUser().getUserName()); //取得userbean裡面的username
//			pstmt.setString(2, bean.getCustomerName());  //取得下單時輸入的名字
//			pstmt.setString(3, bean.getCustomerPhone()); //取得下單時輸入的電話

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
				
				Set<T_OrderBean> traffic = bean.getT_OderBeans();
				for(T_OrderBean element : traffic ) {
					if (element.getOrderType().equals("0")) {
						
					
				
						try {
							PreparedStatement pstmt3 = conn.prepareStatement(sqlT_Order_LiString);
							
							for(T_OrderBean ele : traffic ) {
								pstmt3.setBigDecimal(1, order_id); //ORDER_ID from ORDER_TABLE
								System.out.println("order_id= " + order_id);
								
								pstmt3.setInt(2, ele.getHsrDO().getSnSchedule());	//取得SnSchedule
								System.out.println("SnSchedule= " + ele.getHsrDO().getSnSchedule());
								
								pstmt3.setBigDecimal(3, ele.getTrafficPrice()); //取得交通訂單總價
								System.out.println("TrafficPrice= " + ele.getTrafficPrice());
								
								pstmt3.setBigDecimal(4, ele.getNums_days()); //取得訂票張數or租借天數
								System.out.println("Nums_days= " + ele.getNums_days());
								
								pstmt3.setString(5, ele.getStartPoint()); //取得起始站位置
								System.out.println("StartPoint= " + ele.getStartPoint());
								
								pstmt3.setString(6, ele.getDestination()); //取得起始站位置		
								System.out.println("Destination= " + ele.getDestination());
								
								Timestamp ts2 = new Timestamp(ele.getDeparatureDate().getTime()); //出發日
								pstmt3.setTimestamp(7, ts2); //出發日
								System.out.println("DeparatureDate= " + ts2);
								
								pstmt3.setString(8, ele.getOrderType()); //交通類型0高鐵or1租車
								System.out.println("OrderType= " + ele.getOrderType());
								
								pstmt3.executeQuery();
							}
						}catch (SQLException ex) {
							ex.printStackTrace();
							throw new RuntimeException("發生SQL例外: " + ex.getMessage());
						}
					}
				}
				
			}catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("發生SQL例外: " + ex.getMessage());
			}
			conn.commit();


		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			//conn.rollback();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		
		
		
	}
	
	
}
