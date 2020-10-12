package innocence741;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.ConnectionPool;

/**
 * Servlet implementation class ShowHistricalT_Order
 */
@WebServlet("/ShowHistricalT_OrderServlet")
public class ShowHistricalT_OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowHistricalT_OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ArrayList<ArrayList> combineArrayList = new ArrayList<>();
		T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(ConnectionPool.LOADING_WITH_SERVER);
		t_Order_ListDAO.searchHistoricalOrder(combineArrayList, "abab");	//假設使用者為abab 之後從session取得
//		System.out.println("combineArrayList.size= " + combineArrayList.size());
		
        ObjectMapper objectMapper = new ObjectMapper();
        String ujson = objectMapper.writeValueAsString(combineArrayList);
//		JsonNode jsonNode = objectMapper.readTree(ujson);
//		System.out.println(jsonNode.path(0).size());	
//		JsonNode node = jsonNode.path(1).path(1).path("order_id");	//取得orderID的位置
//		System.out.println(node);
        System.out.println(ujson+"\n");
        PrintWriter out = response.getWriter();
        out.println(ujson.toString());
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
