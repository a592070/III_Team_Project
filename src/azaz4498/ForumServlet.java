package azaz4498;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConnectionPool;

/**
 * Servlet implementation class ForumServlet
 */
@WebServlet("/ForumServlet")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForumServlet() {
		super();
		
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
			List<ArticleDO> artList =forumDAO.articleList();
			
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			request.setAttribute("ErrorMsgKey", errorMsgMap);
			
			request.setAttribute("Article", artList);
			RequestDispatcher rd = request.getRequestDispatcher("azaz4498/Forum.jsp");
			rd.forward(request, response);
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		//還不知道放甚麼		
		//HttpSession session = request.getSession();
		
		

	}

}
