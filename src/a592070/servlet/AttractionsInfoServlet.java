package a592070.servlet;


import a592070.pojo.AttractionDO;
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
import java.util.List;

@WebServlet(name = "AttractionsInfoServlet", urlPatterns = "/AttractionsInfoServlet")
public class AttractionsInfoServlet extends HttpServlet {
    private AttractionService attractionService;
    private static final int pageSize = 50;


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
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode objectNode = mapper.createObjectNode();

            String currentPage = req.getParameter("nowPage");
            String area = req.getParameter("area");
            System.out.println(currentPage);
            System.out.println(area);

            PageSupport pageSupport = new PageSupport();
            pageSupport.setPageSize(pageSize);
            pageSupport.setTotalCount(attractionService.getSize());

            if (!StringUtil.isEmpty(currentPage)) {
                pageSupport.setCurrentPage(Integer.parseInt(currentPage));
            }

            int currentPageNo = pageSupport.getCurrentPage();

            List<AttractionDO> list = attractionService.listEle(currentPageNo, pageSupport.getPageSize(), area);

            objectNode.put("currentPage", currentPageNo);
            objectNode.put("totalPage", pageSupport.getTotalPageCount());
            objectNode.put("currentPageList", mapper.writeValueAsString(list));
            mapper.writeValue(resp.getWriter(), objectNode);
        }catch (Exception e){
            e.printStackTrace();
        }

//        if(currentPage == null){
//            objectNode.put("totalPage", listInfoVO.size()/50);
//            objectNode.put("nowPageInfo", mapper.writeValueAsString(currentList.subList(0, 50)));
//            mapper.writeValue(resp.getWriter(), objectNode);
//        }else{
//            // page = 0,1,2,3,...
//            int page = Integer.parseInt(currentPage);
//            int start = 50*page;
//            int end = Math.min(start + 50, listInfoVO.size());
//
//            mapper.writeValue(resp.getWriter(), listInfoVO.subList(start, end));
//        }
    }
}
