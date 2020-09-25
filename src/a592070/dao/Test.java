package a592070.dao;

import controller.ConnectionPool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException, SQLException {
        AttractionsDAO attractionsDAO = new AttractionsDAO(ConnectionPool.LOADING_WITHOUT_SERVER);

        System.out.println();
    }
}
