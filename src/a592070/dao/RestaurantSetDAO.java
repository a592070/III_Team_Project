package a592070.dao;

import a592070.pojo.RestaurantDO;
import controller.ConnectionPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantSetDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;

    public RestaurantSetDAO() throws IOException {
        ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
    }

    public List<RestaurantDO> listRestaurantSet(String... limit) throws IOException, SQLException {
        sql = "select  from restaurant_set r1, restaurant r2 where restaurant_set.booking_id=restaurant.r_id";
        for (int i = 0; i < limit.length; i++) {
            sql += limit[i];
        }
        sql +=  "order by restaurant_set.priority desc";
        List<RestaurantDO> list = new ArrayList<>();
        try {
            conn = ds.getConnection();
            rs = ConnectionPool.execute(conn, predStmt, rs, sql, new Object[]{});
            while(rs.next()){
                RestaurantDO restaurantDO = new RestaurantDO();
                //String rID; String name; String address; String opentime; String description; String transportation; String type;
                //BigDecimal rating; String region; String tel; String picture; String serviceinfo; String booking_id;
                restaurantDO.setRId(rs.getString("R_ID"));
                restaurantDO.setName(rs.getString("name"));
                restaurantDO.setAddress(rs.getString("address"));
                restaurantDO.setOpentime(rs.getString("opentime"));
                restaurantDO.setTransportation(rs.getString("transportation"));
                restaurantDO.setType(rs.getString("type"));
                restaurantDO.setRating(rs.getBigDecimal("rating"));
                restaurantDO.setRegion(rs.getString("region"));
                restaurantDO.setTel(rs.getString("tel"));
                restaurantDO.setPicture(rs.getString("picture"));
                restaurantDO.setServiceinfo(rs.getString("serviceinfo"));

                list.add(restaurantDO);
            }
        } catch (SQLException | IOException e) {
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

    public List<RestaurantDO> listRestaurantSetByRegion(String sRegion) throws IOException, SQLException {
        return listRestaurantSet(sRegion);
    }

}
