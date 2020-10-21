package asx54630.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import asx54630.model.HotelBean;
import asx54630.model.HotelDAO;
import utils.HibernateUtil;

/**
 * Servlet implementation class DemoHotelServlet
 */
@WebServlet("/DemoHotelServlet")
public class DemoHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		HotelDAO hDao = new HotelDAO(session);
		HotelBean hBean = hDao.select(1006);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.write("HotelName:" + hBean.getNAME() + "<br/>");
		out.write("HotelAddress:" + hBean.getADDRESS() + "<br/>");
		
		out.close();
		
	}

}
