package innocence741;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ConnectionPool;
//import oracle.net.aso.a;
import pojo.OrderTableBean;
import rambo0021.AccountBean;

/**
 * Servlet implementation class T_OrderServlet
 */
@WebServlet("/T_OrderServlet")
public class T_OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public T_OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
		hsrDAO hsrDAO = new hsrDAO(ConnectionPool.LOADING_WITH_SERVER);
        
        String snSchedule = request.getParameter("snSchedule");
        
        String startPoint = request.getParameter("startPoint");
        
        String destination = request.getParameter("destination");
        int trafficPrice = hsrDAO.ticketPrice(startPoint, destination);
        
        String nums_days = request.getParameter("nums_days");
        
        String departureDate_tmp = request.getParameter("departureDate");
        int[] departureDate = spiltDate(departureDate_tmp);
        
        String orderType = request.getParameter("orderType");
        
        String customerName = request.getParameter("customerName");
        
        String customerPhone = request.getParameter("customerPhone");

//        System.out.println("snSchedule= "+snSchedule);
//        System.out.println("startPoint= "+startPoint);
//        System.out.println("destination= "+destination);
//        System.out.println("trafficPrice= " +trafficPrice);
//        System.out.println("nums_days= "+nums_days);
//        System.out.println("departureDate_tmp= "+departureDate);
//        System.out.println("departureDate= "+departureDate[0]+" "+departureDate[1]+" "+departureDate[2]);
//        System.out.println("orderType= "+orderType);
        hsrDO hsrDO = new hsrDO();
        OrderTableBean bean = new OrderTableBean();
        T_OrderBean tBean = new T_OrderBean();
        AccountBean user = new AccountBean();
        
		hsrDO.setSnSchedule(Integer.parseInt(snSchedule));
		tBean.setHsrDO(hsrDO);
		tBean.setTrafficPrice(BigDecimal.valueOf(trafficPrice));
		tBean.setNums_days(BigDecimal.valueOf(Integer.parseInt(nums_days)));
		tBean.setStartPoint(startPoint);
		tBean.setDestination(destination);
		tBean.setDeparatureDate(Timestamp.valueOf(LocalDate.of(departureDate[0], departureDate[1], departureDate[2]).atStartOfDay()));
		tBean.setOrderType(orderType);
		tBean.setCustomerName(customerName);	//先假裝
		tBean.setCustomerPhone(customerPhone);	//先假裝
        
		
		user.setUserName("innocence741");	//假裝訂購人為innocence
		bean.setUser(user);	//假裝訂購人為innocence
		bean.addT_OderBean(tBean);

		int[] rcd = new int[1];
		String[] rcdOrder_id = new String[1];
		T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(ConnectionPool.LOADING_WITH_SERVER);
		t_Order_ListDAO.createOrderTable(bean, rcd, rcdOrder_id);
		System.out.println("rec= "+rcd[0]);
		
		BigDecimal order_id = new BigDecimal(rcdOrder_id[0]);
		tBean.setOrder_id(order_id);
		t_Order_ListDAO = new T_Order_ListDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
		t_Order_ListDAO.createT_Order_List(bean, rcd);

		printJSON(request,response,rcd);
    }
    
    public void printJSON(HttpServletRequest request, HttpServletResponse response, int[] rcd) throws IOException {
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
    
    public int[] spiltDate(String departureDate_tmp) {
    	String[] tmp = new String[3];    	
    	tmp = departureDate_tmp.split("-");
    	int[] departureDate = {Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2])};
    	return departureDate; 
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
