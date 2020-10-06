package azaz4498;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import controller.ConnectionPool;

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
			String sql = "SELECT * FROM attractions";
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AttractionsPageVO attractionsPageVO = new AttractionsPageVO();
				
				attractionsPageVO.setName(rs.getString("name"));
				attractionsPageVO.setDescription(rs.getString("description"));
				attractionsPageVO.setAddress(rs.getString("address"));
				attractionsPageVO.setTel(rs.getString("tel"));
				attractionsPageVO.setPx(rs.getBigDecimal("px"));
				attractionsPageVO.setPy(rs.getBigDecimal("py"));
				attractionsPageVO.setOpentime(rs.getString("opentime"));
				attractionsPageVO.setTraveling_info(rs.getString("traveling_info"));
				attractionsPageVO.setTotal_number_rooms(rs.getString("total_number_rooms"));
				attractionsPageVO.setService_info(rs.getString("service_info"));
				attractionsPageVO.setRating(rs.getBigDecimal("rating"));
				attractionsPageVO.setRegion(rs.getString("region_name"));
				
				list.add(attractionsPageVO);
				
			}
//			print測試是否取得資料庫資料
//			System.out.println(list);

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
			conn = ds.getConnection();
			String sql = "INSERT INTO ACCOUNT (FAVORITE) VALUES('?') WHERE IDENTITY='?'";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"C1_376490000A_100130" );  //暫時使用第一筆資料作為加入清單依據，如何獲取當前頁面景點ID待解決。
			pstmt.setString(2, "username");
			pstmt.executeQuery();

		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if (conn!=null) {
				conn.close();
			}
		}

	}




}
