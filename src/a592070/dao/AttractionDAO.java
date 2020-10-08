package a592070.dao;

import a592070.pojo.AttractionDO;
import controller.ConnectionPool;
import utils.StringUtil;

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

    public AttractionDAO(int connType) throws IOException {
        ds = ConnectionPool.getDataSource(connType);
    }

    public AttractionDO getEle(int id) throws SQLException, IOException {
        return getEle("a_sn", id);
    }
    public AttractionDO getEle(String columnName, Object columnValue) throws SQLException, IOException {
        sql = "select * from attraction where \""+columnName.toUpperCase()+"\"=? ";
        Object[] params = {columnValue};
        AttractionDO attractionDO = null;
        try {
            conn = ds.getConnection();
            rs = ConnectionPool.execute(conn, predStmt, rs, sql, params);
            if (rs.next()) {
                attractionDO = new AttractionDO();
                attractionDO.setId(rs.getInt("a_sn"));
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
        }catch (SQLException| IOException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return attractionDO;
    }
    public List<AttractionDO> listEleLike(String columnName, String columnValue) throws SQLException, IOException {
        sql = "select * from attraction where \""+columnName.toUpperCase()+"\" like ?";
        Object[] params = {"%"+columnValue+"%"};
        List<AttractionDO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            rs = ConnectionPool.execute(conn, predStmt, rs, sql, params);
            AttractionDO attractionDO = null;
            while (rs.next()) {
                attractionDO = new AttractionDO();
                attractionDO.setId(rs.getInt("a_sn"));
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
        }catch (SQLException| IOException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }
    public List<AttractionDO> listEleLike(String keyWords) throws SQLException, IOException {
        sql = "select * from attraction where name like ? or toldescribe like ? or description like ? or address like ? or keywords like ?";
        Object[] params = {"%"+keyWords+"%", "%"+keyWords+"%", "%"+keyWords+"%", "%"+keyWords+"%", "%"+keyWords+"%"};

        List<AttractionDO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            rs = ConnectionPool.execute(conn, predStmt, rs, sql, params);
            AttractionDO attractionDO = null;
            while (rs.next()) {
                attractionDO = new AttractionDO();
                attractionDO.setId(rs.getInt("a_sn"));
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
        }catch (SQLException| IOException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

    public List<AttractionDO> listEleByRownum(int startIndex, int endIndex){
        return listEleByRownum(startIndex, endIndex, null);
    }
    public List<AttractionDO> listEleByRownum(int startIndex, int endIndex, String region){
        List<AttractionDO> list = new ArrayList<>();

        Object[] params = null;

        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select rownum, t.* ")
                .append("from (select rownum rn, attraction.* case PICTURE when 'NULL' then 0 else 1 end as p from attraction ");
        if(!StringUtil.isEmpty(region)) {
            sqlBuffer.append("where region like concat(concat('%', ?), '%') ");
            params = new Object[]{region, startIndex, endIndex};
        }else{
            params = new Object[]{startIndex, endIndex};
        }
        sqlBuffer.append("order by p desc, RATING desc) t ")
                .append("where rn>=? and rn<=?");

//        sql =   "select rownum, t.* " +
//                "from (select rownum rn, attraction.* case PICTURE when 'NULL' then 0 else 1 end as p from attraction where region like concat(concat('%', ?), '%') order by p desc, RATING desc) t " +
//                "where rn>=? and rn<=?";
        sql = sqlBuffer.toString();

        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, params);
            rs = predStmt.executeQuery();

            while(rs.next()){
                AttractionDO attractionDO = new AttractionDO();
                attractionDO.setId(rs.getInt("a_sn"));
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
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

}
