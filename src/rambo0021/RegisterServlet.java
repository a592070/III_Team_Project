package rambo0021;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
		Account account = new Account();
		RegisterDAO registerDAO = new RegisterDAO();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");   //setup response content type
		response.setCharacterEncoding("UTF-8"); //setup response character encoding type
		
		String username = request.getParameter("username");
		account.setUsername(username);	
		String password = request.getParameter("password");
		account.setPassword(password);	
		String email = request.getParameter("email");
		account.setEmail(email);
		InputStream pictrure = request.getPart("picture").getInputStream();
		account.setPicture(pictrure);
		System.out.println("Servlet is on");
		registerDAO.insertData(account);
		pictrure.close();
		
	}

}
