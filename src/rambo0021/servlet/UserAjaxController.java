package rambo0021.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rambo0021.model.AccountBean;
import rambo0021.model.RegisterDAO;

/**
 * Servlet implementation class UserAjaxController
 */
@WebServlet("/UserAjaxController")
public class UserAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccountBean account = new AccountBean();
		RegisterDAO registerDAO = new RegisterDAO();
		System.out.println(request.getParameter("username"));
		account.setUserName(request.getParameter("username"));
		boolean checkUser = registerDAO.checkUser(account);
		System.out.println(checkUser);
		response.getWriter().println(checkUser);	
		}

}
