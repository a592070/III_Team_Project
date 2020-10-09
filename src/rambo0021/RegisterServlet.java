package rambo0021;

import java.io.IOException;
import java.io.InputStream;import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import controller.ConnectionPool;
import iring29.RestaurantDAO;
import iring29.bean.RestaurantBean;
import utils.IOUtils;


/**
 * Servlet implementation class registerServlet
 */
@MultipartConfig
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//		
	//		 
	//		
	//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//儲存會員基本資料
		AccountBean account = new AccountBean();
		RegisterDAO registerDAO = new RegisterDAO();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8"); 

		String username = request.getParameter("username").trim();
		account.setUserName(username);	
		String password = request.getParameter("password").trim();
		account.setPassword(password);	
		int identity = Integer.parseInt(request.getParameter("identity").trim());
		account.setIdentity(identity);
		String email = request.getParameter("email").trim();
		account.setEmail(email);
		InputStream pictrure = request.getPart("picture").getInputStream();
		// TODO
		try {

			account.setPicture(new SerialBlob(IOUtils.toByteArray(pictrure)));

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		String nickname = request.getParameter("nickname").trim();
		account.setNickName(nickname);
		System.out.println("Servlet is on");
		registerDAO.insertData(account);
		//儲存店家基本資料
		//餐廳
		
		RestaurantBean rBean = new RestaurantBean();
		rBean.setName(request.getParameter("rsname").trim()); //餐廳名稱
		rBean.setAddress(request.getParameter("address").trim()); //餐廳地址
		rBean.setOpentime(request.getParameter("opentime").trim()); //餐廳營業時間
		rBean.setDescription(request.getParameter("description").trim()); //餐廳描述
		rBean.setTransportation(request.getParameter("transportation").trim()); //餐廳交通方式
		rBean.setType(request.getParameter("type").trim()); //餐廳類型
		rBean.setRating(BigDecimal.ZERO); //rating初始值設為0
		rBean.setPicture(request.getParameter("rpicture").trim()); //餐廳照片，url格式
		rBean.setServiceinfo(request.getParameter("serviceinfo").trim()); //餐廳用餐訊息
		rBean.setAccount(username); // session中的username(account)

		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		try {
			restaurantDAO.createRestaurant(rBean);
		} catch (SQLException e) {
			System.out.println("店家新增失敗");
			e.printStackTrace();
		}

		//住宿
		//交通


		pictrure.close();
		response.sendRedirect(request.getContextPath()+"/rambo0021/login.jsp");


	}

}
