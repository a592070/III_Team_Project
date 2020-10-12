package rambo0021;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import utils.IOUtils;

/**
 * Servlet implementation class HomePageUpdateServlet
 */
@MultipartConfig
@WebServlet("/HomePageUpdateServlet")
public class HomePageUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePageUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//update db,session
		InputStream picture = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession(false);
		HomePageDAO homePageDAO = new HomePageDAO();
		SHA2DAO sha2 = new SHA2DAO();
		
		
		if (session == null) {
			response.sendRedirect(response.encodeRedirectURL(
					request.getContextPath() + "/rambo0021/login.jsp"));
			return;
		}
		AccountBean account = (AccountBean)session.getAttribute("Login");
	if(request.getPart("picture").getSize() > 0) { //有上傳照片
		System.out.println("有照片");
		picture = request.getPart("picture").getInputStream();
			
				try {
					account.setPicture(new SerialBlob(IOUtils.toByteArray(picture)));
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				account.setPassword(sha2.getSHA256(request.getParameter("password")));
				account.setNickName(request.getParameter("nickName"));
				account.setEmail(request.getParameter("email"));
				account.setModify_Date(new Date());
				homePageDAO.updateUserData(account);
	}
	picture.close();
	response.sendRedirect(request.getContextPath()+"/rambo0021/homePage.jsp");


}
}
