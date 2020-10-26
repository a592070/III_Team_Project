package rambo0021.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import rambo0021.old.OrderBean;
import rambo0021.old.OrderDAO;

/**
 * Servlet implementation class ListAjaxController
 */
@WebServlet("/ListAjaxController")
public class ListAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListAjaxController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OrderDAO orderDAO = new OrderDAO();
		OrderBean order = new OrderBean();
		System.out.println(request.getParameter("orderid"));
		order.setOrderId(Integer.valueOf(request.getParameter("orderid")));
		order = orderDAO.selectOrderlist(order);
		
		ObjectMapper objectMapper = new ObjectMapper();
//		String ojson = objectMapper.writeValueAsString(order);

//		PrintWriter out = response.getWriter();
//		out.println(ojson.toString());
		objectMapper.writeValue(response.getWriter(), order);

	}

}
