package rambo0021;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession hsession = req.getSession(false);
		if (hsession.getAttribute("Login") == null) {
			// 請使用者登入
			res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/rambo0021/login.jsp"));
			return;
		}
		chain.doFilter(request, response);
	}
}
