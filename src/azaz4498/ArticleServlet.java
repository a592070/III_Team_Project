package azaz4498;

import java.io.DataOutput;
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

import controller.ConnectionPool;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
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
			forumDAO = new ForumDAO(ConnectionPool.LOADING_WITH_SERVER);
			HttpSession session =request.getSession();
			int artid;
			int typeid;
			CommentDO commentDO = null;
			artid=Integer.valueOf(request.getParameter("artId"));
			typeid=Integer.valueOf(request.getParameter("art_TypeId"));
			
//			System.out.println("typeid 有沒有抓到 = "+typeid);
			List<CommentDO> commentList=forumDAO.showCommentByArticleId(artid);
			
			request.setAttribute("Article", forumDAO.showArticleByArticleId(artid));
			request.setAttribute("Comment", commentList);
			request.setAttribute("Type",forumDAO.showArtTypeByTypeId(typeid));
			session.setAttribute("currArticle",artid);
			session.setAttribute("currTypeId", typeid);
			
			RequestDispatcher rd = request.getRequestDispatcher("azaz4498/Article.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
