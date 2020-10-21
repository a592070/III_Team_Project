package a592070.dao;

import a592070.pojo.RegionDO;
import controller.ConnectionPool;
import org.hibernate.Session;
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
    private Session session;

    public RegionDAO(int connType) throws IOException {
        ds = ConnectionPool.getDataSource(connType);
    }
    public RegionDAO(Session session){
        this.session = session;
    }
    public List<RegionDO> listRegion(){
        return session.createQuery("from RegionDO order by area", RegionDO.class).list();
    }

}
