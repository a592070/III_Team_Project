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

	// 依類型顯示文章
	public List<Article> showArticlesByType(int typeId) throws SQLException {
		Query<Article> query = session.createQuery("From Article where ART_TYPE_ID=?1", Article.class);
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
		Article article2 = session.get(Article.class, articleId);
		int commNum = article2.getArtCommNum();
		article2.setArtCommNum(commNum + 1);
		comment.setComDate(new Date(Calendar.getInstance().getTime().getTime()));
		comment.setComContent(content);
		comment.setComUserId(userid);
		comment.setComPic("default");
		article.setArtId(articleId);
		comment.setArticle(article);
		session.save(comment);
		session.save(article2);
		System.out.println("New comment created !");
	}

	// 刪除文章
	public boolean deleteArticle(int articleId, String userid) {
		Article result = session.get(Article.class, articleId);
		String op = result.getArtUserId();
		if (result != null && userid.equals(op)) {
			session.delete(result);
			System.out.println("文章已刪除!");
			return true;
		}

		return false;

	}

	// 刪除評論
	public boolean deleteComment(int commentId, String userid) {
		Comment result = session.get(Comment.class, commentId);
		String op = result.getComUserId();
		if (result != null &&  op.equals(userid)) {
			Article article = result.getArticle();
			int articleId = article.getArtId();
			Article article2 = session.get(Article.class, articleId);
			int commNum = article2.getArtCommNum();
			article2.setArtCommNum(commNum - 1);
			session.save(article2);
			session.delete(result);
			System.out.println("評論已刪除");
			return true;
		}
		return false;
	}

	// 編輯文章
	public Article articleEdit(String title, String content, int articleId, String userid) throws SQLException {
		Article result = session.get(Article.class, articleId);
		String op = result.getArtUserId();
		if (result != null && userid.equals(op)) {
			Query<Article> query = session.createQuery(
					"update F_ARTICLE article set article.ART_CONTENT=?1,article.ART_TITLE=?2 where ART_ID=?3",
					Article.class);
			query.setParameter(1, content);
			query.setParameter(2, title);
			query.setParameter(3, articleId);
			query.executeUpdate();
		}
		return result;
	}

	// 編輯評論
	public Comment commentEdit(String content, int commentId, String userid) throws SQLException {
		Comment result = session.get(Comment.class, commentId);
		String op = result.getComUserId();
		if (result != null && userid.equals(op)) {
			Query<Comment> query = session.createQuery("update Comment set COM_CONTENT=?1 where COM_ID=?2",
					Comment.class);
			query.setParameter(1, content);
			query.setParameter(2, commentId);
			query.executeUpdate();
		}
		return result;

	}

	public Long getNumOfComment(int articleId) throws SQLException {

		Article result = session.get(Article.class, articleId);
		if (result != null) {
			Query query = session.createQuery("Select Count(*) From Comment where COM_ART_ID=?1");
			query.setParameter(1, articleId);
			Long commentNum = (Long) query.uniqueResult();
			return commentNum;
		}
		return (Long) null;

	}
}
