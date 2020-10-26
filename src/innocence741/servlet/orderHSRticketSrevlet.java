package innocence741.servlet;

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

import org.hibernate.Session;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import innocence741.model.HighSpeedRail;
import innocence741.model.HighSpeedRailDAO;

/**
 * Servlet implementation class orderHSRticketSrevlet
 */
@WebServlet("/orderHSRticketSrevlet")
public class orderHSRticketSrevlet extends HttpServlet {
	private Session session;
       
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
        
        HttpSession session2 = request.getSession(false);
        if (session2.getAttribute("Login") == null) {
            // 請使用者登入
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
			
			List<HighSpeedRail> list;	//創建一個List裝載列車資訊
	    	int price = 0;
			HighSpeedRailDAO hsrDAO = new HighSpeedRailDAO(session);
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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
