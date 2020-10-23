package azaz4498.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;

public class ForumDAO {
	private Session session;

	public ForumDAO(Session session) {
		this.session = session;
	}

	// 顯示文章列表
	public List<Article> showAllArticles() {
		Query<Article> query = session.createQuery("From Article", Article.class);
		List<Article> list = query.list();
		return list;
	}

	// 依文章顯示評論
	public List<Comment> showCommentsByArticle(int articleId) throws SQLException {
		Query<Comment> query = session.createQuery("From Comment where COM_ART_ID=?1", Comment.class);
		query.setParameter(1, articleId);
		List<Comment> list = query.list();
		return list;
	}
	//依類型顯示文章
	public List<Article> showArticlesByType(int typeId) throws SQLException{
		Query<Article> query = session.createQuery("From Article where ART_TYPE_ID=?1",Article.class);
		query.setParameter(1, typeId);
		List<Article> list = query.list();
		return list;
	}

	// 新增文章
	public void newArticle(String title, int typeId, String content, String userId) throws SQLException {
		Article article = new Article();
		ArticleType type = new ArticleType();
		article.setArtTitle(title);
		article.setArtContent(content);
		article.setArtUserId(userId);
		article.setArtCreTime(new Date(Calendar.getInstance().getTime().getTime()));
		article.setArtCommNum(0);
		article.setArtView(0);
		article.setArtPic("default");
		type.setTypeId(typeId);
		article.setArticleType(type);
		session.save(article);
		System.out.println("New article created !");

	}

	// 新增評論
	public void newComment(String content, int articleId, String userid) throws SQLException {
		Comment comment = new Comment();
		Article article = new Article();
		comment.setComDate(new Date(Calendar.getInstance().getTime().getTime()));
		comment.setComContent(content);
		comment.setComUserId(userid);
		comment.setComPic("default");
		article.setArtId(articleId);
		comment.setArticle(article);
		session.save(comment);
		System.out.println("New comment created !");
	}

	// 刪除文章
	public boolean deleteArticle(int articleId) {
		Article result = session.get(Article.class, articleId);
		if (result != null) {
			session.delete(result);
			System.out.println("文章已刪除!");
			return true;
		}

		return false;

	}

	// 刪除評論
	public boolean deleteComment(int commentId) {
		Comment result = session.get(Comment.class, commentId);
		if (result != null) {
			session.delete(result);
			System.out.println("評論已刪除");
			return true;
		}
		return false;
	}

}
