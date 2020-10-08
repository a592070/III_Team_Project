package azaz4498;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import controller.ConnectionPool;

public class ForumDAO {
	private Connection conn;
	private DataSource ds;
	private String sql;
	private Statement stmt;
	private PreparedStatement pstmt;
	private static List<ArticleDO> articleList;
	private static List<CommentDO> commentList;
	private static List<ArticleTypeDO> artTypeList;

	public ForumDAO() throws IOException, SQLException {
		ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);

	}
	//各清單初始化
	public void articleListInit() throws SQLException {
		articleList = new ArrayList<>();

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			sql = "SELECT * FROM f_article ORDER BY art_cre_time";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ArticleDO articleDO = new ArticleDO();
			
				articleDO.setArtTypeId(rs.getInt("art_type_id"));
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
	public void artTypeListInit() throws SQLException{
		artTypeList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			sql = "SELECT * FROM f_art_type ORDER BY type_id";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ArticleTypeDO articleTypeDO = new ArticleTypeDO();
				
				articleTypeDO.setTypeId(rs.getInt("type_id"));
				articleTypeDO.setTypeName(rs.getString("type_name"));
				
				artTypeList.add(articleTypeDO);

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
		if (artTypeList==null||artTypeList.size()==0) {
			artTypeListInit();
		}
	}
	
//文章、評論、文章類型 清單
	public List<ArticleDO> artileList(){
		return articleList;
	}
	
	public List<CommentDO> commentList(){
		return commentList;
	}
	
	public List<ArticleTypeDO> articleTypeList(){
		return artTypeList;
	}
//新增文章方法
	public void addNewArticle(ArticleDO articleDO)throws SQLException{
		try {
			conn = ds.getConnection();
			sql = "INSERT INTO f.article (ART_CONTENT, ART_CRE_TIME, ART_USERID, ART_COMM_NUM, ART_VIEW, ART_TYPE_ID, ART_TITLE) VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1,articleDO.getArtContent());
			pstmt.setTimestamp(2, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
			pstmt.setString(3, articleDO.getArtUserId());
			pstmt.setInt(4, articleDO.getArtCommNum());
			pstmt.setInt(5, articleDO.getArtView());
			pstmt.setInt(6, articleDO.getArtTypeId());
			pstmt.setString(7, articleDO.getArtTitle());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
