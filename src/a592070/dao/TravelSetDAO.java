package a592070.dao;

import controller.ConnectionPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TravelSetDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;

    public TravelSetDAO() throws IOException {
        this.ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
    }

    // TODO
//    public void getTravelSetById(String id){
//        String sql = "select t.id as id, t.traffic_go_id as trafficId_1, t.traffic_back_id as trafficId_2, t.traffic_rent_id as trafficId_3, t.restaurant_id as restaurant_id, e2.booking_id as restaurant_booking_id,  " +
//                "from travel_set_1day t, traffic_set e1, restaurant_set e2, attractions_set e3, hotel_set e4 " +
//                "where (t.traffic_go_id=e1.id or t.traffic_back_id=e1.id or t.traffic_rent_id=e1.id)" +
//                "and t.restaurant_id=e2.id and t.attractions_id=e3.id and hotel_id=e4.id";
//        Object[] params = {id};
//        try {
//            conn = ds.getConnection();
//            ConnectionPool.execute(conn, predStmt, rs, sql, params);
//
//        } catch (SQLException | IOException throwables) {
//            throwables.printStackTrace();
//        }finally {
//            ConnectionPool.closeResources(conn, predStmt, rs);
//        }
//    }

}
