package iring29;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			try {
				processQueryR_HP(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("m-add") != null) {
			System.out.println(request.getParameter("m-add"));
			try {
				processModifyR_HP(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("cancel") != null) {
			try {
				processCancelModify(request, response);
				System.out.println("go cancel");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("confirm-location") != null) {
			try {
				processModifyLocation(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//顯示Restaurant HP
	public void processQueryR_HP(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		request.getSession();
		String name = request.getParameter("username").trim();
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		RestaurantBean rBean = restaurantDAO.Restaurant_HP(name);
		BigDecimal r_sn = rBean.getR_sn();
		
		R_Order_ListDAO r_Order_ListDAO = new R_Order_ListDAO(ConnectionPool.LOADING_WITH_SERVER);
		OrderTableBean otBean = r_Order_ListDAO.findR_Order(r_sn); //多個
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

	// 取消修改
	public void processCancelModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in cancel");
		request.getSession();
		request.getRequestDispatcher("iring29/RestaurantHP.jsp").forward(request, response);
	}

	// 修改R_Location
	public void processModifyLocation(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		HttpSession session = request.getSession(false);
		String address = request.getParameter("address");
		String trans = request.getParameter("transportation");
		RestaurantBean rBean = (RestaurantBean) session.getAttribute("r_hp");
		if (address == "") {
			System.out.println(rBean.getAddress()); // 取得Restaurant Address
			address = rBean.getAddress();
		}
		if(trans == "") {
			trans = rBean.getTransportation();
		}
		
		ModifyDAO modifyDAO = new ModifyDAO(ConnectionPool.LOADING_WITH_SERVER);
		modifyDAO.R_Address(address, trans, rBean.getR_sn());
		rBean.setAddress(address);
		rBean.setTransportation(trans);
		request.getRequestDispatcher("iring29/RestaurantHP.jsp").forward(request, response);;
		
	}
}
