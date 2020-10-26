<<<<<<< HEAD
package asx54630.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import asx54630.model.HotelBean;
import asx54630.model.HotelBeanService;
import asx54630.model.HotelDAO;
import utils.HibernateUtil;


@WebServlet("/HotelServlet")
public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";   
	
	 public void init(ServletConfig config) throws ServletException
	 {
	   super.init(config);
	 }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding(CHARSET_CODE);
		    response.setContentType(CONTENT_TYPE);
		    System.out.print("in");
	
			if (request.getParameter("search")!=null) 
				try {
					gotoSearchProcess(request,response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	private void gotoSearchProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String keyword = request.getParameter("keyword").trim();
		String regionkeywd = request.getParameter("regionkeywd").trim();
		String typekeywd = request.getParameter("typekeywd").trim();
		System.out.print("("+keyword+")");
		System.out.print("("+regionkeywd+")");
		System.out.print("("+typekeywd+")");
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		HotelBeanService hService = new HotelBeanService(session);
		List<HotelBean> hoteldata = hService.selectAll(keyword,regionkeywd,typekeywd);
		request.setAttribute("hoteldata", hoteldata);
		System.out.print(hoteldata);
//		HotelBean hBean = hService.selectAll(bd);
		request.getRequestDispatcher("/asx54630/HotelServiceResult.jsp").forward(request,response);
		
		
	}

=======
package asx54630.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import asx54630.model.HotelBean;
import asx54630.model.HotelBeanService;
import asx54630.model.HotelDAO;
import utils.HibernateUtil;


@WebServlet("/HotelServlet")
public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";   
	
	 public void init(ServletConfig config) throws ServletException
	 {
	   super.init(config);
	 }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding(CHARSET_CODE);
		    response.setContentType(CONTENT_TYPE);
		    System.out.print("in");
	
			if (request.getParameter("search")!=null) 
				try {
					gotoSearchProcess(request,response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	private void gotoSearchProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String keyword = request.getParameter("keyword").trim();
		String regionkeywd = request.getParameter("regionkeywd").trim();
		String typekeywd = request.getParameter("typekeywd").trim();
		System.out.print("("+keyword+")");
		System.out.print("("+regionkeywd+")");
		System.out.print("("+typekeywd+")");
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		HotelBeanService hService = new HotelBeanService(session);
		List<HotelBean> hoteldata = hService.selectAll(keyword,regionkeywd,typekeywd);
		request.setAttribute("hoteldata", hoteldata);
		System.out.print(hoteldata);
//		HotelBean hBean = hService.selectAll(bd);
		request.getRequestDispatcher("/asx54630/HotelServiceResult.jsp").forward(request,response);
		
		
	}

>>>>>>> 223ad593d563fbddf72cfcec28aa3247d9fe48a7
}