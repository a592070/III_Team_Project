package a592070.servlet;

import a592070.pojo.TravelSetDO;
import a592070.service.TravelSetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import globalinit.Constant;
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
        doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println("TravelSetSelectServlet\t"+session.getId());
        String username = "system";

        List<TravelSetDO> list = service.listTravelSet(username);
        session.setAttribute(Constant.TravelSetList_session, list);

//        session.setAttribute(Constant.TravelSetListCar_session, travelSetDO.getListTravelCar());
//        session.setAttribute(Constant.TravelSetListHotel_session, travelSetDO.getListTravelHotel());
//        session.setAttribute(Constant.TravelSetListRestaurant_session, travelSetDO.getListTravelRestaurant());
//        session.setAttribute(Constant.TravelSetListAttraction_session, travelSetDO.getListTravelAttraction());

        ObjectMapper mapper = new ObjectMapper();
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
        mapper.writeValue(resp.getWriter(), arrayNode);
    }
}
