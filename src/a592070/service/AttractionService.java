package a592070.service;

import a592070.dao.AttractionDAO;
import a592070.pojo.AttractionDO;
import controller.ConnectionPool;
import utils.StringUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AttractionService {
    private AttractionDAO attractionDAO;
    public static Integer size;

    public AttractionService() throws IOException {
        this.attractionDAO = new AttractionDAO(ConnectionPool.LOADING_WITH_SERVER);
    }

    public int getSize(){
        if(size == null || size==0) size = attractionDAO.getSize();
        return size;
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
        currentPage = currentPage-1;
        return attractionDAO.listAttractionByRownum(pageSize*currentPage, pageSize*(currentPage+1), region);
    }

    public List<AttractionDO> searchEle(int currentPage, int pageSize, String keywords) throws IOException, SQLException {
        currentPage = currentPage-1;
        return attractionDAO.listAttractionLike(pageSize*currentPage, pageSize*(currentPage+1), keywords);
    }


}
