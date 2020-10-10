package iring29;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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

	// create order
	public void createOrder(OrderTableBean otbean) throws SQLException {
		
		String sqlOrder_Table = "insert into ORDER_TABLE (ORDER_DATE, USERNAME) values(?, ?)";

		String sqlR_Order_List = "insert into R_ORDER_LIST (ORDER_ID, R_SN, CUSTOMER_NUM, BOOK_TIME, DEPOSIT, CUS_NAME, CUS_PHONE) "
				+ "values(?, ?, ?, ?, ?, ?, ?)";

		String generatedColumn1[] = { "ORDER_ID" }; // 取得ORDER_ID Sequence
		try {
			// insert ORDER_DATA
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlOrder_Table, generatedColumn1);
			Timestamp ts = new Timestamp(System.currentTimeMillis()); // 下訂單時間
			pstmt.setTimestamp(1, ts); 
			pstmt.setString(2, otbean.getUser().getUserName()); // 取得userbean裡面的username
		

			pstmt.executeQuery();
			pstmt.clearBatch();
			BigDecimal order_id;
			ResultSet generatedKeys = pstmt.getGeneratedKeys();

			if (generatedKeys.next()) {
				order_id = generatedKeys.getBigDecimal(1); // 取得ORDER_ID from ORDER_TABLE
			} else {
				throw new RuntimeException("無法取得新增之ORDER_TABLE表格的主鍵");
			}
			
			Set<R_OrderBean> oBean = otbean.getR_OderBeans();
			try {
				PreparedStatement pstmt2 = conn.prepareStatement(sqlR_Order_List);
				
				for(R_OrderBean ele : oBean) {
					pstmt2.setBigDecimal(1, order_id); // ORDER_ID from ORDER_TABLE
					pstmt2.setBigDecimal(2, ele.getRestaurantBean().getR_sn());
					pstmt2.setBigDecimal(3, ele.getCustomerNum());
					Timestamp ts2 = new Timestamp(ele.getBooking_date().getTime()); // 設定要去餐廳的訂位日期
					pstmt2.setTimestamp(4, ts2);
					pstmt2.setBigDecimal(5, ele.getDeposit()); // 訂餐廳訂金
					pstmt2.setString(6, ele.getCustomerName());
					pstmt2.setString(7, ele.getCustomerPhone());
					
				}
				
				pstmt2.executeQuery();

			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("發生SQL例外: " + ex.getMessage());
			}
			conn.commit();
			pstmt.close();
			
		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	// find r_order
	public R_OrderBean findR_order_List(BigDecimal r_sn) throws SQLException {
		String sql = "select r_sn_order, max(order_id) order_id , customer_num, book_time, cus_name, cus_phone from r_order_list "
				+ "where r_sn = ? group by r_sn_order, customer_num, book_time, cus_name, cus_phone";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setBigDecimal(1, r_sn);
			ResultSet rs = pstmt.executeQuery();
			pstmt.clearBatch();

			R_OrderBean roBean = new R_OrderBean();
			while (rs.next()) {
				BigDecimal r_sn_order = rs.getBigDecimal("R_SN_ORDER");
				BigDecimal order_id = rs.getBigDecimal("ORDER_ID");
				BigDecimal c_num = rs.getBigDecimal("CUSTOMER_NUM");
				Timestamp b_time = rs.getTimestamp("BOOK_TIME");
				String cus_name = rs.getString("CUS_NAME");
				String cus_phone = rs.getString("CUS_PHONE");
				roBean.setR_sn_order(r_sn_order);
				roBean.setOrder_id(order_id);
				roBean.setCustomerNum(c_num);
				roBean.setBooking_date(b_time);
				roBean.setCustomerName(cus_name);
				roBean.setCustomerPhone(cus_phone);
			}
			return roBean;

		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();
			return null;

		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	// Delete Order
	public boolean cancelR_Order(BigDecimal r_sn_order) throws SQLException {
		String sql = "delete r_order_list where r_sn_order = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setBigDecimal(1, r_sn_order);
			pstmt.executeQuery();
			pstmt.clearBatch();
			conn.commit();
			pstmt.close();
			return true;
		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();
			return false;

		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	// set OrderTableBean for display HP
	public OrderTableBean findR_Order(BigDecimal r_sn) throws SQLException {
		String sql = "select * from R_ORDER_LIST where R_SN = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setBigDecimal(1, r_sn);
			ResultSet rs = pstmt.executeQuery();
			pstmt.clearBatch();
			
			 OrderTableBean otBean = new OrderTableBean();

			while (rs.next()) {
				BigDecimal r_sn_order = rs.getBigDecimal("R_SN_ORDER");
				BigDecimal orderID = rs.getBigDecimal("ORDER_ID");
				BigDecimal cus_num = rs.getBigDecimal("CUSTOMER_NUM");
				Timestamp ts = rs.getTimestamp("BOOK_TIME");
				BigDecimal deposit = rs.getBigDecimal("DEPOSIT");
				String cus_name = rs.getString("CUS_NAME");
				String cus_phone = rs.getString("CUS_PHONE");
				R_OrderBean roBean = new R_OrderBean(r_sn_order, orderID, ts, cus_num, deposit, null,cus_name,cus_phone);
				otBean.addR_OderBean(roBean);
			}
			rs.close();
			pstmt.close();
			return otBean;

		} catch (Exception e) {
			System.err.println("新增資料時發生錯誤:" + e);
			conn.rollback();
			return null;

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	
	//find booking name, phone
			public OrderTableBean BookData(BigDecimal order_id) throws SQLException {
				String sql = "select name, phone from order_data where sn = (select sn_no from order_table where order_id = ?)";
				
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setBigDecimal(1, order_id);
					ResultSet rs = pstmt.executeQuery();
					pstmt.clearBatch();
					OrderTableBean otBean = new OrderTableBean();
					while (rs.next()) {
						
//						otBean.setCustomerName(rs.getString("NAME"));
//						otBean.setCustomerPhone(rs.getString("PHONE"));
						
					}
					rs.close();
					pstmt.close();
					return otBean;
				}catch (Exception e) {
					System.err.println("新增資料時發生錯誤:" + e);
					conn.rollback();
					return null;

				} finally {
					if (conn != null) {
						conn.close();
					}
				}
				
			}
}
