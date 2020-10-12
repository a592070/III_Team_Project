
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
import java.util.Date;
import java.util.Set;

import javax.sql.DataSource;

import controller.ConnectionPool;
import iring29.bean.R_OrderBean;
import pojo.OrderTableBean;
import rambo0021.AccountBean;

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
		for(T_OrderBean ele : traffic ) {
			if (ele.getOrderType().equals("0")) {
				try {

					conn = ds.getConnection();
					PreparedStatement pstmt3 = conn.prepareStatement(sqlT_Order_LiString);

//					for(T_OrderBean ele : traffic ) {
						pstmt3.setBigDecimal(1, ele.getOrder_id()); //ORDER_ID from ORDER_TABLE
						
						pstmt3.setInt(2, ele.getHsrDO().getSnSchedule());	//取得SnSchedule
							
						pstmt3.setBigDecimal(3, ele.getTrafficPrice()); //取得交通訂單總價
							
						pstmt3.setBigDecimal(4, ele.getNums_days()); //取得訂票張數or租借天數
							
						pstmt3.setString(5, ele.getStartPoint()); //取得起始站位置
							
						pstmt3.setString(6, ele.getDestination()); //取得起始站位置		
							
						Timestamp ts2 = new Timestamp(ele.getDeparatureDate().getTime()); //出發日
						pstmt3.setTimestamp(7, ts2); //出發日
							
						pstmt3.setString(8, ele.getOrderType()); //交通類型0高鐵or1租車
						
						pstmt3.setString(9, ele.getCustomerName()); 
						
						pstmt3.setString(10, ele.getCustomerPhone()); 
						
						pstmt3.setBigDecimal(11, ele.getT_sn_order());

//						System.out.println("order_id= " + ele.getOrder_id());
//						System.out.println("SnSchedule= " + ele.getHsrDO().getSnSchedule());
//						System.out.println("TrafficPrice= " + ele.getTrafficPrice());
//						System.out.println("Nums_days= " + ele.getNums_days());
//						System.out.println("StartPoint= " + ele.getStartPoint());
//						System.out.println("Destination= " + ele.getDestination());
//						System.out.println("DeparatureDate= " + ts2);
//						System.out.println("OrderType= " + ele.getOrderType());
//						System.out.println("CustomerName= " + ele.getCustomerName());
//						System.out.println("CustomerPhone= " + ele.getCustomerPhone());
//						System.out.println("T_sn_order= " + ele.getT_sn_order());
							
						pstmt3.executeQuery();
//					}
					conn.commit();
					rcd[0] = 1;

				}catch (SQLException ex) {
					conn.rollback();
					rcd[0] = 0;
					System.err.println("新增資料時發生錯誤:" + ex);
					conn.rollback();

//					ex.printStackTrace();
//					throw new RuntimeException("發生SQL例外: " + ex.getMessage());
				}
			}
		}

	}
	
	public void searchHistoricalOrder(ArrayList<ArrayList> combineArrayList, String userid) throws SQLException {
		ArrayList<OrderTableBean> tmp_orderTableBeans = new ArrayList<>();
		ArrayList<OrderTableBean> orderTableBeans = new ArrayList<>();
//		String sql = "select * from order_table o, t_order_list t " + 
//					 "where o.order_id = t.order_id(+) and " + 
//					 "o.username = " + "\'" + userid + "\'" +
//					 "order by o.order_id";
		String sql = "select * from order_table o, t_order_list t, highspeedrail h "
				+ "where o.order_id = t.order_id(+) "
				+ "and t.sn_schedule = h.sn_schedule(+) "
				+ "and o.username = " + "\'" + userid +"\'" 
				+ " order by o.order_id";

		AccountBean user = new AccountBean();

		try {
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				OrderTableBean oBean = new OrderTableBean();
				T_OrderBean tBean = new T_OrderBean();
				hsrDO hsrDO = new hsrDO();
				CarTypeBean carTypeBean = new CarTypeBean();
				
				oBean.setOrder_id(rs.getBigDecimal("ORDER_ID"));
				oBean.setOrder_date(rs.getTimestamp("ORDER_DATE"));
				
				user.setUserName(rs.getString("USERNAME"));
				user.setModify_Date(new Date());
				user.setRegister(new Date());
				oBean.setUser(user);
				
				tBean.setT_sn_order(rs.getBigDecimal("SN_ORDER"));
				hsrDO.setSnSchedule(rs.getInt("SN_SCHEDULE"));
				hsrDO.setIdHSR(rs.getString("ID_HSR"));
				tBean.setHsrDO(hsrDO);	//設置SN_SCHEDULE
				tBean.setTrafficPrice(rs.getBigDecimal("TICKETPRICE"));
				tBean.setNums_days(rs.getBigDecimal("NUMBERS_DAYS"));
				tBean.setStartPoint(rs.getString("STARTPOINT"));
				tBean.setDestination(rs.getString("DESTINATION"));
				tBean.setDeparatureDate(rs.getTimestamp("DEPARTUREDATE"));
				carTypeBean.setSn_carType(rs.getBigDecimal("SN_CARTYPE"));
				tBean.setCarTypeBean(carTypeBean);	//設置SN_CARTYPE
				tBean.setOrderType(rs.getString("ORDER_TYPE"));
				tBean.setCustomerName(rs.getString("NAME"));
				tBean.setCustomerPhone(rs.getString("PHONE"));
				
				oBean.addT_OderBean(tBean);
				
				if(!(tBean.getT_sn_order() == null)) {	//代表此筆訂單有交通部分
					orderTableBeans.add(oBean);
					if(orderTableBeans.size()>1) {
						int tmp = (orderTableBeans.get(orderTableBeans.size()-1 ).getOrder_id().compareTo(orderTableBeans.get(orderTableBeans.size()-2).getOrder_id())); //判斷當前物件跟上一個物件的orderID是否相同
						if(tmp != 0) {
							tmp_orderTableBeans.add(orderTableBeans.get(orderTableBeans.size()-1));	//將不同orderID的物件取出 存放到tmpList裡
							orderTableBeans.remove(orderTableBeans.size()-1);	//從orderTableBeans移除不同orderID的物件
							combineArrayList.add(orderTableBeans);	//將orderTableBeans存放到最終的List中
							orderTableBeans = new ArrayList<>();	//重製orderTableBeans
							orderTableBeans.add(tmp_orderTableBeans.get(0));	//將剛剛取出的物件從tmp放回orderTableBeans中
							tmp_orderTableBeans.clear();	//清除tmpList
						}
						System.out.println("orderTableBeans= " + orderTableBeans.size());
					}
				}
			}
			combineArrayList.add(orderTableBeans);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
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

