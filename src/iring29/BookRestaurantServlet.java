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
       

    public BookRestaurantServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String res_name = request.getParameter("res_name");
		String book_date = (String) session.getAttribute("book_date");
		String person_numer = (String) session.getAttribute("person_numer");
		request.setAttribute("res_name", res_name );
		request.setAttribute("book_date", book_date);
		request.setAttribute("person_numer", person_numer);
		request.getRequestDispatcher("/iring29/OrderList.jsp").forward(request, response);
	}

}
