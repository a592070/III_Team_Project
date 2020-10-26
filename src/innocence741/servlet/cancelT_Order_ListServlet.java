<<<<<<< HEAD
package innocence741.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import innocence741.model.T_Order_ListDAO;
import utils.HibernateUtil;

/**
 * Servlet implementation class cancelT_Order_ListServlet
 */
@WebServlet("/cancelT_Order_ListServlet")
public class cancelT_Order_ListServlet extends HttpServlet {
	private Session session;

    public cancelT_Order_ListServlet() {
        super();
    }
    
    public void	processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        SessionFactory factory = HibernateUtil.getSessionFactory();
     	session = factory.getCurrentSession();
//     	session.beginTransaction();

        String t_sn_order_tmp = request.getParameter("t_sn_order");
        
		boolean flag = true;
        BigDecimal t_sn_order = BigDecimal.valueOf(Integer.parseInt(t_sn_order_tmp));
        System.out.println("t_sn_order= " + t_sn_order);
        
        T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(session);
        flag = t_Order_ListDAO.delT_Order_List(t_sn_order);
		printJSON(request,response,flag);
    }
    
    public void printJSON(HttpServletRequest request, HttpServletResponse response, boolean flag) throws IOException {
    	String str = "";
    	if(flag == true) {
    		str = "{\"check\" : \"success\"}";
    	}else {
    		str = "{\"check\" : \"fail\"}";
		}
    	System.out.println("str= " + str);
    	PrintWriter out = response.getWriter();
    	out.println(str);
    	
//        session.getTransaction().commit();

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
=======
package innocence741.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import innocence741.model.T_Order_ListDAO;
import utils.HibernateUtil;

/**
 * Servlet implementation class cancelT_Order_ListServlet
 */
@WebServlet("/cancelT_Order_ListServlet")
public class cancelT_Order_ListServlet extends HttpServlet {
	private Session session;

    public cancelT_Order_ListServlet() {
        super();
    }
    
    public void	processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        SessionFactory factory = HibernateUtil.getSessionFactory();
     	session = factory.getCurrentSession();
//     	session.beginTransaction();

        String t_sn_order_tmp = request.getParameter("t_sn_order");
        
		boolean flag = true;
        BigDecimal t_sn_order = BigDecimal.valueOf(Integer.parseInt(t_sn_order_tmp));
        System.out.println("t_sn_order= " + t_sn_order);
        
        T_Order_ListDAO t_Order_ListDAO = new T_Order_ListDAO(session);
        flag = t_Order_ListDAO.delT_Order_List(t_sn_order);
		printJSON(request,response,flag);
    }
    
    public void printJSON(HttpServletRequest request, HttpServletResponse response, boolean flag) throws IOException {
    	String str = "";
    	if(flag == true) {
    		str = "{\"check\" : \"success\"}";
    	}else {
    		str = "{\"check\" : \"fail\"}";
		}
    	System.out.println("str= " + str);
    	PrintWriter out = response.getWriter();
    	out.println(str);
    	
//        session.getTransaction().commit();

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
>>>>>>> 223ad593d563fbddf72cfcec28aa3247d9fe48a7
