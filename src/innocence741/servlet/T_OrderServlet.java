package innocence741.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import controller.ConnectionPool;
import innocence741.model.HighSpeedRail;
import innocence741.model.HighSpeedRailDAO;

import innocence741.model.T_Order_List;
import innocence741.model.T_Order_ListDAO;
import utils.HibernateUtil;
//import oracle.net.aso.a;
import pojo.OrderTableBean;
import rambo0021.model.AccountBean;

/**
 * Servlet implementation class T_OrderServlet
 */
@WebServlet("/T_OrderServlet")
public class T_OrderServlet extends HttpServlet {
	private Session session2;


    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
		SessionFactory factory = HibernateUtil.getSessionFactory();		
		session2 = factory.getCurrentSession();
		session2.beginTransaction();
        

		HighSpeedRailDAO highSpeedRailDAO = new HighSpeedRailDAO(session2);
        
        String snSchedule = request.getParameter("snSchedule");
        
        String startPoint = request.getParameter("startPoint");
        
        String destination = request.getParameter("destination");
        int trafficPrice = highSpeedRailDAO.ticketPrice(startPoint, destination);
        
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
        HttpSession session = request.getSession(false);	//使用者的資訊SESSION
        
		Set<T_Order_List> t_Order_Lists = new HashSet<T_Order_List>();
		OrderTableBean order_table = new OrderTableBean();
		T_Order_List t_Order_List =new T_Order_List();
		Timestamp ts = new Timestamp(System.currentTimeMillis()); //下訂單時間

        

        AccountBean user = (AccountBean) session.getAttribute("Login");	//之後要換的User
		
		
        
		HighSpeedRail highSpeedRail = session2.get(HighSpeedRail.class, BigDecimal.valueOf(Integer.parseInt(snSchedule))); //之後要換Integer.parseInt(snSchedule)
		order_table.setUser(user);	//假裝user是ipip
		order_table.setOrder_date(ts);
		t_Order_List.setHighSpeedRail(highSpeedRail);
		t_Order_List.setTicketPrice(BigDecimal.valueOf(trafficPrice));	//之後要換BigDecimal.valueOf(trafficPrice)
		t_Order_List.setNums_days(BigDecimal.valueOf(Integer.parseInt(nums_days)));	//之後要換BigDecimal.valueOf(Integer.parseInt(nums_days))
		t_Order_List.setStartPoint(startPoint);	//之後要換startPoint
		t_Order_List.setDestination(destination);	//之後要換destination
		t_Order_List.setDeparatureDate(Timestamp.valueOf(LocalDate.of(departureDate[0], departureDate[1], departureDate[2]).atStartOfDay()));	//之後要換Timestamp.valueOf(LocalDate.of(departureDate[0], departureDate[1], departureDate[2]).atStartOfDay())
		t_Order_List.setOrderType(orderType);	//之後要換orderType
		t_Order_List.setOrder_table(order_table);
		t_Order_List.setCustomerName(customerName);	//之後要換customerName
		t_Order_List.setCustomerPhone(customerPhone);	//之後要換customerPhone
		
		t_Order_Lists.add(t_Order_List);	//把t_Order_list裝進Set中
		order_table.setT_Order_Lists(t_Order_Lists);	//one to many
		

		T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(session2);
		boolean flag = t_Order_ListDAO.createOrderTable(order_table);
		System.out.println(flag);
		
		


		
		printJSON(request,response,flag);
    }
    
    public void printJSON(HttpServletRequest request, HttpServletResponse response, boolean flag) throws IOException, SQLException {
//    	System.out.println("rcd_printJSON" + rcd[0]);
    	String str = "";
    	if(flag == true) {
    		str = "{\"check\" : \"success\"}";
    	}else {
    		str = "{\"check\" : \"fail\"}";
		}
    	System.out.println("str= " + str);
    	PrintWriter out = response.getWriter();
    	out.println(str);
    	
		if(flag == false) {
			session2.getTransaction().rollback();
			throw new SQLException("就說你錯了吧");
		}
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
