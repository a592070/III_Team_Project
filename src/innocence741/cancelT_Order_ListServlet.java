package innocence741;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConnectionPool;

/**
 * Servlet implementation class cancelT_Order_ListServlet
 */
@WebServlet("/cancelT_Order_ListServlet")
public class cancelT_Order_ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cancelT_Order_ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void	processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String t_sn_order_tmp = request.getParameter("t_sn_order");
		int[] rcd = new int[1];
        BigDecimal t_sn_order = BigDecimal.valueOf(Integer.parseInt(t_sn_order_tmp));
        System.out.println("t_sn_order= " + t_sn_order);
        T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(ConnectionPool.LOADING_WITH_SERVER);
        t_Order_ListDAO.delT_Order_List(t_sn_order, rcd);
		printJSON(request,response,rcd);
    }
    
    public void printJSON(HttpServletRequest request, HttpServletResponse response, int[] rcd) throws IOException {
//    	System.out.println("rcd_printJSON" + rcd[0]);
    	String str = "";
    	if(rcd[0] == 1) {
    		str = "{\"check\" : \"success\"}";
    	}else {
    		str = "{\"check\" : \"fail\"}";
		}
    	System.out.println("str= " + str);
    	PrintWriter out = response.getWriter();
    	out.println(str);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
