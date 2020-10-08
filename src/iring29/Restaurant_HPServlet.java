package iring29;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConnectionPool;
import iring29.bean.R_OrderBean;
import iring29.bean.RestaurantBean;
import pojo.OrderTableBean;


@WebServlet("/Restaurant_HPServlet")
public class Restaurant_HPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

    public Restaurant_HPServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		if (request.getParameter("QUERY") != null) {
			try {
				processQueryR_HP(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(request.getParameter("m-add") != null) {
			System.out.println(request.getParameter("m-add"));
			processModifyR_HP(request, response);
		}
	}
	
	public void processQueryR_HP(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		String name = request.getParameter("username").trim();
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		RestaurantBean rBean = restaurantDAO.Restaurant_HP(name);
		BigDecimal r_sn = rBean.getR_sn();
		OrderTableBean otBean = restaurantDAO.findR_Order(r_sn);
		Set<R_OrderBean> roBean = otBean.getR_OderBeans();
		request.getSession().setAttribute("r_hp", rBean);
		request.getSession().setAttribute("roBean", roBean);
		request.getRequestDispatcher("iring29/RestaurantHP.jsp").forward(request, response);
	}
	
	public void processModifyR_HP(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in");
//		String rBean = request.getParameter("rBean");
//		System.out.println(rBean);
	}

}
