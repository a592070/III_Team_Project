package a592070.servlet;

import a592070.pojo.*;
import a592070.service.TravelSetService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import globalinit.Constant;
import utils.PageSupport;
import utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;


@WebServlet(name = "TravelSetServlet", urlPatterns = "/TravelSetServlet")
public class TravelSetServlet extends HttpServlet {
    private TravelSetService service;
    private static final int pageSize = 10;
    private static final String OPTION_C = "optionCar";
    private static final String OPTION_H = "optionHotel";
    private static final String OPTION_R = "optionRestaurant";
    private static final String OPTION_A = "optionAttraction";
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
        if("initTravelSet".equals(method)){
            initTravelSet(req,resp);
        }else if("saveTravelSet".equals(method)){
            saveTravelSet(req,resp);
        }else if("newTravelSet".equals(method)){
            newTravelSet(req,resp);
        }else if("setItems".equals(method)){
            setItems(req, resp);
        }else if("setItemsTime".equals(method)){
            setItemsTime(req, resp);
        }else if(OPTION_C.equals(method)){
            optionCar(req, resp);
        }else if(OPTION_H.equals(method)){
            optionHotel(req, resp);
        }else if(OPTION_R.equals(method)){
            optionRestaurant(req, resp);
        }else if(OPTION_A.equals(method)){
            optionAttraction(req, resp);
        }
    }

    private void initTravelSet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();

        String sSn = req.getParameter("sn");
        int sn = 0;
        TravelSetDO travelSet;
        if(!StringUtil.isEmpty(sSn)){
            sn = Integer.parseInt(sSn);
            travelSet = service.getTravelSet(sn);
            session.setAttribute(Constant.TravelSetEdit_session, travelSet);
        }else if(session.getAttribute(Constant.TravelSetEdit_session) != null){
            travelSet = (TravelSetDO) session.getAttribute(Constant.TravelSetEdit_session);
        }else{
            travelSet = new TravelSetDO();
//            travelSet.setCreatedUser();
            session.setAttribute(Constant.TravelSetEdit_session, travelSet);
            return;
        }

//        System.out.println("TravelSetServlet\t"+session.getId());
//        List<TravelSetDO> list = (List<TravelSetDO>) session.getAttribute(Constant.TravelSetList_session);
//        TravelSetDO travelSet = null;
//        for (TravelSetDO ele : list) {
//            if(ele.getSn() == sn) {
//                travelSet = ele;
//                break;
//            }
//        }


        ObjectMapper mapper = new ObjectMapper();

        ObjectNode jsonObj = mapper.createObjectNode();;
        ArrayNode arrayNode = mapper.createArrayNode();

        List<TravelEleCarDO> listEleCar = travelSet.getListTravelCar();
        for (TravelEleCarDO ele : listEleCar) {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("sn", ele.getSn());
//            objectNode.put("name", ele.getCar().getCarType());
//            objectNode.put("price", ele.getCar().getPrice());
//            objectNode.put("company", ele.getCar().getCompany());
//            if(ele.getTime() != null) objectNode.put("time", ele.getTime().getTime());

            String sJson = mapper.writeValueAsString(ele.getCar());
            arrayNode.add(sJson);
//            arrayNode.add(objectNode);
        }

        jsonObj.set("listEleCar", arrayNode);
        arrayNode = mapper.createArrayNode();

        List<TravelEleHotelDO> listEleHotel = travelSet.getListTravelHotel();
        for (TravelEleHotelDO ele : listEleHotel) {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("sn", ele.getSn());
//            objectNode.put("name", ele.getHotel().getName());
//            objectNode.put("doubleRoomPrice", ele.getHotel().getDoubleRoomPrice());
//            objectNode.put("quadrupleRoomPrice", ele.getHotel().getQuadrupleRoomPrice());
//            objectNode.put("address", ele.getHotel().getAddress());
//            objectNode.put("rating", ele.getHotel().getRating());
//            if(ele.getTime() != null) objectNode.put("time", ele.getTime().getTime());
//            arrayNode.add(objectNode);
            String sJson = mapper.writeValueAsString(ele.getHotel());
            arrayNode.add(sJson);
        }

        jsonObj.set("listEleHotel", arrayNode);
        arrayNode = mapper.createArrayNode();

        List<TravelEleRestaurantDO> listEleRestaurant = travelSet.getListTravelRestaurant();
        for (TravelEleRestaurantDO ele : listEleRestaurant) {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("sn", ele.getSn());
//            objectNode.put("name", ele.getRestaurant().getName());
//            objectNode.put("type", ele.getRestaurant().getType());
//            objectNode.put("address", ele.getRestaurant().getAddress());
//            objectNode.put("rating", ele.getRestaurant().getRating());
//            if(ele.getTime() != null) objectNode.put("time", ele.getTime().getTime());
//            arrayNode.add(objectNode);
            String sJson = mapper.writeValueAsString(ele.getRestaurant());
            arrayNode.add(sJson);
        }

        jsonObj.set("listEleRestaurant", arrayNode);
        arrayNode = mapper.createArrayNode();

        List<TravelEleAttractionDO> listEleAttraction = travelSet.getListTravelAttraction();
        for (TravelEleAttractionDO ele : listEleAttraction) {
//            ObjectNode objectNode = mapper.createObjectNode();
//            objectNode.put("sn", ele.getSn());
//            objectNode.put("name", ele.getAttraction().getName());
//            objectNode.put("address", ele.getAttraction().getAddress());
//            objectNode.put("ticketInfo", ele.getAttraction().getTicketInfo());
//            if(ele.getTime() != null) objectNode.put("time", ele.getTime().getTime());
//            arrayNode.add(objectNode);
            String sJson = mapper.writeValueAsString(ele.getAttraction());
            arrayNode.add(sJson);
        }

        jsonObj.set("listEleAttraction", arrayNode);

        mapper.writeValue(resp.getWriter(), jsonObj);
    }
    private void newTravelSet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();

        String name = req.getParameter("travelSetName");
        String description = req.getParameter("travelSetDescription");


        TravelSetDO travelSet = (TravelSetDO)session.getAttribute(Constant.TravelSetEdit_session);
        travelSet.setName(name);
        travelSet.setDescription(description);
//        System.out.println(travelSet);
        boolean flag = service.addTravelSet(travelSet);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        objectNode.put("status", flag);
        mapper.writeValue(resp.getWriter(), objectNode);
    }

    private void saveTravelSet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();

        String name = req.getParameter("travelSetName");
        String description = req.getParameter("travelSetDescription");


        TravelSetDO travelSet = (TravelSetDO)session.getAttribute(Constant.TravelSetEdit_session);
        travelSet.setName(name);
        travelSet.setDescription(description);

//        System.out.println(travelSet);

        boolean flag = service.updateTravelSet(travelSet);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        objectNode.put("status", flag);
        mapper.writeValue(resp.getWriter(), objectNode);

    }

    private void setItemsTime(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String selectType = req.getParameter("selectType");
        HttpSession session = req.getSession();

        String sIndex = req.getParameter("index");
        int index = 0;
        if(!StringUtil.isEmpty(sIndex)) index = Integer.parseInt(sIndex);
        String sTime = req.getParameter("time");
        System.out.println(sTime);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if(!StringUtil.isEmpty(sTime)) time = new Timestamp(Long.parseLong(sTime));

        System.out.println(time);

        TravelSetDO travelSet = (TravelSetDO) session.getAttribute(Constant.TravelSetEdit_session);

        if(OPTION_C.equals(selectType)){
            travelSet.getListTravelCar().get(index).setTime(time);
        }else if(OPTION_H.equals(selectType)){
            travelSet.getListTravelHotel().get(index).setTime(time);
        }else if(OPTION_R.equals(selectType)){
            travelSet.getListTravelRestaurant().get(index).setTime(time);
        }else if(OPTION_A.equals(selectType)){
            travelSet.getListTravelAttraction().get(index).setTime(time);
        }
//        System.out.println((TravelSetDO) session.getAttribute(Constant.TravelSetEdit_session));
    }

    private void setItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String selectType = req.getParameter("selectType");
        HttpSession session = req.getSession();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        String sList = req.getParameter("list");

        TravelSetDO travelSet;
        if(session.getAttribute(Constant.TravelSetEdit_session) == null){
            travelSet = new TravelSetDO();
        }else{
            travelSet = (TravelSetDO) session.getAttribute(Constant.TravelSetEdit_session);
        }

        if(OPTION_C.equals(selectType)){
            List<CarVO> selectList = new ArrayList<>();
            if(!StringUtil.isEmpty(sList)){
                selectList = mapper.readValue(sList, new TypeReference<List<CarVO>>() {});
            }
            List<TravelEleCarDO>  travelEleList = new ArrayList<>();
            for (CarVO ele : selectList) {
                TravelEleCarDO travelEleDO = new TravelEleCarDO();
                travelEleDO.setTravelId(travelSet.getSn());
                travelEleDO.setCar(ele);
                travelEleList.add(travelEleDO);
            }
            travelSet.setListTravelCar(travelEleList);

        }else if(OPTION_H.equals(selectType)){
            List<HotelVO> selectList = new ArrayList<>();
            if(!StringUtil.isEmpty(sList)){
                selectList = mapper.readValue(sList, new TypeReference<List<HotelVO>>() {});
            }
            List<TravelEleHotelDO>  travelEleList = new ArrayList<>();
            for (HotelVO ele : selectList) {
                TravelEleHotelDO travelEleDO = new TravelEleHotelDO();
                travelEleDO.setTravelId(travelSet.getSn());
                travelEleDO.setHotel(ele);
                travelEleList.add(travelEleDO);
            }
            travelSet.setListTravelHotel(travelEleList);

        }else if(OPTION_R.equals(selectType)){
            List<RestaurantVO> selectList = new ArrayList<>();
            if(!StringUtil.isEmpty(sList)){
                selectList = mapper.readValue(sList, new TypeReference<List<RestaurantVO>>() {});
            }
            List<TravelEleRestaurantDO>  travelEleList = new ArrayList<>();
            for (RestaurantVO ele : selectList) {
                TravelEleRestaurantDO travelEleDO = new TravelEleRestaurantDO();
                travelEleDO.setTravelId(travelSet.getSn());
                travelEleDO.setRestaurant(ele);
                travelEleList.add(travelEleDO);
            }
            travelSet.setListTravelRestaurant(travelEleList);

        }else if(OPTION_A.equals(selectType)){
            List<AttractionVO> selectList = new ArrayList<>();
            if(!StringUtil.isEmpty(sList)){
                selectList = mapper.readValue(sList, new TypeReference<List<AttractionVO>>() {});
            }
            List<TravelEleAttractionDO>  travelEleList = new ArrayList<>();
            for (AttractionVO ele : selectList) {
                TravelEleAttractionDO travelEleDO = new TravelEleAttractionDO();
                travelEleDO.setTravelId(travelSet.getSn());
                travelEleDO.setAttraction(ele);
                travelEleList.add(travelEleDO);
            }
            travelSet.setListTravelAttraction(travelEleList);
        }
        session.setAttribute(Constant.TravelSetEdit_session, travelSet);

//        System.out.println((TravelSetDO) session.getAttribute(Constant.TravelSetEdit_session));
    }

    private void optionAttraction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        String currentPage = req.getParameter("nowPage");
        String area = req.getParameter("area");

        List<AttractionVO> list;

        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);

        int currentPageNo;
        if(!StringUtil.isEmpty(currentPage)) {
            pageSupport.setCurrentPage(Integer.parseInt(currentPage));
        }
        currentPageNo = pageSupport.getCurrentPage();

        if(!StringUtil.isEmpty(area)){
            list = service.listAttraction(currentPageNo, pageSupport.getPageSize(), area);
            pageSupport.setTotalCount(service.getRegionLimitSize(area));
        }else{
            pageSupport.setTotalCount(service.getTotalSize());
            list = service.listAttraction(currentPageNo, pageSupport.getPageSize());
        }

        objectNode.put("currentPage", currentPageNo);
        objectNode.put("totalPage", pageSupport.getTotalPageCount());
        objectNode.put("totalCount", pageSupport.getTotalCount());
        objectNode.put("currentPageList", mapper.writeValueAsString(list));
        mapper.writeValue(resp.getWriter(), objectNode);
    }

    public void optionCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<CarVO> list = service.listCarVO();

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        String currentPage = req.getParameter("nowPage");

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


        String currentPage = req.getParameter("nowPage");
        String area = req.getParameter("area");


        List<RestaurantVO> list;
        if(!StringUtil.isEmpty(area)){
            area = area.replace("臺", "台").substring(0,2);
            list = service.listRestaurant(area);
        }else{
            list = service.listRestaurant();
        }


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
