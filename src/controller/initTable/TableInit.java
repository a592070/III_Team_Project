package controller.initTable;


import controller.ConnectionPool;
import pojo.AttractionsDO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;


public abstract class TableInit {
    protected AttractionsDO attractionsDO;
    protected DataSource dataSource;
    protected Connection conn;

    public TableInit() throws IOException {
        dataSource = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
    }

}
