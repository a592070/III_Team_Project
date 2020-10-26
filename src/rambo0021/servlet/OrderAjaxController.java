package rambo0021.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import rambo0021.model.AccountBean;
import rambo0021.old.OrderBean;
import rambo0021.old.OrderDAO;


@WebServlet("/OrderAjaxController")
public class OrderAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDAO orderDAO = new OrderDAO();
		HttpSession session = request.getSession(false);
		AccountBean account = (AccountBean)session.getAttribute("Login");
		List<OrderBean> list = orderDAO.selectOrder(account);
		for (OrderBean order : list) {
		    System.out.println(order.getOrderDateString());
	        System.out.println(order.getOrderId());	
		}

		ObjectMapper objectMapper = new ObjectMapper();
		String ojson = objectMapper.writeValueAsString(list);
		
		PrintWriter out = response.getWriter();
		out.println(ojson.toString());
	}

}
