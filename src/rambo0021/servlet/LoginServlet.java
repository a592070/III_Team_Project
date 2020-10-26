package rambo0021.servlet;

import java.io.IOException;
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

import rambo0021.model.AccountBean;
import rambo0021.model.SHA2DAO;
import rambo0021.model.VerifyRecaptcha;
import rambo0021.old.HomePageDAO;

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
        SHA2DAO sha2 = new SHA2DAO();
//        OrderDAO orderDAO = new OrderDAO();
        HttpSession session = request.getSession();
        Map<String, String> errorMsgMap = new HashMap<String, String>();       
        request.setAttribute("ErrorMsgKey", errorMsgMap);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8"); 
		account.setUserName(request.getParameter("userName").trim());	
		String password=sha2.getSHA256(request.getParameter("password").trim());
		
		//圖靈測試
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
		//查密碼
		homePage.selectUserData(account);
		//判斷帳密&驗證
		if (password.equals(account.getPassword()) && verify) {
			session.setAttribute("Login",account);
//			List<OrderBean> list = orderDAO.selectOrder(account);
//			session.setAttribute("Order", list);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}else if(!verify) {
			errorMsgMap.put("LoginError", "驗證不通過");
			RequestDispatcher rd = request.getRequestDispatcher("/rambo0021/login.jsp");
			rd.forward(request, response);
			return;
		}
		else {
			errorMsgMap.put("LoginError", "帳號或密碼錯誤");
			RequestDispatcher rd = request.getRequestDispatcher("/rambo0021/login.jsp");
			rd.forward(request, response);
			return;
		}
		
		
	}

}
