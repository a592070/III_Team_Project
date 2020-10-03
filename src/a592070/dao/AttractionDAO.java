package a592070.dao;

import a592070.pojo.AttractionDO;
import controller.ConnectionPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttractionDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;

    public AttractionDAO() throws IOException {
        ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
    }

    public AttractionDO getEle(String columnName, String columnValue) throws SQLException, IOException {
        sql = "select * from attraction where \""+columnName.toUpperCase()+"\"=?";
        Object[] params = {columnValue};
        conn = ds.getConnection();
        rs = ConnectionPool.execute(conn, predStmt, rs, sql, params);
        AttractionDO attractionDO = null;
        if(rs.next()){
            attractionDO = new AttractionDO();
            attractionDO.setId(rs.getString("id"));
            attractionDO.setName(rs.getString("name"));
            attractionDO.setToldescribe(rs.getNString("toldescribe"));
            attractionDO.setDescription(rs.getNString("description"));
            attractionDO.setTel(rs.getString("tel"));
            attractionDO.setAddress(rs.getString("address"));
            attractionDO.setRegion(rs.getString("region"));
            attractionDO.setTravellingInfo(rs.getString("travellinginfo"));
            attractionDO.setOpenTime(rs.getString("opentime"));
            attractionDO.setPicture(rs.getString("picture"));
            attractionDO.setPx(rs.getBigDecimal("px"));
            attractionDO.setPy(rs.getBigDecimal("py"));
            attractionDO.setTicketInfo(rs.getString("ticketinfo"));
            attractionDO.setKeywords(rs.getString("keywords"));
            attractionDO.setRemarks(rs.getString("remarks"));
            attractionDO.setRating(rs.getBigDecimal("rating"));
        }
        ConnectionPool.closeResources(conn, predStmt, rs);
        return attractionDO;
    }
    public List<AttractionDO> listEleLike(String columnName, String columnValue) throws SQLException, IOException {
        sql = "select * from attraction where \""+columnName.toUpperCase()+"\" like ?";
        Object[] params = {"%"+columnValue+"%"};
        conn = ds.getConnection();
        rs = ConnectionPool.execute(conn, predStmt, rs, sql, params);
        AttractionDO attractionDO = null;
        List<AttractionDO> list = new ArrayList<>();
        while(rs.next()){
            attractionDO = new AttractionDO();
            attractionDO.setId(rs.getString("id"));
            attractionDO.setName(rs.getString("name"));
            attractionDO.setToldescribe(rs.getNString("toldescribe"));
            attractionDO.setDescription(rs.getNString("description"));
            attractionDO.setTel(rs.getString("tel"));
            attractionDO.setAddress(rs.getString("address"));
            attractionDO.setRegion(rs.getString("region"));
            attractionDO.setTravellingInfo(rs.getString("travellinginfo"));
            attractionDO.setOpenTime(rs.getString("opentime"));
            attractionDO.setPicture(rs.getString("picture"));
            attractionDO.setPx(rs.getBigDecimal("px"));
            attractionDO.setPy(rs.getBigDecimal("py"));
            attractionDO.setTicketInfo(rs.getString("ticketinfo"));
            attractionDO.setKeywords(rs.getString("keywords"));
            attractionDO.setRemarks(rs.getString("remarks"));
            attractionDO.setRating(rs.getBigDecimal("rating"));
            list.add(attractionDO);
        }
        ConnectionPool.closeResources(conn, predStmt, rs);
        return list;
    }
    public List<AttractionDO> listEleLike(String keyWords) throws SQLException, IOException {
        sql = "select * from attraction where name like ? or toldescribe like ? or description like ? or address like ? or keywords like ?";
        Object[] params = {"%"+keyWords+"%", "%"+keyWords+"%", "%"+keyWords+"%", "%"+keyWords+"%", "%"+keyWords+"%"};
        conn = ds.getConnection();
        rs = ConnectionPool.execute(conn, predStmt, rs, sql, params);
        AttractionDO attractionDO = null;
        List<AttractionDO> list = new ArrayList<>();
        while(rs.next()){
            attractionDO = new AttractionDO();
            attractionDO.setId(rs.getString("id"));
            attractionDO.setName(rs.getString("name"));
            attractionDO.setToldescribe(rs.getNString("toldescribe"));
            attractionDO.setDescription(rs.getNString("description"));
            attractionDO.setTel(rs.getString("tel"));
            attractionDO.setAddress(rs.getString("address"));
            attractionDO.setRegion(rs.getString("region"));
            attractionDO.setTravellingInfo(rs.getString("travellinginfo"));
            attractionDO.setOpenTime(rs.getString("opentime"));
            attractionDO.setPicture(rs.getString("picture"));
            attractionDO.setPx(rs.getBigDecimal("px"));
            attractionDO.setPy(rs.getBigDecimal("py"));
            attractionDO.setTicketInfo(rs.getString("ticketinfo"));
            attractionDO.setKeywords(rs.getString("keywords"));
            attractionDO.setRemarks(rs.getString("remarks"));
            attractionDO.setRating(rs.getBigDecimal("rating"));
            list.add(attractionDO);
        }
        ConnectionPool.closeResources(conn, predStmt, rs);
        return list;
    }

}
