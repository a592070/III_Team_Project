package azaz4498;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConnectionPool;

/**
 * Servlet implementation class NewComment
 */
@WebServlet("/NewCommentServlet")
public class NewCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		ForumDAO forumDAO;
		try {
			forumDAO = new ForumDAO(ConnectionPool.LOADING_WITH_SERVER);
			HttpSession session =request.getSession();
			int currArticleid = (int) session.getAttribute("currArticle");
			String commentString = request.getParameter("commentarea");
			System.out.println(commentString);
			System.out.println(session.getAttribute("currArticle"));
			System.out.println(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
			forumDAO.addNewComment(commentString, currArticleid, "azaz4498");
			
			RequestDispatcher rd = request.getRequestDispatcher("/ArticleServlet?artId="+currArticleid);
			rd.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
