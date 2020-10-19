package rambo0021.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rambo0021.model.AccountBean;

/**
 * Servlet implementation class Homepage
 */
@WebServlet("/Homepage")
public class HomepagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomepagServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream os = null;
		InputStream is = null;
		String fileName = null;
		String mimeType = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8"); 
		
		HttpSession session = request.getSession(false);
		if (session.getAttribute("Login") == null) {
			// 請使用者登入
			response.sendRedirect(response.encodeRedirectURL(
					request.getContextPath() + "/rambo0021/login.jsp"));
			return;
		}
		
		AccountBean account = (AccountBean)session.getAttribute("Login");
		try {if (account.getPicture() != null) {
			is=account.getPicture().getBinaryStream();			
		}
	 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}if (is == null) {
			fileName = "NoImage.png" ; 
			is = getServletContext().getResourceAsStream(
					"/rambo0021/Images/NoImage.png");
	
		}
		
		// 由圖片檔的檔名來得到檔案的MIME型態
		mimeType = getServletContext().getMimeType(fileName);
		// 設定輸出資料的MIME型態
		response.setContentType(mimeType);
		// 取得能寫出非文字資料的OutputStream物件
		os = response.getOutputStream();	
		// 由InputStream讀取位元組，然後由OutputStream寫出
		int len = 0;
		byte[] bytes = new byte[8192];
		while ((len = is.read(bytes)) != -1) {
			os.write(bytes, 0, len);
		}


	
		if (is != null) 
			is.close();
		if (os != null) 
			os.close();


	}
	
	




/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
//	doGet(request, response);

}

}
