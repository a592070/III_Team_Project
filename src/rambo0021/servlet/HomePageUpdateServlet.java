package rambo0021.servlet;

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

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import rambo0021.model.AccountBean;
import rambo0021.model.HomePage;
import rambo0021.model.SHA2DAO;
import utils.HibernateUtil;
import utils.IOUtils;

/**
 * Servlet implementation class HomePageUpdateServlet
 */
@MultipartConfig
@WebServlet("/HomePageUpdateServlet")
public class HomePageUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// update db,session
//		InputStream picture = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		HttpSession hsession = request.getSession(false);
		SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        HomePage homePage = new HomePage(session);
        
		if (hsession == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/rambo0021/login.jsp"));
			return;
		}
		AccountBean account = (AccountBean) hsession.getAttribute("Login");
		System.out.println(request.getPart("picture").getSize());
		if (request.getPart("picture").getSize() > 0) { // 有上傳照片
			System.out.println("有上傳照片");
			InputStream picture = request.getPart("picture").getInputStream();

			try {
				account.setPicture(picture.readAllBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

			picture.close();
		}
		account.setPassword(SHA2DAO.getSHA256(request.getParameter("password")));
		account.setNickName(request.getParameter("nickName"));
		account.setEmail(request.getParameter("email"));
		account.setModify_Date(new Date());
		homePage.update(account);
		response.sendRedirect(request.getContextPath() + "/rambo0021/homePage.jsp");

	}
}
