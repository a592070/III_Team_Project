package azaz4498;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import azaz4498.model.Article;
import azaz4498.model.ArticleType;
import azaz4498.model.ForumDAO;
import utils.HibernateUtil;

@WebServlet("/SelectArticleType")
public class SelectArticleType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public SelectArticleType() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		response.setContentType("text/html");
		ForumDAO forumDAO;
		try {
			forumDAO = new ForumDAO(session);
			int articleTypeId;
			articleTypeId = Integer.valueOf(request.getParameter("typeId"));
			List<Article> resultList = forumDAO.showArticlesByType(articleTypeId);
			List<ArticleType> typelist = forumDAO.showArticleType(articleTypeId);
			ArticleType type= typelist.get(0);
			request.setAttribute("Article", resultList);
			request.setAttribute("Type", type);
			RequestDispatcher rd = request.getRequestDispatcher("azaz4498/SelectedArticle.jsp");
			rd.forward(request, response);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
