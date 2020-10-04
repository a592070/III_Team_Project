package rambo0021;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Homepage
 */
@WebServlet("/Homepage")
public class HomepagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     String homepage="homePage.jsp";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomepagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HomePageDAO homePageDAO = new HomePageDAO();
		AccountBean account = new AccountBean();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8"); 
		String username = request.getParameter("username"); 
		account.setUserName(username);
		homePageDAO.selectUserData(account);
		request.setAttribute("account",account);
	  RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
	  if (dispatcher != null) {
		  dispatcher.forward(request, response);
	  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
