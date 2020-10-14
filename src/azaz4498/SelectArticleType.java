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

import controller.ConnectionPool;

/**
 * Servlet implementation class SelectArticleType
 */
@WebServlet("/SelectArticleType")
public class SelectArticleType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectArticleType() {
        super();
       
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
			forumDAO=new ForumDAO(ConnectionPool.LOADING_WITH_SERVER);
			int articleTypeId;
			articleTypeId=Integer.valueOf(request.getParameter("typeId"));
			List<ArticleDO> resultList=forumDAO.serchArticleByType(articleTypeId);
			request.setAttribute("Article", resultList);
			request.setAttribute("Type",forumDAO.showArtTypeByTypeId(articleTypeId));
			RequestDispatcher rd =request.getRequestDispatcher("azaz4498/SelectedArticle.jsp");
			rd.forward(request, response);
			
		} catch (IOException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
