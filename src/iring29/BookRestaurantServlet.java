package iring29;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/BookRestaurantServlet")
public class BookRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	
    public BookRestaurantServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		HttpSession session = request.getSession(false);
		String res_name = request.getParameter("res_name");
		String r_id = request.getParameter("r_id");
		System.out.println(r_id);
		String book_date = (String) session.getAttribute("book_date");
		System.out.println(book_date);
		String person_numer = (String) session.getAttribute("person_numer");
		request.setAttribute("res_name", res_name );
		request.setAttribute("book_date", book_date);
		request.setAttribute("person_numer", person_numer);
		request.setAttribute("r_id", r_id);
		request.getRequestDispatcher("/iring29/OrderList.jsp").forward(request, response);
		
	}
}
