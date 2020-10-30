package rambo0021.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.DataException;

import iring29.model.RestaurantBean;
import iring29.model.RestaurantDAO;
import oracle.security.o3logon.a;
import rambo0021.model.AccountBean;
import rambo0021.model.HomePage;
import rambo0021.model.RegisterDAO;
import rambo0021.model.SHA2DAO;
import utils.HibernateUtil;

@MultipartConfig
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, UnsupportedEncodingException {
		// 儲存會員基本資料
		AccountBean account = new AccountBean();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		RegisterDAO register = new RegisterDAO(session);

		String username = request.getParameter("username").trim();
		account.setUserName(username);

		String password = SHA2DAO.getSHA256(request.getParameter("password").trim());
		account.setPassword(password);

		int identity = Integer.parseInt(request.getParameter("identity").trim());
		account.setIdentity(identity);

		String email = request.getParameter("email").trim();
		account.setEmail(email);

		Date date = new Date();
		account.setModify_Date(date);
        account.setRegister(date);		
		
		
		InputStream pictrure=null;

		try {
			pictrure = request.getPart("picture").getInputStream();
			account.setPicture(pictrure.readAllBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		String nickname = request.getParameter("nickname").trim();
		account.setNickName(nickname);
		System.out.println("Servlet is on");
		
		
		// 儲存店家基本資料
		// 餐廳
		if (identity == 3) {
			RestaurantBean rBean = new RestaurantBean();
			rBean.setName(request.getParameter("rname").trim()); // 餐廳名稱
			rBean.setAddress(request.getParameter("raddress").trim()); // 餐廳地址
			rBean.setOpentime(request.getParameter("ropentime").trim()); // 餐廳營業時間
			rBean.setDescription(request.getParameter("rdescription").trim()); // 餐廳描述
			rBean.setTransportation(request.getParameter("rtransportation").trim()); // 餐廳交通方式
			rBean.setType(request.getParameter("rtype").trim()); // 餐廳類型
			rBean.setRating(BigDecimal.ZERO); // rating初始值設為0
			rBean.setPicture(request.getParameter("rpicture").trim()); // 餐廳照片，url格式
			rBean.setServiceinfo(request.getParameter("serviceinfo").trim()); // 餐廳用餐訊息
	        
			account.setRestaurantBean(rBean);
			rBean.setAccountBean(account);

		}
		// 住宿
		/*
		else if (identity == 4) {

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
		// 交通
		
		else if (identity == 5) {
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
		}
*/
		register.insertData(account);
		
		try {
			pictrure.close();
			response.sendRedirect(request.getContextPath() + "/rambo0021/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		;

	}

}
