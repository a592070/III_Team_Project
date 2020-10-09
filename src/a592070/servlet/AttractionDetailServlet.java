package a592070.servlet;

import a592070.pojo.AttractionDO;
import a592070.service.AttractionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AttractionDetailServlet", urlPatterns = "/AttractionDetailServlet")
public class AttractionDetailServlet extends HttpServlet {
    private AttractionService attractionService;
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            attractionService = new AttractionService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String attractionSn = req.getParameter("attractionSn");
        try{
            if(!StringUtil.isEmpty(attractionSn)){
                int sn = Integer.parseInt(attractionSn);
                AttractionDO ele = attractionService.getEle(sn);

                req.setAttribute("attraction", ele);
                System.out.println(ele);

                req.getRequestDispatcher("/a592070/attractionDetail.jsp").forward(req,resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
