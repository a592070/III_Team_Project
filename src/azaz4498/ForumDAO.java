package azaz4498;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import controller.ConnectionPool;

public class ForumDAO {
	private Connection conn;
	private DataSource ds;
	private String sql;
	private Statement stmt;
	private static List<ArticleDO> articleList;
	private static List<CommentDO> commentList;
	private static List<ArticleTypeDO> artTypeList;

	public ForumDAO() throws IOException, SQLException {
		ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);

	}

	public void articleListInit() throws SQLException {
		articleList = new ArrayList<>();

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			sql = "SELECT * FROM f_article ORDER BY art_cre_time";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ArticleDO articleDO = new ArticleDO();
				
				articleDO.setArtId(rs.getString("art_id"));
				articleDO.setArtTypeId(rs.getString("art_type_id"));
				articleDO.setArtTitle(rs.getString("art_title"));
				articleDO.setArtUserId(rs.getString("art_userid"));
				articleDO.setArtContent(rs.getNString("art_content"));
				articleDO.setArtCommNum(rs.getInt("art_comm_num"));
				articleDO.setArtView(rs.getInt("art_view"));
				articleDO.setArtPic(rs.getString("art_pic"));
				articleDO.setArtCreTime(rs.getDate("art_cre_time"));

				articleList.add(articleDO);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			
		}

	}
	
	
	public void commentListInit() throws SQLException{
		commentList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			sql = "SELECT * FROM f_comment ORDER BY com_art_id";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				CommentDO commentDO= new CommentDO();
				
				commentDO.setComArtId(rs.getString("com_art_id"));
				commentDO.setComId(rs.getString("com_id"));
				commentDO.setComUserId(rs.getString("com_user_id"));
				commentDO.setComContent(rs.getNString("com_content"));
				commentDO.setComPic(rs.getString("com_pic"));
				commentDO.setComDate(rs.getDate("com_date"));
				
				commentList.add(commentDO);
				
				
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			
		}
	}

	public ForumDAO(int dataSourceType) throws IOException,SQLException{
		ds=ConnectionPool.getDataSource(dataSourceType);
		
		if (articleList==null||articleList.size()==0) {
			articleListInit();
		}
		if (commentList==null||commentList.size()==0) {
			commentListInit();
			
		}
	}
}
