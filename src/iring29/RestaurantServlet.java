package iring29;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConnectionPool;
import iring29.bean.RestaurantBean;

/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		
		if (request.getParameter("QUERY1") != null) {
			try {
				processQueryRestaurant(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			}
		else if(request.getParameter("QUERY2") != null) {
			try {
				processQueryRegion(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("DELETE") != null) {
			try {
				processDeleteRestaurant(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void processQueryRestaurant(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		String name = request.getParameter("name").trim();
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		RestaurantBean res_data = restaurantDAO.findRestaurant(name);
		request.getSession().setAttribute("res_data", res_data);
		request.getRequestDispatcher("/iring29/DisplayRestaurant.jsp").forward(request, response);
	}

	
	public void processQueryRegion(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		String region = request.getParameter("region").trim();
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		List<RestaurantBean> res_data_region = restaurantDAO.findRegion(region);
		request.setAttribute("res_data_region", res_data_region);
		request.getRequestDispatcher("iring29/Region_Restaurant.jsp").forward(request, response);
	}
	
	public void processDeleteRestaurant(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		String id = request.getParameter("id").trim();
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		boolean deleteRestaurant = restaurantDAO.deleteRestaurant(id);
		if(deleteRestaurant) {
			request.getRequestDispatcher("iring29/bye.jsp").forward(request, response);
		}
		
	}
}
