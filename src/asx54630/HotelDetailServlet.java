package asx54630;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConnectionPool;

/**
 * Servlet implementation class HotelDetailServlet
 */
@WebServlet(name="HotelDetailServlet",urlPatterns ="/HotelDetailServlet")
public class HotelDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelDetailServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String detailsn = request.getParameter("detailsn").trim();
		System.out.print("("+detailsn+")");
		int detsn = Integer.parseInt(detailsn);
		HotelDAO hotelDAO = new HotelDAO(ConnectionPool.LOADING_WITH_SERVER);
		HotelDO detaildata = null;
		try {
			detaildata = hotelDAO.DetailSearch(detsn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("detaildata", detaildata);
		System.out.print(detaildata);
		request.getRequestDispatcher("/asx54630/MoreDetail.jsp").forward(request,response);
	}

}
