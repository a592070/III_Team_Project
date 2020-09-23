package controller;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.DataInput;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPool {
    private static DataSource dataSource;
    public static final int LOADING_WITH_SERVER = 1;
    public static final int LOADING_WITHOUT_SERVER = 2;

    private static BasicDataSource basicDataSource;
    private String sConnInfo;
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
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("ConnectionPool");
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

    private void readProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("resources/db.properties"));
        sConnInfo = properties.getProperty("ConnInfo");
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
//        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        basicDataSource.setUrl(sConnInfo);
        basicDataSource.setUsername(sUser);
        basicDataSource.setPassword(sPassword);
        basicDataSource.setInitialSize(sInitialSize);    // 初始連線數量
        basicDataSource.setMaxTotal(sMaxTotal);          // 最大連線數量
        basicDataSource.setMaxIdle(sMaxIdle);            // 最大空閒連線數量
        basicDataSource.setMaxWaitMillis(sMaxWait);      // 最大等待時間
        basicDataSource.setRemoveAbandonedTimeout(sRemoveAbandonedTimeout);  // 回收時間
        basicDataSource.setDefaultAutoCommit(false);
    }
}
