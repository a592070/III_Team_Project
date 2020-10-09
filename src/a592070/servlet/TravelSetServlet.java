package a592070.servlet;

import a592070.pojo.CarVO;
import a592070.pojo.HotelVO;
import a592070.pojo.RestaurantVO;
import a592070.service.TravelSetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utils.PageSupport;
import utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "TravelSetServlet", urlPatterns = "/TravelSetServlet")
public class TravelSetServlet extends HttpServlet {
    private TravelSetService service;
    private static final int pageSize = 10;
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
        String method = req.getParameter("method");
        if("optionCar".equals(method)){
            optionCar(req, resp);
        }else if("optionHotel".equals(method)){
            optionHotel(req, resp);
        }else if("optionRestaurant".equals(method)){
            optionRestaurant(req, resp);
        }
    }

    public void optionCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<CarVO> list = service.listCarVO();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        String currentPage = req.getParameter("nowPage");
        System.out.println(currentPage);
        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(list.size());

        int currentPageNo;
        if(!StringUtil.isEmpty(currentPage)) {
            pageSupport.setCurrentPage(Integer.parseInt(currentPage));
        }
        currentPageNo = pageSupport.getCurrentPage();

        int start = pageSupport.getPageSize()*(currentPageNo-1);
        int end = pageSupport.getPageSize()*currentPageNo;

        List<CarVO> currentList = list.subList(start,Math.min(end,pageSupport.getTotalCount()));

        objectNode.put("currentPage", currentPageNo);
        objectNode.put("totalPage", pageSupport.getTotalPageCount());
        objectNode.put("totalCount", pageSupport.getTotalCount());
        objectNode.put("currentPageList", mapper.writeValueAsString(currentList));
        mapper.writeValue(resp.getWriter(), objectNode);
    }
    public void optionHotel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<HotelVO> list = service.listHotel();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        String currentPage = req.getParameter("nowPage");
        System.out.println(currentPage);
        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(list.size());

        int currentPageNo;
        if(!StringUtil.isEmpty(currentPage)) {
            pageSupport.setCurrentPage(Integer.parseInt(currentPage));
        }
        currentPageNo = pageSupport.getCurrentPage();

        int start = pageSupport.getPageSize()*(currentPageNo-1);
        int end = pageSupport.getPageSize()*currentPageNo;

        List<HotelVO> currentList = list.subList(start,Math.min(end,pageSupport.getTotalCount()));

        objectNode.put("currentPage", currentPageNo);
        objectNode.put("totalPage", pageSupport.getTotalPageCount());
        objectNode.put("totalCount", pageSupport.getTotalCount());
        objectNode.put("currentPageList", mapper.writeValueAsString(currentList));
        mapper.writeValue(resp.getWriter(), objectNode);
    }

    public void optionRestaurant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        List<RestaurantVO> list = service.listRestaurant();

        String currentPage = req.getParameter("nowPage");
        System.out.println(currentPage);
        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(list.size());

        int currentPageNo;
        if(!StringUtil.isEmpty(currentPage)) {
            pageSupport.setCurrentPage(Integer.parseInt(currentPage));
        }
        currentPageNo = pageSupport.getCurrentPage();

        int start = pageSupport.getPageSize()*(currentPageNo-1);
        int end = pageSupport.getPageSize()*currentPageNo;

        List<RestaurantVO> currentList = list.subList(start,Math.min(end,pageSupport.getTotalCount()));

        objectNode.put("currentPage", currentPageNo);
        objectNode.put("totalPage", pageSupport.getTotalPageCount());
        objectNode.put("totalCount", pageSupport.getTotalCount());
        objectNode.put("currentPageList", mapper.writeValueAsString(currentList));
        mapper.writeValue(resp.getWriter(), objectNode);
    }
}
