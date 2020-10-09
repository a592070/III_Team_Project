package a592070.dao;

import a592070.pojo.RegionDO;
import controller.ConnectionPool;
import utils.StringUtil;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;

    public RegionDAO(int connType) throws IOException {
        ds = ConnectionPool.getDataSource(connType);
    }
    public List<RegionDO> listRegion() throws SQLException {
        List<RegionDO> list = new ArrayList<>();
        try{
            conn = ds.getConnection();
            sql = "select * from region";
            predStmt = conn.prepareStatement(sql);
            rs = predStmt.executeQuery();
            while(rs.next()){
                RegionDO region = new RegionDO();
                String region_name = rs.getString("region_name");
                if(StringUtil.isEmpty(region_name)) continue;
                region.setRegion(region_name);
                region.setArea(rs.getString("area"));

                list.add(region);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

}
