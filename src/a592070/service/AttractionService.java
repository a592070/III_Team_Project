package a592070.service;

import a592070.dao.AttractionDAO;
import a592070.dao.RegionDAO;
import a592070.pojo.AttractionDO;
import a592070.pojo.RegionDO;
import controller.ConnectionPool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AttractionService {
    private AttractionDAO attractionDAO;
    public Integer size;

    public AttractionService() throws IOException {
        this.attractionDAO = new AttractionDAO(ConnectionPool.LOADING_WITH_SERVER);
    }

    public int getTotalSize(){
        size = attractionDAO.getSize();
        return size;
    }
    public int getRegionLimitSize(String region){
        return attractionDAO.getAttractionRegionSize(region);
    }
    public int getKeywordLimitSize(String keyword){
        return attractionDAO.getAttractionKeyWordsSize(keyword);
    }

    public AttractionDO getEle(int id) throws IOException, SQLException {
         return attractionDAO.getAttraction(id);
    }
    public AttractionDO getEleByValue(String column, Object value) throws IOException, SQLException {
        return attractionDAO.getAttraction(column, value);
    }
    public List<AttractionDO> listEle(int currentPage, int pageSize){
        return listEle(currentPage, pageSize, null);
    }
    public List<AttractionDO> listEle(int currentPage, int pageSize, String region){
        // 從0開始
        int start = pageSize*(currentPage-1)+1;
        int end = pageSize*currentPage;
        return attractionDAO.listAttractionByRownum(start,end, region);
    }

    public List<AttractionDO> searchEle(int currentPage, int pageSize, String keywords) throws IOException, SQLException {
        int start = pageSize*(currentPage-1)+1;
        int end = pageSize*currentPage;
        return attractionDAO.listAttractionLike(start,end, keywords);
    }

    public List<RegionDO> listRegion() throws IOException {
        RegionDAO regionDAO = new RegionDAO(ConnectionPool.LOADING_WITH_SERVER);
        return regionDAO.listRegion();
    }

}
