package rambo0021.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.OrderTableBean;
import utils.HibernateUtil;
@WebServlet("/ListAjaxController")
public class ListAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
//各自訂單
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		
		OrderTableBean oTBean = session.get(OrderTableBean.class, Integer.valueOf(request.getParameter("orderid")));
      
		
		ObjectMapper objectMapper = new ObjectMapper();
//		String ojson = objectMapper.writeValueAsString(order);

//		PrintWriter out = response.getWriter();
//		out.println(ojson.toString());
		objectMapper.writeValue(response.getWriter(), oTBean);

	}

}
