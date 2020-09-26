package azaz4498;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.ConnectionPool;
import pojo.AttractionsDO;

public class AttractionsPageDAO {
	private Connection conn;
	private DataSource ds;
	private static List<AttractionsDO> list;
	AttractionsDO attracionsDO = new AttractionsDO();

	public AttractionsPageDAO() throws IOException {
		ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITH_SERVER);
	}

	public void pageListInit() throws SQLException {
		list = new ArrayList<>();
		try {
			String sql = "SELECT NAME,PX,PY,DESCRIPTION FROM attractions";
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				AttractionsDO attractionsDO = new AttractionsDO();
				attractionsDO.setName(rs.getString("name"));
				attractionsDO.setPx(rs.getBigDecimal("px"));
				attractionsDO.setPy(rs.getBigDecimal("py"));
				attractionsDO.setDescription(rs.getString("description"));
				
				list.add(attractionsDO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void addIntoList() throws SQLException {
		
		try {
			conn=ds.getConnection();
			
		} catch (SQLException e) {
			// TODO: handle exception
		}

	}
}
