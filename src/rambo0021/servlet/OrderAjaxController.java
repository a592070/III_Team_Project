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

import pojo.OrderTableBean;
import rambo0021.model.AccountBean;


//大訂單
@WebServlet("/OrderAjaxController")
public class OrderAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hsession = request.getSession(false);
		AccountBean account = (AccountBean)hsession.getAttribute("Login");
		List<OrderTableBean> list = account.getOrderTableBeans();
		for (OrderTableBean order : list) {
		    System.out.println(order.getOrder_id());
	        System.out.println(order.getOrder_date());	
		}

		ObjectMapper objectMapper = new ObjectMapper();
		String ojson = objectMapper.writeValueAsString(list);
		
		PrintWriter out = response.getWriter();
		out.println(ojson.toString());
	}

}
