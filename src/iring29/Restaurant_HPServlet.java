package iring29;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ConnectionPool;
import iring29.model.R_OrderBean;
import iring29.model.RestaurantBean;
import oracle.security.o3logon.b;
import pojo.OrderTableBean;
import rambo0021.model.AccountBean;

@WebServlet("/Restaurant_HPServlet")
public class Restaurant_HPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public Restaurant_HPServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
//		System.out.println(request.getParameter("finalDecision"));
		System.out.println("inHP");
		HttpSession session = request.getSession(false);
		AccountBean aBean = (AccountBean) session.getAttribute("Login");

//		else if (request.getParameter("cancel") != null) {
//			try {
//				processCancelModify(request, response);
//				System.out.println("go cancel");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} 
//		else if (request.getParameter("confirm-location") != null) {
//			try {
//				processModifyLocation(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		else if (request.getParameter("confirm-type") != null) {
//			try {
//				processModifyType(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		else if (request.getParameter("confirm-info") != null) {
//			try {
//				processModifyInfo(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		if (request.getParameter("picture") != null) {
			try {
				processModifyImg(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(request.getParameter("finalDecision") != null){
			if(request.getParameter("finalDecision").equals("confirmL")) {
				try {
					System.out.println("in confirm L");
					processModifyLocation(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(request.getParameter("finalDecision").equals("confirmT")) {
				try {
					System.out.println("in confirm T");
					processModifyType(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(request.getParameter("finalDecision").equals("confirmI")) {
				try {
					System.out.println("in confirm T");
					processModifyInfo(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(request.getParameter("finalDecision").equals("cancel")) {
				try {
					System.out.println("in cancel");
					processCancelModify(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else if (aBean.getUserName() != null) {
			try {
				processQueryR_HP(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//顯示Restaurant HP
	public void processQueryR_HP(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		HttpSession session = request.getSession(false);
		AccountBean aBean = (AccountBean) session.getAttribute("Login");
		String name = aBean.getUserName();
		RestaurantDAO restaurantDAO = new RestaurantDAO(ConnectionPool.LOADING_WITH_SERVER);
		RestaurantBean rBean = restaurantDAO.Restaurant_HP(name);
		BigDecimal r_sn = rBean.getR_sn();
		
		R_Order_ListDAO r_Order_ListDAO = new R_Order_ListDAO(ConnectionPool.LOADING_WITH_SERVER);
		OrderTableBean otBean = r_Order_ListDAO.findR_Order(r_sn); //多個
		Set<R_OrderBean> roBean = otBean.getR_OderBeans();
		
		for(R_OrderBean bean : roBean) {
			System.out.println(bean.getR_sn_order());
		}
		
		request.getSession().setAttribute("r_hp", rBean);
		request.getSession().setAttribute("roBean", roBean);
		request.getRequestDispatcher("iring29/RestaurantHP.jsp").forward(request, response);
	}


	// 取消修改
	public void processCancelModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in cancel");
		request.getSession();
		request.getRequestDispatcher("iring29/RestaurantHP.jsp").forward(request, response);
	}

	// 修改R_Location
	public void processModifyLocation(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ServletException {
		HttpSession session = request.getSession(false);
		String address = request.getParameter("address");
		String trans = request.getParameter("transportation");
		RestaurantBean rBean = (RestaurantBean) session.getAttribute("r_hp");
		if (address == "") {
			System.out.println(rBean.getAddress()); // 取得Restaurant Address
			address = rBean.getAddress();
		}
		if(trans == "") {
			trans = rBean.getTransportation();
		}
		
		ModifyDAO modifyDAO = new ModifyDAO(ConnectionPool.LOADING_WITH_SERVER);
		modifyDAO.R_Address(address, trans, rBean.getR_sn());
		rBean.setAddress(address);
		rBean.setTransportation(trans);
		request.getRequestDispatcher("iring29/RestaurantHP.jsp").forward(request, response);;
		
	}
	
	// 修改R_Type
		public void processModifyType(HttpServletRequest request, HttpServletResponse response)
				throws IOException, SQLException, ServletException {
			HttpSession session = request.getSession(false);
			String serviceinfo = request.getParameter("serviceinfo");
			String type = request.getParameter("type");
			RestaurantBean rBean = (RestaurantBean) session.getAttribute("r_hp");
			if (serviceinfo == "") {
				serviceinfo= rBean.getServiceinfo();
			}
			if(type == "") {
				type = rBean.getType();
			}
			
			ModifyDAO modifyDAO = new ModifyDAO(ConnectionPool.LOADING_WITH_SERVER);
			modifyDAO.R_Type(serviceinfo, type, rBean.getR_sn());
			rBean.setServiceinfo(serviceinfo);
			rBean.setType(type);
			request.getRequestDispatcher("iring29/RestaurantHP.jsp").forward(request, response);;
			
		}
		
		// 修改R_Info
				public void processModifyInfo(HttpServletRequest request, HttpServletResponse response)
						throws IOException, SQLException, ServletException {
					HttpSession session = request.getSession(false);
					String opentime = request.getParameter("opentime");
					String description = request.getParameter("description");
					RestaurantBean rBean = (RestaurantBean) session.getAttribute("r_hp");
					if (opentime == "") {
						opentime= rBean.getOpentime();
					}
					if(description == "") {
						description = rBean.getDescription();
					}
					
					ModifyDAO modifyDAO = new ModifyDAO(ConnectionPool.LOADING_WITH_SERVER);
					modifyDAO.R_Info(opentime, description, rBean.getR_sn());
					rBean.setOpentime(opentime);
					rBean.setDescription(description);
					request.getRequestDispatcher("iring29/RestaurantHP.jsp").forward(request, response);;
					
				}
				
				// 修改R_Img
				public void processModifyImg(HttpServletRequest request, HttpServletResponse response)
						throws IOException, SQLException, ServletException {
					HttpSession session = request.getSession(false);
					String picture = request.getParameter("picture");
					RestaurantBean rBean = (RestaurantBean) session.getAttribute("r_hp");
					if (picture == "") {
						picture= rBean.getPicture();
					}
					
					ModifyDAO modifyDAO = new ModifyDAO(ConnectionPool.LOADING_WITH_SERVER);
					modifyDAO.R_Img(picture, rBean.getR_sn());
					rBean.setPicture(picture);
					request.getRequestDispatcher("iring29/RestaurantHP.jsp").forward(request, response);;
					
				}
		
}
