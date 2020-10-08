package a592070.dao;

import a592070.pojo.*;
import controller.ConnectionPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
        List<TravelSetDO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement predStmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            sql = "select * from travel_set order by sn";
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                TravelSetDO travelSetDO = new TravelSetDO();
                travelSetDO.setSn(rs.getInt("sn"));
                travelSetDO.setCreatedUser(rs.getString("created"));
                list.add(travelSetDO);
            }

            for (TravelSetDO travelSetDO : list) {
                int id = travelSetDO.getSn();
                travelSetDO.setListTravelAttraction(getAttractionSetByID(id));
                travelSetDO.setListTravelCar(getCarSetByID(id));
                travelSetDO.setListTravelHotel(getHotelSetByID(id));
                travelSetDO.setListTravelRestaurant(getRestaurantSetByID(id));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(stmt != null) stmt.close();
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return list;
    }

    public TravelSetDO getTravelSetByID(int id){
        TravelSetDO travelSetDO = new TravelSetDO();
        Connection conn = null;
        PreparedStatement predStmt = null;
        ResultSet rs = null;
        try{
            sql = "select * from travel_set where sn=? order by sn";
            conn = ds.getConnection();
            predStmt = conn.prepareStatement(sql);
            predStmt.setInt(1, id);
            rs = predStmt.executeQuery();

            if(rs.next()) {
                travelSetDO.setSn(rs.getInt("sn"));
                travelSetDO.setCreatedUser(rs.getString("created"));
                travelSetDO.setListTravelAttraction(getAttractionSetByID(id));
                travelSetDO.setListTravelCar(getCarSetByID(id));
                travelSetDO.setListTravelHotel(getHotelSetByID(id));
                travelSetDO.setListTravelRestaurant(getRestaurantSetByID(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return travelSetDO;
    }
    public List<TravelEleAttractionDO> getAttractionSetByID(int id){
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
                AttractionDO attractionDO = new AttractionDO();
                attractionDO.setId(rs.getInt("a_id"));
                attractionDO.setName(rs.getString("name"));
                attractionDO.setToldescribe(rs.getNString("toldescribe"));
                attractionDO.setDescription(rs.getNString("description"));
                attractionDO.setTel(rs.getString("tel"));
                attractionDO.setAddress(rs.getString("address"));
                attractionDO.setRegion(rs.getString("region"));
                attractionDO.setTravellingInfo(rs.getString("travellinginfo"));
                attractionDO.setOpenTime(rs.getString("opentime"));
                attractionDO.setPicture(rs.getString("picture"));
                attractionDO.setPx(rs.getBigDecimal("px"));
                attractionDO.setPy(rs.getBigDecimal("py"));
                attractionDO.setTicketInfo(rs.getString("ticketinfo"));
                attractionDO.setKeywords(rs.getString("keywords"));
                attractionDO.setRemarks(rs.getString("remarks"));
                attractionDO.setRating(rs.getBigDecimal("rating"));
                travelEleAttractionDO.setAttraction(attractionDO);

                travelEleAttractionDO.setSn(rs.getInt("sn"));
                travelEleAttractionDO.setTravelId(rs.getInt("travel_id"));
                travelEleAttractionDO.setTime(rs.getTimestamp("time"));

                listAttraction.add(travelEleAttractionDO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return listAttraction;
    }
    public List<TravelEleCarDO> getCarSetByID(int id){
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
            e.printStackTrace();
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return listCar;
    }
    public List<TravelEleHotelDO> getHotelSetByID(int id){
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

                travelEleHotelDO.setHotel(hotelVO);
                travelEleHotelDO.setSn(rs.getInt("SN"));
                travelEleHotelDO.setTravelId(rs.getInt("travel_id"));
                travelEleHotelDO.setTime(rs.getTimestamp("time"));

                listHotel.add(travelEleHotelDO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return listHotel;
    }
    public List<TravelEleRestaurantDO> getRestaurantSetByID(int id){
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
            e.printStackTrace();
        }finally {
            ConnectionPool.closeResources(conn, predStmt, rs);
        }
        return listRestaurant;
    }
}
