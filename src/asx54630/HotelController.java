package asx54630;

import javax.servlet.*;
import java.io.*;

import java.sql.*;
import java.util.List;

//import javax.rmi.*;
import javax.naming.*;
import javax.sql.*;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

import controller.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HotelController
 */
@WebServlet("/HotelController")
public class HotelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
	 public void init(ServletConfig config) throws ServletException
	 {
	   super.init(config);
	 }
	 
	 protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	   {
		
	  }
	 /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding(CHARSET_CODE);
		    response.setContentType(CONTENT_TYPE);
		    System.out.print("in");
	
			if (request.getParameter("search")!=null) 
				try {
					gotoSearchProcess(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			 
	}
	
	private void gotoSearchProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException, ServletException {
			String keyword = request.getParameter("keyword").trim();
			String regionkeywd = request.getParameter("regionkeywd").trim();
			String typekeywd = request.getParameter("typekeywd").trim();
			System.out.print("("+keyword+")");
			System.out.print("("+regionkeywd+")");
			System.out.print("("+typekeywd+")");
			
			HotelDAO hotelDAO = new HotelDAO(ConnectionPool.LOADING_WITH_SERVER);
			List<HotelDO> hoteldata = hotelDAO.hSearch(keyword,regionkeywd,typekeywd);
			request.setAttribute("hoteldata", hoteldata);
			System.out.print(hoteldata);
				if (hoteldata == null) request.getRequestDispatcher("/asx54630/SelectError.jsp").forward(request,response);
				else 	 request.getRequestDispatcher("/asx54630/HotelServiceResult.jsp").forward(request,response);
}
	
	
//	private void gotoDetailProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException, ServletException {
//		String detailsn = request.getParameter("detailsn").trim();
//		System.out.print("("+detailsn+")");
//		int detsn = Integer.parseInt(detailsn);
//		HotelDAO hotelDAO = new HotelDAO(ConnectionPool.LOADING_WITH_SERVER);
//		HotelDO detaildata = hotelDAO.DetailSearch(detsn);
//		request.setAttribute("detsn", detsn);
//		System.out.print(detsn);
//		request.getRequestDispatcher("/asx54630/MoreDetail.jsp").forward(request,response);
//}
	
}
