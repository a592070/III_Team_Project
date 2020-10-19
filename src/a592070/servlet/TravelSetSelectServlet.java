package a592070.servlet;

import a592070.pojo.TravelSetDO;
import a592070.service.TravelSetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import globalinit.Constant;
import rambo0021.model.AccountBean;
import utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@WebServlet(name = "TravelSetSelectServlet", urlPatterns = "/TravelSetSelectServlet")
public class TravelSetSelectServlet extends HttpServlet {
    private TravelSetService service;
    @Override
    public void init() throws ServletException {
        super.init();
        service = new TravelSetService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username;
        if(!StringUtil.isEmpty(req.getParameter("system_travelset"))){
            username = "system1";
        } else if(session.getAttribute(Constant.LOGIN) != null){
            AccountBean user = (AccountBean) session.getAttribute(Constant.LOGIN);
            username = user.getUserName();
        }else{
            username = "guest"+session.getId();
//            username = "null";
        }

        String method = req.getParameter("method");
        if("initCurrentTravelSet".equals(method)){
            initCurrentTravelSet(username, req, resp);
        }else if("deleteTravelSet".equals(method)){
            deleteTravelSet(req, resp);
        }



        ObjectMapper mapper = new ObjectMapper();

        List<TravelSetDO> list = service.listTravelSet(username);
        session.setAttribute(Constant.TravelSetList_session, list);


        ArrayNode arrayNode = mapper.createArrayNode();
        for (TravelSetDO travelSetDO : list) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("sn", travelSetDO.getSn());
            objectNode.put("name", travelSetDO.getName());
            objectNode.put("description", travelSetDO.getDescription());
            objectNode.put("updateTime", travelSetDO.getUpdateTime().getTime());
            objectNode.put("createdTime", travelSetDO.getCreatedTime().getTime());
            arrayNode.add(objectNode);
        }

        ObjectNode jsonObj = mapper.createObjectNode();
        jsonObj.set("historyTravelSets", arrayNode);

        TravelSetDO travelSet = (TravelSetDO) session.getAttribute(Constant.TravelSetEdit_session);
        if(travelSet != null){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("sn", travelSet.getSn());
            objectNode.put("name", travelSet.getName());
            objectNode.put("description", travelSet.getDescription());

            jsonObj.set("currentTravelSet", objectNode);
        }
        mapper.writeValue(resp.getWriter(), jsonObj);

    }

    private void initCurrentTravelSet(String username, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        ObjectMapper mapper = new ObjectMapper();

//        System.out.println("TravelSetSelectServlet\t"+session.getId());

        TravelSetDO travelSetDO = new TravelSetDO();
        travelSetDO.setCreatedUser(username);
        session.setAttribute(Constant.TravelSetEdit_session, travelSetDO);

        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("initTravelSet", true);
        mapper.writeValue(resp.getWriter(), objectNode);
    }

    private void deleteTravelSet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sSn = req.getParameter("sn");
        if(!StringUtil.isEmpty(sSn)){
            int sn = Integer.parseInt(sSn);

            boolean flag = service.removeTravelSet(sn);

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", flag);
            mapper.writeValue(resp.getWriter(), objectNode);
        }
    }
}
