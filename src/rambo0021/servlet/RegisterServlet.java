package rambo0021.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import asx54630.HotelDAO;
import asx54630.HotelDO;
import controller.ConnectionPool;
import innocence741.CarRentalCompanyBean;
import innocence741.CarRentalCompanyDAO;
import innocence741.CarTypeBean;
import iring29.RestaurantDAO;
import iring29.bean.RestaurantBean;
import rambo0021.model.AccountBean;
import rambo0021.model.RegisterDAO;
import rambo0021.model.SHA2DAO;
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
		SHA2DAO sha2 = new SHA2DAO();
		RegisterDAO registerDAO = new RegisterDAO();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8"); 

		String username = request.getParameter("username").trim();
		account.setUserName(username);	
		String password = sha2.getSHA256(request.getParameter("password").trim());
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
		if(identity==3) {
			RestaurantBean rBean = new RestaurantBean();
			rBean.setName(request.getParameter("rname").trim()); //餐廳名稱
			rBean.setAddress(request.getParameter("raddress").trim()); //餐廳地址
			rBean.setOpentime(request.getParameter("ropentime").trim()); //餐廳營業時間
			rBean.setDescription(request.getParameter("rdescription").trim()); //餐廳描述
			rBean.setTransportation(request.getParameter("rtransportation").trim()); //餐廳交通方式
			rBean.setType(request.getParameter("rtype").trim()); //餐廳類型
			rBean.setRating(BigDecimal.ZERO); //rating初始值設為0
			rBean.setPicture(request.getParameter("rpicture").trim()); //餐廳照片，url格式
			rBean.setServiceinfo(request.getParameter("serviceinfo").trim()); //餐廳用餐訊息
			rBean.setAccount(username); // session中的username(account)

			RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
			try {
				restaurantDAO.createRestaurant(rBean);
				System.out.println("餐廳新增成功");
			} catch (SQLException e) {
				System.out.println("餐廳新增失敗");
				e.printStackTrace();
			}
		}
		//住宿
		else if (identity==4) {

			HotelDO hotDO = new HotelDO();

			hotDO.setNAME(request.getParameter("hname").trim());
			hotDO.setREGION(request.getParameter("hregion").trim()); 
			hotDO.setADDRESS(request.getParameter("haddress").trim());
			hotDO.setTEL(request.getParameter("htel").trim());
			hotDO.setDOUBLE_ROOM(BigDecimal.valueOf(Integer.parseInt(request.getParameter("droom").trim())));
			hotDO.setQUADRUPLE_ROOM(BigDecimal.valueOf(Integer.parseInt(request.getParameter("qroom").trim())));
			hotDO.setDESCRIPTION(request.getParameter("hdescription"));        
			hotDO.setOPENTIME(request.getParameter("hopentime"));                                
			hotDO.setTYPE(request.getParameter("htype"));                                
			hotDO.setRATING(BigDecimal.ZERO);
			hotDO.setACCOUNT(username);

			HotelDAO hotelDAO = new HotelDAO(ConnectionPool.LOADING_WITH_SERVER);                        
			try {
				hotelDAO.createHotel(hotDO);
				System.out.println("住宿新增成功");
			} catch (SQLException e) {
				System.out.println("住宿新增失敗");
				e.printStackTrace();
			}

		}
		//交通
		else if (identity==5) {
			CarRentalCompanyBean carRentalCompanyBean = new CarRentalCompanyBean();
			CarTypeBean carTypeBean = new CarTypeBean();
			

			carRentalCompanyBean.setCompanyName(request.getParameter("tname").trim());
			carRentalCompanyBean.setAddress(request.getParameter("taddress").trim());
			carRentalCompanyBean.setDescription(request.getParameter("tdescription").trim());
			carRentalCompanyBean.setOpenHours(request.getParameter("topentime").trim());
			carRentalCompanyBean.setTelphoneNum(request.getParameter("ttel").trim());
			carRentalCompanyBean.setCompanyAccount(username);
			carTypeBean.setCarType(request.getParameter("carType").trim());
			carTypeBean.setPrice(BigDecimal.valueOf(Integer.parseInt(request.getParameter("tprice").trim())));

			carTypeBean.setCarRentalCompanyBean(carRentalCompanyBean);


			CarRentalCompanyDAO carRentalCompanyDAO = new CarRentalCompanyDAO(ConnectionPool.LOADING_WITH_SERVER);
			try {
				carRentalCompanyDAO.signUPCarRentalCompany(carRentalCompanyBean);
				System.out.println("交通新增成功");
			} catch (SQLException e) {
				System.out.println("交通新增失敗");
				e.printStackTrace();
			}
		}


		pictrure.close();
		response.sendRedirect(request.getContextPath()+"/rambo0021/login.jsp");


	}

}
