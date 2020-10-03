package controller;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionPool {
    private static DataSource dataSource;
    public static final int LOADING_WITH_SERVER = 1;
    public static final int LOADING_WITHOUT_SERVER = 2;

    private static BasicDataSource basicDataSource;
    private String sUrl;
    private String sDriver;
    private String sUser;
    private String sPassword;
    private int sInitialSize;
    private int sMaxTotal;
    private int sMaxIdle;
    private int sMaxWait;
    private int sRemoveAbandonedTimeout;
    private int sAis_batch_num;
    private String sAutoCommit;


    public void init() throws IOException {
        try {
            Context initContext = new InitialContext();
            dataSource =  (DataSource) initContext.lookup("java:/comp/env/ConnectionPool");
            
        }catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(int type) throws IOException {
        if(dataSource == null) {
            if(type == LOADING_WITH_SERVER) new ConnectionPool().init();
            if(type == LOADING_WITHOUT_SERVER){
                ConnectionPool pool = new ConnectionPool();
                basicDataSource = new BasicDataSource();
                pool.readProperties();
                pool.setPool();
                dataSource = basicDataSource;
            }
        }
        return dataSource;
    }

    public static ResultSet execute(Connection conn, PreparedStatement predStmt, ResultSet rs, String sql, Object[] params) throws IOException, SQLException {
        predStmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            predStmt.setObject(i+1, params[i]);
        }
        rs = predStmt.executeQuery();
        return rs;
    }
    public static int execute(Connection conn, PreparedStatement predStmt, String sql, Object[] params) throws IOException, SQLException {
        int updateRows = 0;
        predStmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            predStmt.setObject(i+1, params[i]);
        }
        updateRows = predStmt.executeUpdate();
        return updateRows;
    }
    public static boolean closeResources(Connection conn, Statement stmt, ResultSet rs){
        boolean flag = true;
        if(rs != null){
            try {
                rs.close();
                rs = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        if(stmt != null){
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        if(conn != null){
            try {
                conn.close();
                conn = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

    private void readProperties() throws IOException {
        String sConfigFile = "db.properties";
        Properties properties = new Properties();
//        InputStream in = ConnectionPool.class.getClassLoader().getResourceAsStream(sConfigFile);
        properties.load(new FileReader("resources/db.properties"));
//        properties.load(in);

        sUrl = properties.getProperty("url");
        sDriver = properties.getProperty("driver");
        sUser = properties.getProperty("User");
        sPassword = properties.getProperty("Password");
        sInitialSize = Integer.parseInt(properties.getProperty("InitialSize"));
        sMaxTotal = Integer.parseInt(properties.getProperty("MaxTotal"));
        sMaxIdle = Integer.parseInt(properties.getProperty("MaxIdle"));
        sMaxWait = Integer.parseInt(properties.getProperty("MaxWait"));
        sRemoveAbandonedTimeout = Integer.parseInt(properties.getProperty("RemoveAbandonedTimeout"));
        sAis_batch_num = Integer.parseInt(properties.getProperty("ais_batch_num"));
        sAutoCommit = properties.getProperty("AutoCommit");
    }
    private void setPool(){
        basicDataSource.setDriverClassName(sDriver);
        basicDataSource.setUrl(sUrl);
        basicDataSource.setUsername(sUser);
        basicDataSource.setPassword(sPassword);
        basicDataSource.setInitialSize(sInitialSize);    // 初始連線數量
        basicDataSource.setMaxTotal(sMaxTotal);          // 最大連線數量
        basicDataSource.setMaxIdle(sMaxIdle);            // 最大空閒連線數量
        basicDataSource.setMaxWaitMillis(sMaxWait);      // 最大等待時間
        basicDataSource.setRemoveAbandonedTimeout(sRemoveAbandonedTimeout);  // 回收時間

        boolean isAutoCommit = "true".equals(sAutoCommit.toLowerCase());
        basicDataSource.setDefaultAutoCommit(isAutoCommit);
    }
}
