package a592070.chat;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener()
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ((HttpServletRequest) sre.getServletRequest()).getSession();

    }
}
