package a592070.dao;

import a592070.pojo.HotelVO;
import a592070.pojo.RestaurantVO;
import controller.ConnectionPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantViewDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;

    public RestaurantViewDAO(int connType) throws IOException {
        ds = ConnectionPool.getDataSource(connType);
    }

    public RestaurantVO getEle(int id) throws IOException, SQLException {
        return getEle("r_sn", id);
    }
    public RestaurantVO getEle(String columnName, Object columnValue) throws IOException, SQLException {
        sql = "select * " +
                "from restaurant " +
                "where \""+columnName.toUpperCase()+"\"=? " +
                "order by r_sn";
        Object[] params = {columnValue};
        RestaurantVO restaurantVO  = null;
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, params);
            rs = predStmt.executeQuery();
            if (rs.next()) {
                restaurantVO = new RestaurantVO();
                restaurantVO.setSn(rs.getInt("r_sn"));
                restaurantVO.setName(rs.getString("name"));
                restaurantVO.setAddress(rs.getString("address"));
                restaurantVO.setPicture(rs.getString("picture"));
                restaurantVO.setDescription(rs.getString("description"));
                restaurantVO.setType(rs.getString("type"));
                restaurantVO.setRating(rs.getBigDecimal("rating"));
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return restaurantVO;
    }

    public List<RestaurantVO> listEle() throws SQLException {
        sql = "select * " +
                "from restaurant " +
                "order by r_sn";
        List<RestaurantVO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            rs = predStmt.executeQuery();
            while (rs.next()) {
                RestaurantVO restaurantVO = new RestaurantVO();
                restaurantVO.setSn(rs.getInt("r_sn"));
                restaurantVO.setName(rs.getString("name"));
                restaurantVO.setAddress(rs.getString("address"));
                restaurantVO.setPicture(rs.getString("picture"));
                restaurantVO.setDescription(rs.getString("description"));
                restaurantVO.setType(rs.getString("type"));
                restaurantVO.setRating(rs.getBigDecimal("rating"));

                list.add(restaurantVO);
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

    public List<RestaurantVO> listEleByRownum(int startIndex, int endIndex) throws SQLException {
        sql = "select rownum, t.* " +
                "from (select rownum rn, restaurant.* " +
                "from restaurant " +
                "order by r_sn) t " +
                "where rn between ? and ?";
        List<RestaurantVO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{startIndex, endIndex});
            rs = predStmt.executeQuery();
            while (rs.next()) {
                RestaurantVO restaurantVO = new RestaurantVO();
                restaurantVO.setSn(rs.getInt("r_sn"));
                restaurantVO.setName(rs.getString("name"));
                restaurantVO.setAddress(rs.getString("address"));
                restaurantVO.setPicture(rs.getString("picture"));
                restaurantVO.setDescription(rs.getString("description"));
                restaurantVO.setType(rs.getString("type"));
                restaurantVO.setRating(rs.getBigDecimal("rating"));

                list.add(restaurantVO);
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }
}
