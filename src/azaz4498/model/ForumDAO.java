package azaz4498.model;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;

public class ForumDAO {
	private Session session;
	
	public ForumDAO(Session session) {
		this.session=session;
	}
	public List<Article> showAllArticles(){
		Query<Article> query=session.createQuery("From Article",Article.class);
		List<Article> list=query.list();
		return list;
	}
}
