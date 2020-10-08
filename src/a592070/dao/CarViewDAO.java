package a592070.dao;

import a592070.pojo.AttractionDO;
import a592070.pojo.CarVO;
import controller.ConnectionPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarViewDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;

    public CarViewDAO(int connType) throws IOException {
        ds = ConnectionPool.getDataSource(connType);
    }

    public CarVO getEle(int id) throws IOException, SQLException {
        return getEle("sn_cartype", id);
    }
    public CarVO getEle(String columnName, Object columnValue) throws IOException, SQLException {
        sql = "select * " +
                "from cartype c1, carrentalcompany c2 " +
                "where c1."+columnName.toUpperCase()+"=? and c1.sn_rentcarcompany=c2.sn_rentalcompany " +
                "order by sn_cartype";
//                "select * from attraction where \""+columnName.toUpperCase()+"\"=? ";
        Object[] params = {columnValue};
        CarVO carVO = null;
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, params);
            rs = predStmt.executeQuery();
            if (rs.next()) {
                carVO = new CarVO();
                carVO.setSn(rs.getInt("sn_cartype"));
                carVO.setCarType(rs.getString("cartype"));
                carVO.setPrice(rs.getInt("price"));
                carVO.setCompany(rs.getString("name_company"));
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return carVO;
    }

    public List<CarVO> listEle() throws SQLException {
        sql = "select * " +
                "from cartype c1, carrentalcompany c2 " +
                "where c1.sn_rentcarcompany=c2.sn_rentalcompany " +
                "order by sn_cartype";
        List<CarVO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            rs = predStmt.executeQuery();
            while (rs.next()) {
                CarVO carVO = new CarVO();
                carVO.setSn(rs.getInt("sn_cartype"));
                carVO.setCarType(rs.getString("cartype"));
                carVO.setPrice(rs.getInt("price"));
                carVO.setCompany(rs.getString("name_company"));

                list.add(carVO);
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

    public List<CarVO> listEleByRownum(int startIndex, int endIndex) throws SQLException {
        sql = "select rownum, t.* " +
                "from (select rownum rn, c1.*, c2.NAME_COMPANY " +
                "from cartype c1, carrentalcompany c2 " +
                "where c1.sn_rentcarcompany = c2.sn_rentalcompany " +
                "order by sn_cartype) t " +
                "where rn between ? and ?";
        List<CarVO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{startIndex, endIndex});
            rs = predStmt.executeQuery();
            while (rs.next()) {
                CarVO carVO = new CarVO();
                carVO.setSn(rs.getInt("sn_cartype"));
                carVO.setCarType(rs.getString("cartype"));
                carVO.setPrice(rs.getInt("price"));
                carVO.setCompany(rs.getString("name_company"));

                list.add(carVO);
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

}
