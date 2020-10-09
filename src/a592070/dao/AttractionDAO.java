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
    private int size;

    public AttractionDAO(int connType) throws IOException {
        ds = ConnectionPool.getDataSource(connType);
    }

    public int getSize() throws SQLException {
        sql = "select count(1) from attraction";
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            rs = predStmt.executeQuery();
            if(rs.next()){
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return size;
    }

    public AttractionDO getAttraction(int id) throws SQLException {
        return getAttraction("a_sn", id);
    }
    public AttractionDO getAttraction(String columnName, Object columnValue) throws SQLException {
        sql = "select * from attraction where \""+columnName.toUpperCase()+"\"=? ";
        Object[] params = {columnValue};
        AttractionDO attractionDO = null;
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, params);
            rs = predStmt.executeQuery();
            if (rs.next()) {
                attractionDO = new AttractionDO();
                attractionDO.setSn(rs.getInt("a_sn"));
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
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return attractionDO;
    }
    public List<AttractionDO> listAttractionLike(String columnName, String columnValue) throws SQLException {
        sql = "select * from attraction where \""+columnName.toUpperCase()+"\" like ?";
        Object[] params = {"%"+columnValue+"%"};
        List<AttractionDO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, params);
            rs = predStmt.executeQuery();
            AttractionDO attractionDO = null;
            while (rs.next()) {
                attractionDO = new AttractionDO();
                attractionDO.setSn(rs.getInt("a_sn"));
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
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

    public int getAttractionKeyWordsSize(String keyWords) throws SQLException {
        try{
            conn = ds.getConnection();
            sql = "select count(1) from attraction where name like ? or toldescribe like ? or description like ? or address like ? or keywords like ?  ";
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{"%" + keyWords + "%", "%" + keyWords + "%", "%" + keyWords + "%", "%" + keyWords + "%", "%" + keyWords + "%"});
            rs = predStmt.executeQuery();
            if(rs.next()){
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return size;
    }
    public List<AttractionDO> listAttractionLike(int startIndex, int endIndex, String keyWords) throws SQLException {
        Object[] params;

        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select rownum, t2.* from(")
                .append("select rownum rn, t.* ")
                .append("from (select * from attraction ");
        if(!StringUtil.isEmpty(keyWords)) {
            sqlBuffer.append("where name like ? or toldescribe like ? or description like ? or address like ? or keywords like ? ");
            params = new Object[]{"%" + keyWords + "%", "%" + keyWords + "%", "%" + keyWords + "%", "%" + keyWords + "%", "%" + keyWords + "%", startIndex, endIndex};
        }else{
            params = new Object[]{startIndex, endIndex};
        }
        sqlBuffer.append("order by picture, RATING desc) t)t2 ")
                .append("where rn between ? and ?");

        sql = sqlBuffer.toString();

        List<AttractionDO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, params);
            rs = predStmt.executeQuery();
            AttractionDO attractionDO = null;
            while (rs.next()) {
                attractionDO = new AttractionDO();
                attractionDO.setSn(rs.getInt("a_sn"));
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
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

    public int getAttractionRegionSize(String region) throws SQLException {
        try{
            conn = ds.getConnection();
            sql = "select count(1) from attraction where region like concat(concat('%', ?), '%') ";
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{region});
            rs = predStmt.executeQuery();
            if(rs.next()){
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return size;
    }
    public List<AttractionDO> listAttractionByRownum(int startIndex, int endIndex, String region) throws SQLException {
        List<AttractionDO> list = new ArrayList<>();

        Object[] params = null;

        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select rownum, t2.* from(")
                .append("select rownum rn, t.* ")
                .append("from (select * from attraction ");
        if(!StringUtil.isEmpty(region)) {
            sqlBuffer.append("where region like concat(concat('%', ?), '%') ");
            params = new Object[]{region, startIndex, endIndex};
        }else{
            params = new Object[]{startIndex, endIndex};
        }
        sqlBuffer.append("order by picture, RATING desc) t)t2 ")
                .append("where rn between ? and ?");

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
                attractionDO.setSn(rs.getInt("a_sn"));
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
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

}
