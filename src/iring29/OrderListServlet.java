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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		HttpSession session = request.getSession(false);
		System.out.println("in");
		
		
		OrderTableBean bean = new OrderTableBean();
		R_OrderBean rBean = new R_OrderBean();
		AccountBean user = new AccountBean();
		user.setUserName("Irene");
		bean.setUser(user);
		RestaurantBean resBean = new RestaurantBean();
		BigDecimal r_id = (BigDecimal) request.getAttribute("r_id");
		resBean.setR_sn(r_id); 
		
		rBean.setRestaurantBean(resBean);
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
		String tsStr = request.getParameter("book_date") + " 00:00:00";
		System.out.println(tsStr);
		ts = Timestamp.valueOf(tsStr);
		rBean.setBooking_date(ts);
		rBean.setCustomerNum(new BigDecimal(request.getParameter("person_numer")));
		
		bean.addR_OderBean(rBean);
		System.out.println(request.getParameter("b-name"));
		System.out.println(request.getParameter("b-phone"));
		bean.setCustomerName(request.getParameter("b-name"));
		bean.setCustomerPhone(request.getParameter("b-phone"));

		R_Order_ListDAO r_Order_ListDAO = new R_Order_ListDAO(ConnectionPool.LOADING_WITH_SERVER);
		try {
			r_Order_ListDAO.createOrder(bean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
//		request.getRequestDispatcher("").forward(request, response);
		
	}

}
