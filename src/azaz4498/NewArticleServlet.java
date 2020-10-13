package azaz4498;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import controller.ConnectionPool;
import rambo0021.AccountBean;

/**
 * Servlet implementation class NewArticleServlet
 */
@WebServlet("/NewArticleServlet")
public class NewArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewArticleServlet() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		ForumDAO forumDAO;
		try {
			forumDAO = new ForumDAO(ConnectionPool.LOADING_WITH_SERVER);
			HttpSession session = request.getSession(false);
			String articleTitle = request.getParameter("article_title");
			int articleTypeSelect = Integer.parseInt(request.getParameter("article_type_select"));
			String articleContent = request.getParameter("article_content");
			if (session.getAttribute("Login") != null) {
				AccountBean account = (AccountBean) session.getAttribute("Login");
				String userName = account.getUserName();
				forumDAO.addNewArticle(articleTitle, articleTypeSelect, articleContent,userName );
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
