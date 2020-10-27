package azaz4498.action;

import java.io.IOException;
import java.sql.SQLException;

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


@WebServlet("/NewCommentServlet")
public class NewCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public NewCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
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
			AccountBean account = (AccountBean) httpsession.getAttribute("Login");
			int currArticleid = (int) httpsession.getAttribute("currArticle");
			int currTypeid = (int) httpsession.getAttribute("currTypeId");
//			int typeid=Integer.valueOf(request.getParameter("art_TypeId"));
//			System.out.println("this is new comment servlet = "+typeid);
			String commentString = request.getParameter("commentarea");
			String commUserId = account.getUserName();
//			System.out.println(commentString);
//			System.out.println(session.getAttribute("currArticle"));
//			System.out.println(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
			forumDAO.newComment(commentString, currArticleid, commUserId);

			RequestDispatcher rd = request
					.getRequestDispatcher("/ArticleServlet?artId=" + currArticleid + "&art_TypeId=" + currTypeid);
			rd.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
