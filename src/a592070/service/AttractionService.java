package a592070.service;

import a592070.dao.AttractionDAO;
import a592070.dao.RegionDAO;
import a592070.pojo.AttractionDO;
import a592070.pojo.RegionDO;
import org.hibernate.Session;

import java.util.List;

public class AttractionService {
    private AttractionDAO attractionDAO;
    public Integer size;
    private RegionDAO regionDAO;

    public AttractionService() {
    }

    public AttractionService(Session session){
        attractionDAO = new AttractionDAO(session);
        regionDAO = new RegionDAO(session);
    }

    public void setSession(Session session){
        attractionDAO = new AttractionDAO(session);
        regionDAO = new RegionDAO(session);
    }

    public int getTotalSize(){
        try {
            size = attractionDAO.getSize();
        } catch (Exception e) {
            new RuntimeException("AttractionDAO getSize()錯誤\n"+e).printStackTrace();
        }
        return size;
    }
    public int getRegionLimitSize(String region){
        int size = 0;
        try {
            size = attractionDAO.getAttractionRegionSize(region);
        } catch (Exception e) {
            new RuntimeException("AttractionDAO getAttractionRegionSize()錯誤\n"+e).printStackTrace();
        }
        return size;
    }
    public int getKeywordLimitSize(String keyword){
        int size = 0;
        try {
            size = attractionDAO.getAttractionKeyWordsSize(keyword);
        } catch (Exception e) {
            new RuntimeException("AttractionDAO getAttractionKeyWordsSize()錯誤\n"+e).printStackTrace();
        }
        return size;
    }

    public AttractionDO getEle(int id)  {
        AttractionDO attraction = null;
        try {
            attraction = attractionDAO.getAttraction(id);
        } catch (Exception e) {
            new RuntimeException("AttractionDAO getAttraction()錯誤\n"+e).printStackTrace();
        }
        return attraction;
    }
    public AttractionDO getEleByValue(String column, Object value) {
        AttractionDO attraction = null;
        try {
            attraction = attractionDAO.getAttraction(column, value);
        } catch (Exception e) {
            new RuntimeException("AttractionDAO getAttraction()錯誤\n"+e).printStackTrace();
        }
        return attraction;
    }
    public List<AttractionDO> listEle(int currentPage, int pageSize){
        return listEle(currentPage, pageSize, null);
    }
    public List<AttractionDO> listEle(int currentPage, int pageSize, String region){
        // 從0開始
        int start = pageSize*(currentPage-1)+1;
        int end = pageSize*currentPage;
        List<AttractionDO> list = null;
        try {
            list = attractionDAO.listAttractionByRownum(start, end, region);
        } catch (Exception e) {
            new RuntimeException("AttractionDAO listAttractionByRownum()錯誤\n"+e).printStackTrace();
        }
        return list;
    }

    public List<AttractionDO> searchEle(int currentPage, int pageSize, String keywords) {
        int start = pageSize*(currentPage-1)+1;
        int end = pageSize*currentPage;
        List<AttractionDO> list = null;
        try {
            list = attractionDAO.listAttractionLike(start, end, keywords);
        } catch (Exception e) {
            new RuntimeException("AttractionDAO listAttractionLike()錯誤\n"+e).printStackTrace();
        }
        return list;
    }

    public List<RegionDO> listRegion() {
        List<RegionDO> list = null;
        try {
            list = regionDAO.listRegion();
        } catch (Exception e) {
            new RuntimeException("RegionDAO 錯誤\n"+e).printStackTrace();
        }
        return list;
    }

}
