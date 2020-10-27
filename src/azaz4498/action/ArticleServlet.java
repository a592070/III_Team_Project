package azaz4498.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import azaz4498.model.Article;
import azaz4498.model.ArticleType;
import azaz4498.model.Comment;
import azaz4498.model.ForumDAO;
import utils.HibernateUtil;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	public ArticleServlet() {
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
			HttpSession httpsession = request.getSession();
			int artid;
			int typeid;
			Comment comment = null;
			artid = Integer.valueOf(request.getParameter("artId"));
			typeid = Integer.valueOf(request.getParameter("art_TypeId"));

//			System.out.println("typeid 有沒有抓到 = " + artid);
			List<Comment> commentList = forumDAO.showCommentsByArticle(artid);
			List<Article> articlelist = forumDAO.showArticleById(artid);
			List<ArticleType> typelist = forumDAO.showArticleType(typeid);
			ArticleType type = typelist.get(0);
			Article article = articlelist.get(0);

			request.setAttribute("Article", article);
			request.setAttribute("Comment", commentList);
			request.setAttribute("Type", type);
			httpsession.setAttribute("currArticle", artid);
			httpsession.setAttribute("currTypeId", typeid);

			RequestDispatcher rd = request.getRequestDispatcher("azaz4498/Article.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
