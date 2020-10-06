package rambo0021;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.IOUtils;


/**
 * Servlet implementation class registerServlet
 */
@MultipartConfig
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		 
//		
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccountBean account = new AccountBean();
		RegisterDAO registerDAO = new RegisterDAO();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8"); 
		
		String username = request.getParameter("username").trim();
		account.setUserName(username);	
		String password = request.getParameter("password").trim();
		account.setPassword(password);	
		int identity = Integer.parseInt(request.getParameter("identity").trim());
		account.setIdentity(identity);
		String email = request.getParameter("email").trim();
		account.setEmail(email);
		InputStream pictrure = request.getPart("picture").getInputStream();
		// TODO
		account.setPicture(null);
		
		Date date = new Date();
		account.setModify_Date(date);
		account.setRegister(date);
		String nickname = request.getParameter("nickname").trim();
		account.setNickName(nickname);
		System.out.println("Servlet is on");
		registerDAO.insertData(account);
		pictrure.close();
		
	}

}
