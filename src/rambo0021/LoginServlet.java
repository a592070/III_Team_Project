package rambo0021;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = new AccountBean();
        HomePageDAO homePage = new HomePageDAO();
        HttpSession session = request.getSession();
        Map<String, String> errorMsgMap = new HashMap<String, String>();
        request.setAttribute("ErrorMsgKey", errorMsgMap);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8"); 
		account.setUserName(request.getParameter("userName").trim());	
		String password=request.getParameter("password").trim();	
		homePage.selectUserData(account);
		if (password.equals(account.getPassword())) {
			session.setAttribute("Login",account);
			response.sendRedirect("/III_Team_Project/rambo0021/index.jsp");
		}else {
			errorMsgMap.put("LoginError", "帳號或密碼錯誤");
			RequestDispatcher rd = request.getRequestDispatcher("/rambo0021/login.jsp");
			rd.forward(request, response);
			return;
		}
		
	}

}
