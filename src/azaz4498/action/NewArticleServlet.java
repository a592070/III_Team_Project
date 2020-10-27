package azaz4498.action;

import java.io.IOException;
import java.sql.SQLException;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import azaz4498.model.ForumDAO;
import rambo0021.model.AccountBean;
import utils.HibernateUtil;


@WebServlet("/NewArticleServlet")
public class NewArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public NewArticleServlet() {

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
			HttpSession httpsession = request.getSession(false);
			String articleTitle = request.getParameter("article_title");
			int articleTypeSelect = Integer.parseInt(request.getParameter("article_type_select"));
			String articleContent = request.getParameter("article_content");
			if (httpsession.getAttribute("Login") != null) {
				AccountBean account = (AccountBean) httpsession.getAttribute("Login");
				String userName = account.getUserName();
				forumDAO.newArticle(articleTitle, articleTypeSelect, articleContent, userName);
//				System.out.println("文章標題是  " + articleTitle + " 文章類型是" + articleTypeSelect + "文章內容" + articleContent
//						+ "用戶ID" + userName);
			}

			RequestDispatcher rd = request.getRequestDispatcher("/ForumServlet");
			rd.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
