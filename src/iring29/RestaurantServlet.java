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

		if (request.getParameter("QUERY") != null) {
//			if (request.getParameter("restaurant_name") != null) {
//				try {
//					processQueryRestaurant(request, response);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} 
			if (request.getParameter("region_name") != null) {
				try {
					processQueryRegion(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void processQueryRestaurant(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String name = request.getParameter("restaurant_name").trim();
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		RestaurantBean res_data = restaurantDAO.findRestaurant(name);
		request.getSession().setAttribute("res_data", res_data);
		request.getRequestDispatcher("/iring29/DisplayRestaurant.jsp").forward(request, response);
	}

	public void processQueryRegion(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		String region = request.getParameter("region_name").trim();
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		List<RestaurantBean> res_data_region = restaurantDAO.findRegion(region);
		request.setAttribute("res_data_region", res_data_region );
//		List<String> name = new ArrayList<>();
//		List<BigDecimal> rating = new ArrayList<>();
//		List<String> type = new ArrayList<>();
//		for(RestaurantBean res :res_data_region ) {
//			String res_name = res.getName();
//			BigDecimal res_rating = res.getRating();
//			String res_type = res.getType();
//			name.add(res_name);
//			rating.add(res_rating);
//			type.add(res_type);
//		}
//		
//		request.setAttribute("res_name", name);
//		request.setAttribute("res_rating", rating);
//		request.setAttribute("res_type", type);
		request.getRequestDispatcher("/iring29/Region_Restaurant.jsp").forward(request, response);
	}


}
