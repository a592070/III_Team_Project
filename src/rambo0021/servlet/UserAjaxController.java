package rambo0021.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import rambo0021.model.AccountBean;
import rambo0021.model.Register;
import utils.HibernateUtil;



@WebServlet("/UserAjaxController")
public class UserAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Register register = new Register(session);
		
		System.out.println(request.getParameter("username"));
//		account.setUserName(request.getParameter("username"));
//		boolean checkUser = registerDAO.checkUser(account);
		boolean checkusr = register.checkusr(request.getParameter("username").trim());
		System.out.println(checkusr);
		response.getWriter().println(checkusr);	
		}

}
