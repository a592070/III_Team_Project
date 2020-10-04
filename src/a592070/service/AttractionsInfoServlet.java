package a592070.service;

import a592070.dao.AttractionsDAO;
import a592070.vo.AttractionsInfoVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controller.ConnectionPool;
import utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AttractionsInfoServlet", urlPatterns = "/AttractionsInfoServlet")
public class AttractionsInfoServlet extends HttpServlet {
    private List<AttractionsInfoVO> listInfoVO;
    private AttractionsDAO attractionsDAO;
    private List<AttractionsInfoVO> currentList;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            attractionsDAO = new AttractionsDAO(ConnectionPool.LOADING_WITHOUT_SERVER);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listInfoVO = attractionsDAO.listInfoVO();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        String showPage = req.getParameter("nowPage");
        String area = req.getParameter("area");
        if(StringUtil.isEmpty(area)){
            currentList = listInfoVO;
        }else{
            currentList = new ArrayList<>();
            for (AttractionsInfoVO attractionsInfoVO : listInfoVO) {
                if(attractionsInfoVO.getArea().equals(area)){
                    currentList.add(attractionsInfoVO);
                }
            }
        }


        if(showPage == null){
            objectNode.put("totalPage", listInfoVO.size()/50);
            objectNode.put("nowPageInfo", mapper.writeValueAsString(currentList.subList(0, 50)));
            mapper.writeValue(resp.getWriter(), objectNode);
        }else{
            // page = 0,1,2,3,...
            int page = Integer.parseInt(showPage);
            int start = 50*page;
            int end = Math.min(start + 50, listInfoVO.size());

            mapper.writeValue(resp.getWriter(), listInfoVO.subList(start, end));
        }
    }
}
