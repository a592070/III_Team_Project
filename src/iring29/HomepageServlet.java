package iring29;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import controller.ConnectionPool;
import pojo.AccountDO;

/**
 * Servlet implementation class HomepageServlet
 */
@WebServlet("/HomepageServlet")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomepageServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;

		try {
//			System.out.println("start");

			HomepageDAO homepageDAO = new HomepageDAO();

			// 編碼值為UTF-8
			request.setCharacterEncoding("UTF-8");

			if (request.getParameter("QUERY") != null) {
				processQuery(request, response, homepageDAO);
			}
			if (request.getParameter("UPDATE") != null) {
				processUpdate(request, response, homepageDAO);
			}

		} catch (Exception e) {
			System.out.println("Connection Pool Error!");
		}

	}

	private void processQuery(HttpServletRequest request, HttpServletResponse response, HomepageDAO homepageDAO)
			throws IOException {

		// 讀取username
		String username = request.getParameter("username");

		AccountDO accDo = homepageDAO.findUser(username);
		if (username == null) {
			showError(response, " can not find this user");
		} else {
			showForm(response, accDo);
		}

	}

	private void processUpdate(HttpServletRequest request, HttpServletResponse response, HomepageDAO homepageDAO)
			throws SQLException, IOException {

		// 讀取username
		String username = request.getParameter("username");

		AccountDO accDo = homepageDAO.UserinfoUpdate(username);
		if (username == null)
			showError(response, " can not find this use");
		else {
			accDo.setUsername(username);
			homepageDAO.UserinfoUpdate(username);
			showForm(response, accDo);

		}
	}

	private void showError(HttpServletResponse response, String message) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Department Form</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY BGCOLOR='#FDF5E6'>");
		out.println("message:" + message);
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}

	private void showForm(HttpServletResponse response, AccountDO accDo) throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>HomePage</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY BGCOLOR='#FDF5E6'>");
		out.println("<H1 ALIGN='CENTER'>Department Form</H1>");
		out.println("<FORM ACTION='./HomepageServlet'>");
		out.println("Username:<INPUT TYPE='TEXT' NAME='deptid' VALUE='" + accDo.getUsername() + "'><BR>");
		out.println("User password:  <INPUT TYPE='TEXT' NAME='deptname' VALUE='" + accDo.getPassword() + "'><BR>");
		out.println("  <CENTER>");
		out.println("<INPUT NAME='QUERY'  TYPE='SUBMIT' VALUE='QUERY'>");
		out.println("<INPUT NAME='UPDATE' TYPE='SUBMIT' VALUE='UPDATE'>");
		out.println("</CENTER>");
		out.println("</FORM>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}
}
