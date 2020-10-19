package asx54630;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConnectionPool;
import iring29.R_Order_ListDAO;
import iring29.RestaurantDAO;
import iring29.bean.R_OrderBean;
import iring29.bean.RestaurantBean;
import pojo.OrderTableBean;
import rambo0021.model.AccountBean;

/**
 * Servlet implementation class H_HomepageServlet
 */
@WebServlet("/H_HomepageServlet")
public class H_HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public H_HomepageServlet() {
        super();
        // TODO Auto-generated constructor stub
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

		HttpSession session = request.getSession(false);
		AccountBean aBean = (AccountBean) session.getAttribute("Login");
		if (aBean.getUserName() != null) {
			try {
				SelectHomePage(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
	public void SelectHomePage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		HttpSession session = request.getSession(false);
		AccountBean aBean = (AccountBean) session.getAttribute("Login");
		String hhomepage = aBean.getUserName();
		System.out.println("業者名稱"+hhomepage);
		HotelDAO hotelDAO = new HotelDAO(ConnectionPool.LOADING_WITH_SERVER);
		HotelDO hotelDO = hotelDAO.HomePageSearch(hhomepage);
		request.getSession().setAttribute("hotelDO", hotelDO);
		System.out.println("店家資料:"+hotelDO);
		request.getRequestDispatcher("asx54630/H_HomePage.jsp").forward(request, response);
	}

}
