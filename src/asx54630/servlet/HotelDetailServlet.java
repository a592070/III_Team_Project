package asx54630.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import asx54630.model.HotelBean;
import asx54630.model.HotelBeanService;
import utils.HibernateUtil;

@WebServlet(name="HotelDetailServlet",urlPatterns ="/HotelDetailServlet")
public class HotelDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HotelDetailServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String detailsn = request.getParameter("detailsn").trim();
		System.out.print("("+detailsn+")");
		BigDecimal detsn = new BigDecimal(detailsn);
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		HotelBeanService hService = new HotelBeanService(session);
		HotelBean detaildata = null;
		detaildata = hService.hotelDetail(detsn);
		request.setAttribute("detaildata", detaildata);
		System.out.print(detaildata);
		request.getRequestDispatcher("/asx54630/MoreDetail.jsp").forward(request,response);
	}

}

