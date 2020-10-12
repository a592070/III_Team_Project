package a592070.dao;

import a592070.pojo.CarVO;
import a592070.pojo.HotelVO;
import controller.ConnectionPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelViewDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;

    public HotelViewDAO(int connType) throws IOException {
        ds = ConnectionPool.getDataSource(connType);
    }

    public HotelVO getEle(int id) throws IOException, SQLException {
        return getEle("sn", id);
    }
    public HotelVO getEle(String columnName, Object columnValue) throws SQLException {
        sql = "select * " +
                "from hotel " +
                "where \""+columnName.toUpperCase()+"\"=? " +
                "order by sn";
        Object[] params = {columnValue};
        HotelVO hotelVO  = null;
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, params);
            rs = predStmt.executeQuery();
            if (rs.next()) {
                hotelVO = new HotelVO();
                hotelVO.setSn(rs.getInt("sn"));
                hotelVO.setName(rs.getString("name"));
                hotelVO.setAddress(rs.getString("address"));
                hotelVO.setDoubleRoomPrice(rs.getInt("double_room"));
                hotelVO.setQuadrupleRoomPrice(rs.getInt("quadruple_room"));
                hotelVO.setRating(rs.getBigDecimal("rating"));
                hotelVO.setDescription(rs.getString("description"));
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return hotelVO;
    }

    public List<HotelVO> listEle() throws SQLException {
        sql = "select * " +
                "from hotel " +
                "order by sn";
        List<HotelVO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            rs = predStmt.executeQuery();
            while (rs.next()) {
                HotelVO hotelVO = new HotelVO();
                hotelVO.setSn(rs.getInt("sn"));
                hotelVO.setName(rs.getString("name"));
                hotelVO.setAddress(rs.getString("address"));
                hotelVO.setDoubleRoomPrice(rs.getInt("double_room"));
                hotelVO.setQuadrupleRoomPrice(rs.getInt("quadruple_room"));
                hotelVO.setRating(rs.getBigDecimal("rating"));
                hotelVO.setDescription(rs.getString("description"));

                list.add(hotelVO);
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

    public List<HotelVO> listEleByRownum(int startIndex, int endIndex) throws SQLException {
        sql = "select rownum, t.* " +
                "from (select rownum rn, hotel.* " +
                "from hotel " +
                "order by sn) t " +
                "where rn between ? and ?";
        List<HotelVO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{startIndex, endIndex});
            rs = predStmt.executeQuery();
            while (rs.next()) {
                HotelVO hotelVO = new HotelVO();
                hotelVO.setSn(rs.getInt("sn"));
                hotelVO.setName(rs.getString("name"));
                hotelVO.setAddress(rs.getString("address"));
                hotelVO.setDoubleRoomPrice(rs.getInt("double_room"));
                hotelVO.setQuadrupleRoomPrice(rs.getInt("quadruple_room"));
                hotelVO.setRating(rs.getBigDecimal("rating"));
                hotelVO.setDescription(rs.getString("description"));

                list.add(hotelVO);
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }
}
