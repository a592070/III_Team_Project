package azaz4498.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import azaz4498.model.Article;
import azaz4498.model.ForumDAO;
import utils.HibernateUtil;

public class Test {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			ForumDAO fDao = new ForumDAO(session);
			List<Article> list = fDao.showAllArticles();

			for (Article article : list) {
				System.out.println("Title: " + article.getArtTitle());
				System.out.println("UserId:" + article.getArtUserId());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		HibernateUtil.closeSessionFactory();
	}
}
