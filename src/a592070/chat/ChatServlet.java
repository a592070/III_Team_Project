package a592070.chat;

import rambo0021.AccountBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String userChatId;
        if(session.getAttribute("Login") != null){
            AccountBean user = (AccountBean) session.getAttribute("Login");
            userChatId = user.getUserName();
        }else{
            userChatId = "guest"+session.getId();
        }
        req.setAttribute("userChatId", userChatId);
        req.getRequestDispatcher("a592070/chatRoom.jsp").forward(req, resp);
    }
}
