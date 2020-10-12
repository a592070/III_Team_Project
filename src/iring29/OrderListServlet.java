package iring29;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

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
import rambo0021.AccountBean;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public OrderListServlet() {
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
//		System.out.println(request.getParameter("finalDecision"));
		HttpSession session = request.getSession(false);
		if (request.getParameter("finalDecision") != null) {
			try {
				System.out.println("finalDecision");
				processInsertOrder(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (request.getParameter("c") != null) {
			try {
				processCancelOrder(request, response);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		} 
//		else if(r_sn_order != null) { //使用者訂單
//			try {
//			user_OrderList(request, response);
//			} catch (IOException | SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		else if (request.getParameter("time") != null) {
//			updateBookTime(request, response);
//		}

	}

	//下訂單
	public void processInsertOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		System.out.println("insert");
		HttpSession session = request.getSession(false);
		OrderTableBean bean = new OrderTableBean();
		R_OrderBean rBean = new R_OrderBean();
		AccountBean user = new AccountBean();
		user.setUserName("Irene"); // 正式使用要改username
		bean.setUser(user);
		RestaurantBean resBean = new RestaurantBean();
		BigDecimal r_id = new BigDecimal(request.getParameter("r_id"));
		String res_name = request.getParameter("res_name");
		System.out.println("r_id =" + r_id);
		System.out.println("r_name = " + res_name);
		resBean.setR_sn(r_id);
		resBean.setName(res_name);
		
		rBean.setRestaurantBean(resBean);
		String time = request.getParameter("time");
		System.out.println("time=" + time);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String tsStr = request.getParameter("book_date") +" " + time + ":00";
		System.out.println(tsStr);
		ts = Timestamp.valueOf(tsStr);
		rBean.setBooking_date(ts);
		rBean.setCustomerNum(new BigDecimal(request.getParameter("person_numer")));
		rBean.setCustomerName(request.getParameter("b-name"));
		rBean.setCustomerPhone(request.getParameter("b-phone"));

		bean.addR_OderBean(rBean);

		R_Order_ListDAO r_Order_ListDAO = new R_Order_ListDAO(ConnectionPool.LOADING_WITH_SERVER);
		try {
			r_Order_ListDAO.createOrder(bean);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		R_OrderBean roBean = r_Order_ListDAO.findR_order_List(r_id);
		request.getSession().setAttribute("roBean", roBean);
		request.getSession().setAttribute("r_name", res_name);
		request.getSession().setAttribute("bean", bean);

		
		request.getRequestDispatcher("/iring29/DisplayOrderList.jsp").forward(request, response);

	}

	public void processQueryRestaurant(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String name = request.getParameter("res_name").trim();
		String book_date = request.getParameter("book_date").trim();
		String person_numer = request.getParameter("person_numer").trim();
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		RestaurantBean res_data = restaurantDAO.findRestaurant(name);
		System.out.println(name);
		request.getSession().setAttribute("res_data", res_data);
		request.getSession().setAttribute("book_date", book_date);
		request.getSession().setAttribute("person_numer", person_numer);
		request.getRequestDispatcher("/iring29/DisplayRestaurant.jsp").forward(request, response);
	}
// 取消訂單
	public void processCancelOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		HttpSession session = request.getSession(false);
		System.out.println("in cancel");
		BigDecimal r_sn_order= new BigDecimal(request.getParameter("r_sn_order"));
		System.out.println(r_sn_order);
		R_Order_ListDAO r_Order_ListDAO = new R_Order_ListDAO(ConnectionPool.LOADING_WITH_SERVER);
		boolean cancelR_Order = r_Order_ListDAO.cancelR_Order(r_sn_order);
		if(cancelR_Order == true) {
			request.getRequestDispatcher("/iring29/bye.jsp").forward(request, response);
		}
	}
	
// user查詢訂單
	public void user_OrderList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(false);
		R_Order_ListDAO r_Order_ListDAO = new R_Order_ListDAO(ConnectionPool.LOADING_WITH_SERVER);
		//取得
		BigDecimal r_sn_order = null;
		R_OrderBean roBean = r_Order_ListDAO.UserOrderList(r_sn_order);
		request.getSession().setAttribute("roBean", roBean);

		request.getRequestDispatcher("/iring29/UserOrderList.jsp").forward(request, response);
		
	}

//	public void updateBookTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String time = request.getParameter("time");
//		String res_name = request.getParameter("res_name").trim();
//		String book_date = request.getParameter("book_date").trim();
//		String person_numer = request.getParameter("person_numer").trim();
//		BigDecimal r_id = new BigDecimal(request.getParameter("r_id"));
//		request.getSession().setAttribute("time", time);
//		request.getSession().setAttribute("res_name", res_name);
//		request.getSession().setAttribute("book_date", book_date);
//		request.getSession().setAttribute("person_numer", person_numer);
//		request.getSession().setAttribute("r_id", r_id);
//		System.out.println(time);
//		request.getRequestDispatcher("/iring29/OrderList.jsp").forward(request, response);
//	}
}