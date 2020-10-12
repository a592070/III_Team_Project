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
import java.util.Set;

import javax.sql.DataSource;

//import org.apache.naming.java.javaURLContextFactory;

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

//	public ForumDAO() throws IOException, SQLException {
//		ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
//
//	}
	public ForumDAO(int dataSourceType) throws IOException, SQLException{
		ds = ConnectionPool.getDataSource(dataSourceType);
		articleListInit();

		artTypeListInit();
	}
	// 各清單初始化
	public  void articleListInit() throws SQLException{
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
				articleDO.setArtId(rs.getInt("art_id"));
				articleDO.setArtUserId(rs.getString("art_userid"));
				articleDO.setArtContent(rs.getNString("art_content"));
				articleDO.setArtCommNum(rs.getInt("art_comm_num"));
				articleDO.setArtView(rs.getInt("art_view"));
				articleDO.setArtPic(rs.getString("art_pic"));
				articleDO.setArtCreTime(rs.getDate("art_cre_time"));

				articleList.add(articleDO);
//				System.out.println("新增成功");

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

	/*
	public void commentListInit() throws SQLException {
		commentList = new ArrayList<>();
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			sql = "SELECT * FROM f_comment ORDER BY com_art_id";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				CommentDO commentDO = new CommentDO();

				commentDO.setComId(rs.getInt("com_id"));
				commentDO.setComArtId(rs.getInt("com_art_id"));
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
*/
	public void artTypeListInit() throws SQLException {
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

	

	//文章、評論、文章類型 清單
	public List<ArticleDO> articleList() {
		return articleList;
	}

	public List<CommentDO> commentList() {
		return commentList;
	}

	public List<ArticleTypeDO> articleTypeList() {
		return artTypeList;
	}

	//新增文章
	public void addNewArticle(String articleTitle, int articleTypeId, String articleContent, String userid) throws SQLException {
		try {
			conn = ds.getConnection();
			sql = "INSERT INTO f_article (ART_CONTENT, ART_CRE_TIME, ART_USERID, ART_COMM_NUM, ART_VIEW, ART_TYPE_ID, ART_TITLE) VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, articleContent);
			pstmt.setTimestamp(2, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
			pstmt.setString(3, userid);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, articleTypeId);
			pstmt.setString(7, articleTitle);

		
			pstmt.executeUpdate();
			conn.commit();
			pstmt.clearBatch();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			}
	}
	//新增評論
	
	public void addNewComment(String commentString, int artId, String userid) throws SQLException{
			
		try {
			conn=ds.getConnection();
			
			sql="INSERT INTO F_COMMENT (COM_CONTENT, COM_ART_ID, COM_USER_ID, COM_DATE) VALUES(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			
			
			pstmt.setNString(1, commentString);
			pstmt.setInt(2, artId);
			pstmt.setString(3, userid);
			pstmt.setTimestamp(4, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
			
			
			pstmt.executeUpdate();
			conn.commit();
			pstmt.clearBatch();
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			}
	}

	// 依文章ID刪除文章
	public void deletArticle(ArticleDO articleDO) throws SQLException {
		try {
			conn = ds.getConnection();
			sql = "DELETE FROM f_article WHERE art_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleDO.getArtId());

			pstmt.executeQuery();
			pstmt.clearBatch();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 依評論ID刪除評論
	public void deletComment(CommentDO commentDO) throws SQLException {
		try {
			conn = ds.getConnection();
			sql = "DELETE FROM f_comment WHERE com_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentDO.getComId());

			pstmt.executeQuery();
			pstmt.clearBatch();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//修改文章標題
	public void editArticleTitle(ArticleDO articleDO) throws SQLException{
		try {
			conn = ds.getConnection();
			sql = "UPDATE f_article SET art_title='?'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, articleDO.getArtTitle());

			pstmt.executeUpdate();
			pstmt.clearBatch();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//修改文章內容
	public void editArticleContent(ArticleDO articleDO) throws SQLException{
		try {
			conn = ds.getConnection();
			sql = "UPDATE f_article SET art_content='?'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, articleDO.getArtContent());

			pstmt.executeQuery();
			pstmt.clearBatch();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//修改評論內容
	public void editCommentArticleContent(CommentDO commentDO) throws SQLException{
			try {
				conn = ds.getConnection();
				sql = "UPDATE f_comment SET com_content='?'";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, commentDO.getComContent());

				pstmt.executeQuery();
				pstmt.clearBatch();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	//依類型查詢文章
	public void serchArticleByType(ArticleDO articleDO) throws SQLException{
		try {
			conn = ds.getConnection();
			sql = "SELECT * FROM F_ARTICLE WHERE art_type_id=?";
			//1=旅遊,2=住宿,3=美食,4=景點
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleDO.getArtTypeId());

			pstmt.executeQuery();
			pstmt.clearBatch();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//依文章id顯示文章
	public  ArticleDO showArticleByArticleId(int articleId) throws SQLException{
		ArticleDO articleDO=null;
		try {
			conn=ds.getConnection();
			
			sql="SELECT * FROM F_ARTICLE WHERE ART_ID=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,articleId);
			ResultSet rs= pstmt.executeQuery();
			if (rs.next()) {
				articleDO = new ArticleDO();
				articleDO.setArtId(articleId);
				articleDO.setArtTitle(rs.getString("ART_TITLE"));
				articleDO.setArtContent(rs.getNString("ART_CONTENT"));
				articleDO.setArtCreTime(rs.getDate("ART_CRE_TIME"));
				articleDO.setArtTypeId(rs.getInt("ART_TYPE_ID"));
				articleDO.setArtUserId(rs.getString("ART_USERID"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return articleDO;
	}
	//依文章id顯示評論
	public List<CommentDO> showCommentByArticleId(int commentArticleId) throws SQLException{
		commentList = new ArrayList<>();
		try {
			conn=ds.getConnection();
			sql="SELECT * FROM F_COMMENT WHERE COM_ART_ID=? ORDER BY COM_DATE";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,commentArticleId);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()) {
				CommentDO commentDO = new CommentDO();
				commentDO.setComArtId(commentArticleId);
				commentDO.setComContent(rs.getString("COM_CONTENT"));
				commentDO.setComUserId(rs.getString("COM_USER_ID"));
				commentDO.setComId(rs.getInt("COM_ID"));
				commentDO.setComDate(rs.getDate("COM_DATE"));
				
				commentList.add(commentDO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentList;
	}
	//依文章類型ID顯示文章類型
	public ArticleTypeDO showArtTypeByTypeId(int typeId) throws SQLException{
		ArticleTypeDO articleTypeDO = null;
		try {
			conn=ds.getConnection();
			sql="SELECT * FROM F_ART_TYPE WHERE TYPE_ID=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,typeId);
			ResultSet rs= pstmt.executeQuery();
			if (rs.next()) {
				articleTypeDO = new ArticleTypeDO();
				articleTypeDO.setTypeId(typeId);
				articleTypeDO.setTypeName(rs.getString("TYPE_NAME"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleTypeDO;
		
	}

}
