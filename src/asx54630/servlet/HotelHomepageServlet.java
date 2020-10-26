<<<<<<< HEAD:src/asx54630/servlet/HotelHomepageServlet.java
package asx54630.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import asx54630.model.HotelBean;
import asx54630.model.HotelBeanService;


import pojo.OrderTableBean;
import rambo0021.model.AccountBean;
import utils.HibernateUtil;


@WebServlet("/ HotelHomepageServlet")
public class HotelHomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HotelHomepageServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		AccountBean aBean = (AccountBean) session.getAttribute("Login");
		if (aBean.getUserName() != null) {
			try {
				SelectHomePage(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
	public void SelectHomePage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		HttpSession session = request.getSession(false);
		AccountBean aBean = (AccountBean) session.getAttribute("Login");
		String hhomepage = aBean.getUserName();
		System.out.println("業者名稱"+hhomepage);
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session1 = factory.getCurrentSession();
		
		HotelBeanService hService = new HotelBeanService(session1);
		HotelBean hoteldata = hService.hotelHomePage(hhomepage);
		request.setAttribute("hoteldata", hoteldata);
		
		request.getSession().setAttribute("hoteldata", hoteldata);
		System.out.println("店家資料:"+hoteldata);
		request.getRequestDispatcher("asx54630/H_HomePage.jsp").forward(request, response);
	}

}
=======
package asx54630.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import asx54630.model.HotelBean;
import asx54630.model.HotelBeanService;


import pojo.OrderTableBean;
import rambo0021.model.AccountBean;
import utils.HibernateUtil;


@WebServlet("/ HotelHomepageServlet")
public class HotelHomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HotelHomepageServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		AccountBean aBean = (AccountBean) session.getAttribute("Login");
		if (aBean.getUserName() != null) {
			try {
				SelectHomePage(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
	public void SelectHomePage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		HttpSession session = request.getSession(false);
		AccountBean aBean = (AccountBean) session.getAttribute("Login");
		String hhomepage = aBean.getUserName();
		System.out.println("業者名稱"+hhomepage);
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session1 = factory.getCurrentSession();
		
		HotelBeanService hService = new HotelBeanService(session1);
		HotelBean hoteldata = hService.hotelHomePage(hhomepage);
		request.setAttribute("hoteldata", hoteldata);
		
		request.getSession().setAttribute("hoteldata", hoteldata);
		System.out.println("店家資料:"+hoteldata);
		request.getRequestDispatcher("asx54630/H_HomePage.jsp").forward(request, response);
	}

}
>>>>>>> 223ad593d563fbddf72cfcec28aa3247d9fe48a7:src/asx54630/H_HomepageServlet.java
