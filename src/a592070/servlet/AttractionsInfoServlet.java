package a592070.servlet;


import a592070.pojo.AttractionDO;
import a592070.pojo.RegionDO;
import a592070.service.AttractionService;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AttractionsInfoServlet", urlPatterns = "/AttractionsInfoServlet")
public class AttractionsInfoServlet extends HttpServlet {
    private AttractionService attractionService;
    private static final int pageSize = 50;


    @Override
    public void init() throws ServletException {
        super.init();
        attractionService = new AttractionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("initPageRegion".equals(method)){
            initPageRegion(req, resp);
        }else if("selectPage".equals(method)){
            selectPage(req, resp);
        }

    }


    public void initPageRegion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        List<RegionDO> regions = attractionService.listRegion();

        objectNode.put("regions", mapper.writeValueAsString(regions));
        mapper.writeValue(resp.getWriter(), objectNode);

    }
    public void selectPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        String currentPage = req.getParameter("nowPage");
        String area = req.getParameter("area");
        String keyword = req.getParameter("keyword");

        List<AttractionDO> list;

        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);

        int currentPageNo;
        if(!StringUtil.isEmpty(currentPage)) {
            pageSupport.setCurrentPage(Integer.parseInt(currentPage));
        }
        currentPageNo = pageSupport.getCurrentPage();

        if(!StringUtil.isEmpty(keyword)){
            pageSupport.setTotalCount(attractionService.getKeywordLimitSize(keyword));
            list = attractionService.searchEle(currentPageNo, pageSupport.getPageSize(), keyword);
        }else if(!StringUtil.isEmpty(area)){
            pageSupport.setTotalCount(attractionService.getRegionLimitSize(area));
            list = attractionService.listEle(currentPageNo, pageSupport.getPageSize(), area);
        }else{
            pageSupport.setTotalCount(attractionService.getTotalSize());
            list = attractionService.listEle(currentPageNo, pageSupport.getPageSize());
        }

        objectNode.put("currentPage", currentPageNo);
        objectNode.put("totalPage", pageSupport.getTotalPageCount());
        objectNode.put("totalCount", pageSupport.getTotalCount());
        objectNode.put("currentPageList", mapper.writeValueAsString(list));
        mapper.writeValue(resp.getWriter(), objectNode);

    }
//    public void selectKeyword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            ObjectNode objectNode = mapper.createObjectNode();
//
//            String currentPage = req.getParameter("nowPage");
//            String keyword = req.getParameter("keyword");
//            System.out.println(currentPage);
//            System.out.println(keyword);
//
//            PageSupport pageSupport = new PageSupport();
//            pageSupport.setPageSize(pageSize);
//
//            if (!StringUtil.isEmpty(keyword)) {
//                pageSupport.setTotalCount(attractionService.getKeywordLimitSize(keyword));
//            } else {
//                pageSupport.setTotalCount(attractionService.getTotalSize());
//            }
//
//            if (!StringUtil.isEmpty(currentPage)) {
//                pageSupport.setCurrentPage(Integer.parseInt(currentPage));
//            }
//
//            int currentPageNo = pageSupport.getCurrentPage();
//
//
//            List<AttractionDO> list = attractionService.searchEle(currentPageNo, pageSupport.getPageSize(), keyword);
//
//
//            objectNode.put("currentPage", currentPageNo);
//            objectNode.put("totalPage", pageSupport.getTotalPageCount());
//            objectNode.put("totalCount", pageSupport.getTotalCount());
//            objectNode.put("currentPageList", mapper.writeValueAsString(list));
//            mapper.writeValue(resp.getWriter(), objectNode);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
