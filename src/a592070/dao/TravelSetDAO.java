package a592070.dao;

import a592070.pojo.*;
import controller.ConnectionPool;
import utils.CollectionUtils;
import utils.StringUtil;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TravelSetDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;

    public TravelSetDAO(int connType) throws IOException {
        this.ds = ConnectionPool.getDataSource(connType);
    }

    public List<TravelSetDO> listTravelSet() throws SQLException {
//        List<TravelSetDO> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement predStmt = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            sql = "select * from travel_set order by sn";
//            conn = ds.getConnection();
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                TravelSetDO travelSetDO = new TravelSetDO();
//                travelSetDO.setSn(rs.getInt("sn"));
//                travelSetDO.setCreatedUser(rs.getString("created"));
//                list.add(travelSetDO);
//            }
//
//            for (TravelSetDO travelSetDO : list) {
//                int id = travelSetDO.getSn();
//                travelSetDO.setListTravelAttraction(getAttractionSetByID(id));
//                travelSetDO.setListTravelCar(getCarSetByID(id));
//                travelSetDO.setListTravelHotel(getHotelSetByID(id));
//                travelSetDO.setListTravelRestaurant(getRestaurantSetByID(id));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            if(stmt != null) stmt.close();
//            ConnectionPool.closeResources(conn, predStmt, rs);
//        }
//        return list;
        return listTravelSet(null);
    }
    public List<TravelSetDO> listTravelSet(String created) throws SQLException {
        List<TravelSetDO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement predStmt = null;
        ResultSet rs = null;

        StringBuffer sqlBuffer = new StringBuffer();
        Object[] params = null;
        sqlBuffer.append("select * from travel_set ");
        if(!StringUtil.isEmpty(created)){
            sqlBuffer.append("where created=? and available=1 ");
            params = new Object[]{created};
        }
        sqlBuffer.append("order by priority desc");
        try{
            sql = sqlBuffer.toString();
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, params);
            rs = predStmt.executeQuery();
            while(rs.next()){
                TravelSetDO travelSetDO = new TravelSetDO();
                travelSetDO.setSn(rs.getInt("sn"));
                travelSetDO.setCreatedUser(rs.getString("created"));
                travelSetDO.setDescription(rs.getString("description"));
                travelSetDO.setPriority(rs.getInt("priority"));
                travelSetDO.setCreatedTime(rs.getTimestamp("created_time"));
                travelSetDO.setUpdateTime(rs.getTimestamp("update_time"));
                travelSetDO.setName(rs.getString("name"));

                list.add(travelSetDO);
            }
            for (TravelSetDO travelSetDO : list) {
                int id = travelSetDO.getSn();
                travelSetDO.setListTravelAttraction(getAttractionSetByID(id));
                travelSetDO.setListTravelCar(getCarSetByID(id));
                travelSetDO.setListTravelHotel(getHotelSetByID(id));
                travelSetDO.setListTravelRestaurant(getRestaurantSetByID(id));
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

    public TravelSetDO getTravelSetByID(int id) throws SQLException {
        TravelSetDO travelSetDO = null;
        Connection conn = null;
        PreparedStatement predStmt = null;
        ResultSet rs = null;
        try{
            sql = "select * from travel_set where sn=? and available=1 order by sn";
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt.setInt(1, id);
            rs = predStmt.executeQuery();

            if(rs.next()) {
                travelSetDO = new TravelSetDO();
                travelSetDO.setSn(rs.getInt("sn"));
                travelSetDO.setCreatedUser(rs.getString("created"));
                travelSetDO.setName(rs.getString("name"));
                travelSetDO.setDescription(rs.getString("description"));
                travelSetDO.setPriority(rs.getInt("priority"));
                travelSetDO.setCreatedTime(rs.getTimestamp("created_time"));
                travelSetDO.setUpdateTime(rs.getTimestamp("update_time"));
                travelSetDO.setListTravelAttraction(getAttractionSetByID(id));
                travelSetDO.setListTravelCar(getCarSetByID(id));
                travelSetDO.setListTravelHotel(getHotelSetByID(id));
                travelSetDO.setListTravelRestaurant(getRestaurantSetByID(id));
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return travelSetDO;
    }
    public List<TravelEleAttractionDO> getAttractionSetByID(int id) throws SQLException {
        List<TravelEleAttractionDO> listAttraction = new ArrayList<>();
        Connection conn = null;
        PreparedStatement predStmt = null;
        ResultSet rs = null;
        try {
            sql = "select * " +
                    "from travel_ele_a a, attraction a1 " +
                    "where a.travel_id=? and a.A_ID=a1.A_SN " +
                    "order by a.sn";
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt.setInt(1, id);
            rs = predStmt.executeQuery();

            while (rs.next()) {
                TravelEleAttractionDO travelEleAttractionDO = new TravelEleAttractionDO();
//                AttractionDO attractionDO = new AttractionDO();
//                attractionDO.setSn(rs.getInt("a_id"));
//                attractionDO.setName(rs.getString("name"));
//                attractionDO.setToldescribe(rs.getNString("toldescribe"));
//                attractionDO.setDescription(rs.getNString("description"));
//                attractionDO.setTel(rs.getString("tel"));
//                attractionDO.setAddress(rs.getString("address"));
//                attractionDO.setRegion(rs.getString("region"));
//                attractionDO.setTravellingInfo(rs.getString("travellinginfo"));
//                attractionDO.setOpenTime(rs.getString("opentime"));
//                attractionDO.setPicture(rs.getString("picture"));
//                attractionDO.setPx(rs.getBigDecimal("px"));
//                attractionDO.setPy(rs.getBigDecimal("py"));
//                attractionDO.setTicketInfo(rs.getString("ticketinfo"));
//                attractionDO.setKeywords(rs.getString("keywords"));
//                attractionDO.setRemarks(rs.getString("remarks"));
//                attractionDO.setRating(rs.getBigDecimal("rating"));
//                travelEleAttractionDO.setAttraction(attractionDO);

                AttractionVO attractionVO = new AttractionVO();
                attractionVO.setSn(rs.getInt("a_id"));
                attractionVO.setName(rs.getString("name"));
                attractionVO.setPicture(rs.getString("picture"));
                attractionVO.setTicketInfo(rs.getString("ticketinfo"));
                attractionVO.setAddress(rs.getString("address"));
                attractionVO.setDescription(rs.getString("description"));
                travelEleAttractionDO.setAttraction(attractionVO);


                travelEleAttractionDO.setSn(rs.getInt("sn"));
                travelEleAttractionDO.setTravelId(rs.getInt("travel_id"));
                travelEleAttractionDO.setTime(rs.getTimestamp("time"));

                listAttraction.add(travelEleAttractionDO);
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return listAttraction;
    }
    public List<TravelEleCarDO> getCarSetByID(int id) throws SQLException {
        List<TravelEleCarDO> listCar = new ArrayList<>();
        Connection conn = null;
        PreparedStatement predStmt = null;
        ResultSet rs = null;
        try{
            sql = "select * " +
                    "from travel_ele_c c, cartype c1, carrentalcompany c2 " +
                    "where c.travel_id=? and c.c_id=c1.sn_cartype and c1.sn_rentcarcompany=c2.sn_rentalcompany " +
                    "order by sn";
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt.setInt(1, id);
            rs = predStmt.executeQuery();

            while(rs.next()){
                TravelEleCarDO travelEleCarDO = new TravelEleCarDO();
                CarVO carVO = new CarVO();
                carVO.setSn(rs.getInt("c_id"));
                carVO.setCarType(rs.getString("cartype"));
                carVO.setPrice(rs.getInt("price"));
                carVO.setCompany(rs.getString("name_company"));

                travelEleCarDO.setCar(carVO);
                travelEleCarDO.setSn(rs.getInt("sn"));
                travelEleCarDO.setTravelId(rs.getInt("travel_id"));
                travelEleCarDO.setTime(rs.getTimestamp("time"));

                listCar.add(travelEleCarDO);
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return listCar;
    }
    public List<TravelEleHotelDO> getHotelSetByID(int id) throws SQLException {
        List<TravelEleHotelDO> listHotel = new ArrayList<>();
        Connection conn = null;
        PreparedStatement predStmt = null;
        ResultSet rs = null;
        try{
            sql = "select * " +
                    "from travel_ele_h h, hotel h1 " +
                    "where h.travel_id=? and h.h_id=h1.sn " +
                    "order by h.sn";
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt.setInt(1, id);
            rs = predStmt.executeQuery();

            while(rs.next()){
                TravelEleHotelDO travelEleHotelDO = new TravelEleHotelDO();
                HotelVO hotelVO = new HotelVO();
                hotelVO.setSn(rs.getInt("h_id"));
                hotelVO.setName(rs.getString("name"));
                hotelVO.setAddress(rs.getString("address"));
                hotelVO.setDoubleRoomPrice(rs.getInt("double_room"));
                hotelVO.setQuadrupleRoomPrice(rs.getInt("quadruple_room"));
                hotelVO.setRating(rs.getBigDecimal("rating"));
                hotelVO.setDescription(rs.getString("description"));

                travelEleHotelDO.setHotel(hotelVO);
                travelEleHotelDO.setSn(rs.getInt("SN"));
                travelEleHotelDO.setTravelId(rs.getInt("travel_id"));
                travelEleHotelDO.setTime(rs.getTimestamp("time"));

                listHotel.add(travelEleHotelDO);
            }
        }catch (SQLException e){
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return listHotel;
    }
    public List<TravelEleRestaurantDO> getRestaurantSetByID(int id) throws SQLException {
        List<TravelEleRestaurantDO> listRestaurant = new ArrayList<>();
        Connection conn = null;
        PreparedStatement predStmt = null;
        ResultSet rs = null;
        try {
            sql = "select * " +
                    "from travel_ele_r r, restaurant r1 " +
                    "where r.travel_id=? and r.r_id=r1.r_sn " +
                    "order by r.sn";
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt.setInt(1, id);
            rs = predStmt.executeQuery();

            while (rs.next()) {
                TravelEleRestaurantDO travelEleRestaurantDO = new TravelEleRestaurantDO();
                RestaurantVO restaurantVO = new RestaurantVO();
                restaurantVO.setSn(rs.getInt("r_id"));
                restaurantVO.setName(rs.getString("name"));
                restaurantVO.setAddress(rs.getString("address"));
                restaurantVO.setPicture(rs.getString("picture"));
                restaurantVO.setDescription(rs.getString("description"));
                restaurantVO.setType(rs.getString("type"));
                restaurantVO.setRating(rs.getBigDecimal("rating"));

                travelEleRestaurantDO.setRestaurant(restaurantVO);
                listRestaurant.add(travelEleRestaurantDO);
            }
        } catch (SQLException e) {
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return listRestaurant;
    }


    public boolean addTravelSet(TravelSetDO travelSetDO) throws SQLException {
        boolean flag = false;
        int travelSetPK;
        String[] travelSetPkName = {"sn"};
        String sql0 = "insert into travel_set(created, description, priority, name) values(?, ?, ?,?)";
        try{
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql0, travelSetPkName);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetDO.getCreatedUser(), travelSetDO.getDescription(), travelSetDO.getPriority(), travelSetDO.getName()});
            predStmt.executeUpdate();

            ResultSet generatedKeys = predStmt.getGeneratedKeys();
            if(generatedKeys.next()){
                travelSetPK = generatedKeys.getInt(1);
                // prevent new travelSetDO default sn=0
                travelSetDO.setSn(travelSetPK);
            }else{
                conn.rollback();
                throw new RuntimeException("無法取得新增之TravelSet表格的主鍵");
            }
            addTravelEleA(travelSetDO);
            addTravelEleC(travelSetDO);
            addTravelEleH(travelSetDO);
            addTravelEleR(travelSetDO);
//            for (TravelEleAttractionDO ele : travelSetDO.getListTravelAttraction()) {
//                sql0 = "insert into travel_ele_a(travel_id, a_id, time) values (?, ?, ?)";
//                predStmt = conn.prepareStatement(sql0);
//                predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetPK, ele.getAttraction().getSn(), ele.getTime()});
//                predStmt.executeUpdate();
//            }
//            for (TravelEleCarDO ele : travelSetDO.getListTravelCar()) {
//                sql0 = "insert into travel_ele_c(travel_id, c_id, time) values (?, ?, ?)";
//                predStmt = conn.prepareStatement(sql0);
//                predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetPK, ele.getCar().getSn(), ele.getTime()});
//                predStmt.executeUpdate();
//            }
//            for (TravelEleHotelDO ele : travelSetDO.getListTravelHotel()) {
//                sql0 = "insert into travel_ele_h(travel_id, h_id, time) values (?, ?, ?)";
//                predStmt = conn.prepareStatement(sql0);
//                predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetPK, ele.getHotel().getSn(), ele.getTime()});
//                predStmt.executeUpdate();
//            }
//            for (TravelEleRestaurantDO ele : travelSetDO.getListTravelRestaurant()) {
//                sql0 = "insert into travel_ele_r(travel_id, r_id, time) values (?, ?, ?)";
//                predStmt = conn.prepareStatement(sql0);
//                predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetPK, ele.getRestaurant().getSn(), ele.getTime()});
//                predStmt.executeUpdate();
//            }

            conn.commit();
            flag = true;
        } catch (SQLException e) {
            if(conn != null) conn.rollback();
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return flag;
    }

    private void addTravelEleA(TravelSetDO travelSetDO) throws SQLException {
        try {
            for (TravelEleAttractionDO ele : travelSetDO.getListTravelAttraction()) {
                String sql0 = "insert into travel_ele_a(travel_id, a_id, time) values (?, ?, ?)";
                predStmt = conn.prepareStatement(sql0);
                predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetDO.getSn(), ele.getAttraction().getSn(), ele.getTime()});
                predStmt.executeUpdate();
                predStmt.clearParameters();
            }
        }catch (SQLException e){
            if(conn != null) conn.rollback();
            throw e;
        }
    }
    private void addTravelEleC(TravelSetDO travelSetDO) throws SQLException {
        try {
            for (TravelEleCarDO ele : travelSetDO.getListTravelCar()) {
                String sql0 = "insert into travel_ele_c(travel_id, c_id, time) values (?, ?, ?)";
                predStmt = conn.prepareStatement(sql0);
                predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetDO.getSn(), ele.getCar().getSn(), ele.getTime()});
                predStmt.executeUpdate();
                predStmt.clearParameters();
            }
        }catch (SQLException e){
            if(conn != null) conn.rollback();
            throw e;
        }
    }
    private void addTravelEleH(TravelSetDO travelSetDO) throws SQLException {
        try {
            for (TravelEleHotelDO ele : travelSetDO.getListTravelHotel()) {
                String sql0 = "insert into travel_ele_h(travel_id, h_id, time) values (?, ?, ?)";
                predStmt = conn.prepareStatement(sql0);
                predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetDO.getSn(), ele.getHotel().getSn(), ele.getTime()});
                predStmt.executeUpdate();
                predStmt.clearParameters();
            }
        }catch (SQLException e){
            if(conn != null) conn.rollback();
            throw e;
        }
    }
    private void addTravelEleR(TravelSetDO travelSetDO) throws SQLException {
        try {
            for (TravelEleRestaurantDO ele : travelSetDO.getListTravelRestaurant()) {
                String sql0 = "insert into travel_ele_r(travel_id, r_id, time) values (?, ?, ?)";
                predStmt = conn.prepareStatement(sql0);
                predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetDO.getSn(), ele.getRestaurant().getSn(), ele.getTime()});
                predStmt.executeUpdate();
                predStmt.clearParameters();
            }
        }catch (SQLException e){
            if(conn != null) conn.rollback();
            throw e;
        }
    }


    public boolean updateTravelSet(TravelSetDO travelSetDO) throws SQLException {
        boolean flag = false;

        String sql0 = "update travel_set set description=?, priority=?, update_time=?, name=? where SN=?";
        try{
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql0);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetDO.getDescription(), travelSetDO.getPriority(), new Timestamp(System.currentTimeMillis()), travelSetDO.getName(), travelSetDO.getSn()});
            predStmt.executeUpdate();
            predStmt.clearParameters();

            sql0 = "delete travel_ele_a where travel_id=?";
            predStmt = conn.prepareStatement(sql0);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetDO.getSn()});
            predStmt.executeUpdate();
            predStmt.clearParameters();
            addTravelEleA(travelSetDO);

            sql0 = "delete travel_ele_c where travel_id=?";
            predStmt = conn.prepareStatement(sql0);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetDO.getSn()});
            predStmt.executeUpdate();
            predStmt.clearParameters();
            addTravelEleC(travelSetDO);

            sql0 = "delete travel_ele_h where travel_id=?";
            predStmt = conn.prepareStatement(sql0);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetDO.getSn()});
            predStmt.executeUpdate();
            predStmt.clearParameters();
            addTravelEleH(travelSetDO);

            sql0 = "delete travel_ele_r where travel_id=?";
            predStmt = conn.prepareStatement(sql0);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{travelSetDO.getSn()});
            predStmt.executeUpdate();
            predStmt.clearParameters();
            addTravelEleR(travelSetDO);

            conn.commit();
            flag = true;
        } catch (SQLException e) {
            if(conn != null) conn.rollback();
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return flag;
    }

    public boolean setTravelSetUnavailable(int sn) throws SQLException {
        boolean flag = false;
        sql = "update travel_set set available=0 where SN=?";
        try {
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt = ConnectionPool.setParams(predStmt, new Object[]{sn});
            predStmt.executeUpdate();

            flag = true;
            conn.commit();
        } catch (SQLException e) {
            if(conn!=null) conn.rollback();
            throw e;
        }finally {
            ConnectionPool.closeResources(conn, predStmt, null);
        }
        return flag;
    }

}
