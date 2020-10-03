package a592070.dao;

import controller.ConnectionPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AttractionSetDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;

    public AttractionSetDAO() throws IOException {
        ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
    }



}
