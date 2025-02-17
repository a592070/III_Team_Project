package innocence741;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.ConnectionPool;

/**
 * Servlet implementation class orderHSRticketSrevlet
 */
@WebServlet("/orderHSRticketSrevlet")
public class orderHSRticketSrevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderHSRticketSrevlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(false);
        if (session.getAttribute("Login") == null) {
            // 請使用者登入
//        	System.out.println("hahahahaha");
        	String ujson1 = "{\"check\" : \"rederict\"}";
            PrintWriter out = response.getWriter();
            out.println(ujson1.toString());
        }else {
			String idHSR = request.getParameter("idHSR");
			String startPoint = request.getParameter("startPoint");
			String destination = request.getParameter("destination");
			String ticketNum = request.getParameter("ticketNum");
			String departureDate = request.getParameter("departureDate");
	//		System.out.println("idHSR= " + idHSR);
	//		System.out.println("departureDate=" + departureDate);
			
			List<hsrDO> list;
	    	int price = 0;
			hsrDAO hsrDAO = new hsrDAO(ConnectionPool.LOADING_WITH_SERVER);
			hsrDAO.getSN_Schedule(idHSR);
			list = hsrDAO.listHsrDO();
			System.out.println(list.size());
			price = hsrDAO.ticketPrice(startPoint, destination);
			Boolean check = false;
			System.out.println(hsrDAO.getDirection(startPoint, destination).equals(list.get(0).getDirection()));
			if(hsrDAO.getDirection(startPoint, destination).equals(list.get(0).getDirection()))
				check = true;
	        ObjectMapper objectMapper = new ObjectMapper();
	        String ujson = objectMapper.writeValueAsString(list);
	        ujson = "[" + ujson + ",{\"price\" : " + price + "},{\"ticketNum\" : " + ticketNum +"},"
	        		+ "{\"departureDate\" : " + '\"'+departureDate+'\"' +"},"
	        				+ "{\"startPoint\" : " + '\"'+startPoint+'\"' +"},{\"destination\" : " + '\"'+destination+'\"' +"}]";
	//        ujson = "[" + ujson + ",{\"price\" : " + price + "}]";
	
	        
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(ujson);
			JsonNode node1 = jsonNode.path(0).path(0).path(startPoint);
			JsonNode node2 = jsonNode.path(0).path(0).path(destination);
	//		System.out.println(node1);
	//		System.out.println(node1.toString().equals("null"));
			ujson = ujson.substring(0,ujson.length()-1);
	        if(node1.toString().equals("null") || node2.toString().equals("null") || !check) {
	        	ujson = ujson + ",{\"check\" : \"false\"}]";
	        }else {
	        	ujson = ujson + ",{\"check\" : \"true\"}]";
			}
	        System.out.println(ujson+"\n");
	
	        PrintWriter out = response.getWriter();
	        out.println(ujson.toString());
	    }
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
