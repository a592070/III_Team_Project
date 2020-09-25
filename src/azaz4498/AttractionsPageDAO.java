package azaz4498;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import controller.ConnectionPool;
import pojo.AttractionsDO;

public class AttractionsPageDAO {
	private Connection conn;
	private DataSource ds;
	AttractionsDO attracionsDO = new AttractionsDO();

	public AttractionsPageDAO() throws IOException {
		ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITH_SERVER);
	}

	public void pageInit() throws SQLException {

		try {
			String sql = "SELECT NAME,PX,PY,DESCRIPTION FROM attractions WHERE ID='?'";
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, attracionsDO.getId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				AttractionsDO attractionsDO = new AttractionsDO();
				attractionsDO.setName(rs.getString("name"));
				attractionsDO.setPx(rs.getBigDecimal("px"));
				attractionsDO.setPy(rs.getBigDecimal("py"));
				attractionsDO.setDescription(rs.getString("description"));
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
