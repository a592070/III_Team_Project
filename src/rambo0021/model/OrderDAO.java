package rambo0021.model;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.ConnectionPool;

public class OrderDAO {
	public List<OrderBean> selectOrder(AccountBean account) {
		List<OrderBean> list = new ArrayList<>();
		try {
			DataSource dataSource = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITH_SERVER);
			Connection connection = dataSource.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("select ORDER_ID,ORDER_DATE from ORDER_TABLE where USERNAME=?");
		    prepareStatement.setString(1,account.getUserName());
		    ResultSet rs = prepareStatement.executeQuery();
		    while (rs.next()) {
		    	OrderBean order = new OrderBean();
		    	order.setOrderId(rs.getInt("ORDER_ID"));
		    	order.setOrderDate(rs.getDate("ORDER_DATE"));
		    	list.add(order);
		    }
		    prepareStatement.clearParameters();
			prepareStatement.close();
			connection.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return list;

}
	public OrderBean selectOrderlist(OrderBean order){
		
		try {
			DataSource dataSource = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITH_SERVER);
			Connection connection = dataSource.getConnection();
			//餐廳
			PreparedStatement prepareStatement = connection.prepareStatement("select R_SN_ORDER from R_ORDER_LIST where ORDER_ID=?");
						prepareStatement.setInt(1,order.getOrderId());
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				order.setR_orderId(rs.getInt("R_SN_ORDER"));
			}
			prepareStatement.clearParameters();
			prepareStatement.close();
				
			//住宿
			PreparedStatement prepareStatement2 = connection.prepareStatement("select SN_ORDER from H_ORDER_LIST where ORDER_ID=?");
		
			prepareStatement2.setInt(1,order.getOrderId());
			ResultSet rs2 = prepareStatement2.executeQuery();
			while(rs2.next()) {
				order.setH_orderId(rs2.getInt("SN_ORDER"));
			}
			prepareStatement2.clearParameters();
			prepareStatement2.close();
			
			//交通
			PreparedStatement prepareStatement3 = connection.prepareStatement("select SN_ORDER from T_ORDER_LIST  where ORDER_ID=?");
			prepareStatement3.setInt(1,order.getOrderId());
			ResultSet rs3 = prepareStatement3.executeQuery();
			while(rs3.next()) {
				order.setT_orderId(rs3.getInt("SN_ORDER"));
			}
			prepareStatement3.clearParameters();
			prepareStatement3.close();
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
}
