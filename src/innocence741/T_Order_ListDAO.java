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
	
	
	public void createOrderTable(OrderTableBean bean, int[] rcd, String[] order_id) throws SQLException {
		
		String sqlOrder_Table = "insert into ORDER_TABLE (ORDER_DATE, USERNAME) values(?, ?)";
		String generatedColumn1[] = {"ORDER_ID"}; //取得ORDER_ID Sequence
		
		try {
			// insert ORDER_TABLE
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlOrder_Table, generatedColumn1);
			
			Timestamp ts = new Timestamp(System.currentTimeMillis()); //下訂單時間
			pstmt.setTimestamp(1, ts);  
			pstmt.setString(2, bean.getUser().getUserName()); //取得userbean裡面的username
			pstmt.executeQuery();
			pstmt.clearBatch();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			
			
			if(generatedKeys.next()) {
				order_id[0] = generatedKeys.getBigDecimal(1).toString(); //取得SN from ORDER_TABLE
			}else {
				throw new RuntimeException("無法取得新增之ORDER_DATA表格的主鍵");
			}
		
			conn.commit();
			rcd[0] = 1;
		}		
		catch (Exception e) {
			rcd[0] = 0;
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();
		}finally {
			if (conn != null) {
			conn.close();
			}
		}
	}
	
	public void createT_Order_List(OrderTableBean bean, int[] rcd) throws SQLException {

		String sqlT_Order_LiString = "insert into T_ORDER_LIST (ORDER_ID, SN_SCHEDULE, TICKETPRICE, NUMBERS_DAYS, STARTPOINT, DESTINATION, DEPARTUREDATE, ORDER_TYPE, NAME, PHONE, SN_ORDER) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Set<T_OrderBean> traffic = bean.getT_OderBeans();
		for(T_OrderBean element : traffic ) {
			if (element.getOrderType().equals("0")) {
				try {
//					System.out.println("whyyyyyyyyy");
					conn = ds.getConnection();
					PreparedStatement pstmt3 = conn.prepareStatement(sqlT_Order_LiString);
//					System.out.println("whyyyyyyyyy");

					for(T_OrderBean ele : traffic ) {
						pstmt3.setBigDecimal(1, ele.getOrder_id()); //ORDER_ID from ORDER_TABLE
						System.out.println("order_id= " + ele.getOrder_id());
						
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
						
						pstmt3.setString(9, ele.getCustomerName()); //交通類型0高鐵or1租車
						System.out.println("CustomerName= " + ele.getCustomerName());
						
						pstmt3.setString(10, ele.getCustomerPhone()); //交通類型0高鐵or1租車
						System.out.println("CustomerPhone= " + ele.getCustomerPhone());
						
						pstmt3.setBigDecimal(11, ele.getT_sn_order()); //交通類型0高鐵or1租車
						System.out.println("T_sn_order= " + ele.getT_sn_order());

							
						pstmt3.executeQuery();
					}
					conn.commit();
					rcd[0] = 1;

				}catch (SQLException ex) {
					conn.rollback();
					rcd[0] = 0;
					ex.printStackTrace();
					throw new RuntimeException("發生SQL例外: " + ex.getMessage());
				}
			}
		}

	}

//	public void createOrder(OrderTableBean bean,int[] a) throws SQLException {
//		
//		String sqlOrder_Table = "insert into ORDER_TABLE (ORDER_DATE, USERNAME) values(?, ?)";
//
//		String sqlT_Order_LiString = "insert into T_ORDER_LIST (ORDER_ID, SN_SCHEDULE, TICKETPRICE, NUMBERS_DAYS, STARTPOINT, DESTINATION, DEPARTUREDATE, ORDER_TYPE, NAME, PHONE, SN_ORDER) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		
//		String generatedColumn1[] = {"ORDER_ID"}; //取得ORDER_ID Sequence
//		
//		try {
//			// insert ORDER_TABLE
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sqlOrder_Table, generatedColumn1);
//			
//			Timestamp ts = new Timestamp(System.currentTimeMillis()); //下訂單時間
//			pstmt.setTimestamp(1, ts);  
//			pstmt.setString(2, bean.getUser().getUserName()); //取得userbean裡面的username
//			pstmt.executeQuery();
//			pstmt.clearBatch();
//			BigDecimal order_id ; 
//			ResultSet generatedKeys = pstmt.getGeneratedKeys();
//			
//			
//			if(generatedKeys.next()) {
//				order_id = generatedKeys.getBigDecimal(1); //取得SN from ORDER_TABLE
//			}else {
//				throw new RuntimeException("無法取得新增之ORDER_DATA表格的主鍵");
//			}
//			
//			
//			Set<T_OrderBean> traffic = bean.getT_OderBeans();
//			for(T_OrderBean element : traffic ) {
//				if (element.getOrderType().equals("0")) {
//					try {
//						PreparedStatement pstmt3 = conn.prepareStatement(sqlT_Order_LiString);
//					
//						for(T_OrderBean ele : traffic ) {
//							pstmt3.setBigDecimal(1, order_id); //ORDER_ID from ORDER_TABLE
//							System.out.println("order_id= " + order_id);
//							
//							pstmt3.setInt(2, ele.getHsrDO().getSnSchedule());	//取得SnSchedule
//							System.out.println("SnSchedule= " + ele.getHsrDO().getSnSchedule());
//								
//							pstmt3.setBigDecimal(3, ele.getTrafficPrice()); //取得交通訂單總價
//							System.out.println("TrafficPrice= " + ele.getTrafficPrice());
//								
//							pstmt3.setBigDecimal(4, ele.getNums_days()); //取得訂票張數or租借天數
//							System.out.println("Nums_days= " + ele.getNums_days());
//								
//							pstmt3.setString(5, ele.getStartPoint()); //取得起始站位置
//							System.out.println("StartPoint= " + ele.getStartPoint());
//								
//							pstmt3.setString(6, ele.getDestination()); //取得起始站位置		
//							System.out.println("Destination= " + ele.getDestination());
//								
//							Timestamp ts2 = new Timestamp(ele.getDeparatureDate().getTime()); //出發日
//							pstmt3.setTimestamp(7, ts2); //出發日
//							System.out.println("DeparatureDate= " + ts2);
//								
//							pstmt3.setString(8, ele.getOrderType()); //交通類型0高鐵or1租車
//							System.out.println("OrderType= " + ele.getOrderType());
//							
//							pstmt3.setString(9, ele.getCustomerName()); //交通類型0高鐵or1租車
//							System.out.println("CustomerName= " + ele.getCustomerName());
//							
//							pstmt3.setString(10, ele.getCustomerPhone()); //交通類型0高鐵or1租車
//							System.out.println("CustomerPhone= " + ele.getCustomerPhone());
//							
//							pstmt3.setBigDecimal(11, ele.getT_sn_order()); //交通類型0高鐵or1租車
//							System.out.println("T_sn_order= " + ele.getT_sn_order());
//
//								
//							pstmt3.executeQuery();
//						}
//					}catch (SQLException ex) {
//						ex.printStackTrace();
//						throw new RuntimeException("發生SQL例外: " + ex.getMessage());
//					}
//				}
//			}
//			conn.commit();
//			a[0] = 1;
//
//		} catch (Exception e) {
//			a[0] = 0;
//			System.err.println("新增資料時發生錯誤:" + e);
//			conn.rollback();
//
//		} finally {
//			if (conn != null) {
//				conn.close();
//			}
//		}		
//		
//	}
	
	
	
	
}
