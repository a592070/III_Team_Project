package iring29;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConnectionPool;
import iring29.bean.RestaurantBean;

@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public RestaurantServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		HttpSession session = request.getSession(false);
		if (request.getParameter("QUERY") != null) {

			if (request.getParameter("restaurant_name") != "" && request.getParameter("region_name") != null) {
				try {
					processMultiQuery(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (request.getParameter("restaurant_name") != "") {
				try {
					processQueryRestaurant(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (request.getParameter("region_name") != null) {
				try {
					processQueryRegion(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (request.getParameter("go") != null) {
			try {
				System.out.println("in go");
				processQueryRestaurant(request, response);
			} catch (IOException | SQLException | ServletException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void processMultiQuery(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		String name = request.getParameter("restaurant_name").trim();
		String region = request.getParameter("region_name").trim();
		String book_date = request.getParameter("book_date").trim();
		String person_numer = request.getParameter("person_numer").trim();
		request.getSession().setAttribute("book_date", book_date);
		request.getSession().setAttribute("person_numer", person_numer);
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);

		if(name != "" && region != "") {
			List<RestaurantBean> Multi_Rdata = restaurantDAO.findMulti_Name_Region(name, region);
			request.getSession().setAttribute("Multi_Rdata", Multi_Rdata);
			request.getRequestDispatcher("/iring29/MultiRestaurant.jsp").forward(request, response);
			
		}
	}

	public void processQueryRestaurant(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String name = request.getParameter("restaurant_name").trim();
		String book_date = request.getParameter("book_date").trim();
		String person_numer = request.getParameter("person_numer").trim();
		request.getSession().setAttribute("book_date", book_date);
		request.getSession().setAttribute("person_numer", person_numer);
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);

		
			int numR = restaurantDAO.numRestaurant(name);
			System.out.println(numR);
			
		
			if(numR == 1) {
				RestaurantBean res_data = restaurantDAO.findRestaurant(name);
				System.out.println(name);
				request.getSession().setAttribute("res_data", res_data);
				request.getRequestDispatcher("/iring29/DisplayRestaurant.jsp").forward(request, response);
			}
			else if(numR > 1) {
			List<RestaurantBean> Multi_Rdata = restaurantDAO.findMulti_R(name);
			request.getSession().setAttribute("Multi_Rdata", Multi_Rdata);
			request.getRequestDispatcher("/iring29/MultiRestaurant.jsp").forward(request, response);
			
			} else {
			request.getRequestDispatcher("/iring29/BackHome.jsp").forward(request, response);
			}
		
	}

	public void processQueryRegion(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		String region = request.getParameter("region_name").trim();
		String book_date = request.getParameter("book_date").trim();
		String person_numer = request.getParameter("person_numer").trim();
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		List<RestaurantBean> res_data_region = restaurantDAO.findRegion(region);
		request.setAttribute("res_data_region", res_data_region);
		request.getSession().setAttribute("book_date", book_date);
		request.getSession().setAttribute("person_numer", person_numer);
		request.getRequestDispatcher("/iring29/Region_Restaurant.jsp").forward(request, response);
	}

}
