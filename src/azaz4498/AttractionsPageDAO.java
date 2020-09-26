package azaz4498;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import controller.ConnectionPool;
import pojo.AttractionsDO;

public class AttractionsPageDAO {
	private Connection conn;
	private DataSource ds;
	private static List<AttractionsPageVO> list;
	AttractionsPageVO attracionsPageVO = new AttractionsPageVO();

	public AttractionsPageDAO(int datasourceType) throws IOException {
		ds = ConnectionPool.getDataSource(datasourceType);
	}

	public void pageListInit() throws SQLException {
		list = new ArrayList<>();
		try {
			String sql = "SELECT NAME,PX,PY,DESCRIPTION FROM attractions";
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AttractionsPageVO attractionsPageVO = new AttractionsPageVO();
				AttractionsPageVO.setName(rs.getString("name"));
				AttractionsPageVO.setPx(rs.getBigDecimal("px"));
				AttractionsPageVO.setPy(rs.getBigDecimal("py"));
				AttractionsPageVO.setDescription(rs.getString("description"));

				list.add(AttractionsPageVO);
				
				
			}
			//print測試是否取得資料庫資料
			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<AttractionsDO> listAtrractionsPageDO() {
		return list;
	}

	public void addIntoList() throws SQLException {

		try {
			conn = ds.getConnection();

		} catch (SQLException e) {
			// TODO: handle exception
		}

	}




}
