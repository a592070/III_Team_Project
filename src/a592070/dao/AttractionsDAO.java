package a592070.dao;

import a592070.vo.AttractionsInfoVO;
import controller.ConnectionPool;
import pojo.AttractionsDO;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class AttractionsDAO {
    private static List<AttractionsDO> list;
    private static List<AttractionsInfoVO> listVO;
    private static Map<String, List<String>> regionMap;

    private static final int Attraction_Scenic_Spot = 1;
    private static final int Attraction_Restaurant = 3;
    private static final int Attraction_Hotel = 4;
    private static final String sScenic_Spot = "景點";
    private static final String sRestaurant = "餐廳";
    private static final String sHotel = "旅館";

    private DataSource dataSource;
    private Connection conn;
    private String sql;
    private Statement stmt;
    private PreparedStatement predStmt;

    public AttractionsDAO(int dataSourceType) throws IOException, SQLException {
        dataSource = ConnectionPool.getDataSource(dataSourceType);
        if(list == null || list.size()==0){
            listInit();
        }
        if(regionMap == null || regionMap.size()==0){
            mapRegionInit();
        }
    }
    public void listInit() throws SQLException {
        list = new ArrayList<>();
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            sql = "select * from attractions order by rating desc";
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()){
                AttractionsDO attractionsDO = new AttractionsDO();

                attractionsDO.setId(resultSet.getString("id"));
                attractionsDO.setName(resultSet.getString("name"));
                attractionsDO.setDescription(resultSet.getNString("description"));
                attractionsDO.setAddress(resultSet.getString("address"));
                attractionsDO.setTel(resultSet.getString("tel"));
                attractionsDO.setPx(resultSet.getBigDecimal("px"));
                attractionsDO.setPy(resultSet.getBigDecimal("py"));
                attractionsDO.setOpenTime(resultSet.getString("opentime"));
                attractionsDO.setTravelingInfo(resultSet.getString("traveling_info"));
                attractionsDO.setTotalNumberRooms(resultSet.getString("total_number_rooms"));
                attractionsDO.setServiceInfo(resultSet.getString("service_info"));
                attractionsDO.setRating(resultSet.getBigDecimal("rating"));
                attractionsDO.setRegion(resultSet.getString("region_name"));
                attractionsDO.setType(resultSet.getBigDecimal("type"));

                list.add(attractionsDO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
        }
    }
    private void mapRegionInit() throws SQLException {
        regionMap = new HashMap<>();
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            sql = "select * from region order by area";
            ResultSet resultSet = stmt.executeQuery(sql);
            String currentArea = null;
            List<String> tempList = new ArrayList<>();
            while(resultSet.next()){
                String area = resultSet.getString("area");
                if(!Objects.equals(area, currentArea)) tempList = new ArrayList<>();
                tempList.add(resultSet.getString("region_name"));
                regionMap.put(area, tempList);
                currentArea = area;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
        }
    }

    public List<AttractionsDO> listAttractionsDO(){
        return list;
    }
    public Map<String, List<String>> mapRegion(){
        return regionMap;
    }
    public List<AttractionsInfoVO> listInfoVO(){
        listVO = new ArrayList<>();
        if(list != null || list.size() != 0){
            for (AttractionsDO ele : list) {
                AttractionsInfoVO attractionsInfoVO = new AttractionsInfoVO();
                attractionsInfoVO.setName(ele.getName());
                attractionsInfoVO.setAddress(ele.getAddress());
                attractionsInfoVO.setOpentime(ele.getOpenTime());
                attractionsInfoVO.setRating(ele.getRating());

                int type = ele.getType().intValue();
                switch (type){
                    case Attraction_Scenic_Spot:
                        attractionsInfoVO.setType(sScenic_Spot);
                        break;
                    case Attraction_Restaurant:
                        attractionsInfoVO.setType(sRestaurant);
                        break;
                    case Attraction_Hotel:
                        attractionsInfoVO.setType(sHotel);
                        break;
                }

                String region = ele.getRegion();
                attractionsInfoVO.setRegion(region);
                if(regionMap != null || regionMap.size() != 0){
                    regionMap.forEach((key,value) -> {
                        if(value.contains(region)) attractionsInfoVO.setArea(key);
                    });
                }

                listVO.add(attractionsInfoVO);
            }
        }
        return listVO;
    }
}
