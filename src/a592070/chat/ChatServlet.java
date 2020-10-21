package a592070.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import global.Constant;
import rambo0021.model.AccountBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


@WebServlet(name = "ChatServlet" , urlPatterns = "/ChatServlet")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("getChatClients".equals(method)){
            getChatClients(req, resp);
        }else {
            HttpSession session = req.getSession();
            String username;
            if (session.getAttribute("Login") != null) {
                AccountBean user = (AccountBean) session.getAttribute("Login");
                if (user.getIdentity() == 1) {
                    req.getRequestDispatcher("a592070/chatRoomService.jsp").forward(req, resp);
                    return;
                }
            }

            req.getRequestDispatcher("a592070/chatRoomClient.jsp").forward(req, resp);
        }
    }

    public void getChatClients(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Map<String, String> map = (Map<String, String>) getServletContext().getAttribute(Constant.CHATROOM_CLIENTS);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), map);
    }
}
