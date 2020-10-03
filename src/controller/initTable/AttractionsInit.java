
package controller.initTable;

import pojo.AttractionsDO;
import utils.StringUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AttractionsInit extends TableInit{
    public static final String Scenic_Spot_URL = "https://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json";
    public static final String Restaurant_URL = "https://gis.taiwan.net.tw/XMLReleaseALL_public/restaurant_C_f.json";
    public static final String Hotel_URL = "https://gis.taiwan.net.tw/XMLReleaseALL_public/hotel_C_f.json";
    private String sourceURL;
    private String sql;
    private Statement stmt;
    private PreparedStatement predStmt;
    private List<AttractionsDO> listDO;

    public AttractionsInit(String sourceURLType) throws IOException {
        super();
        this.sourceURL = sourceURLType;
        listDO = new DataInit(sourceURL).getListDO();
    }

    private Map<String, String> getRegionMap() throws SQLException {
        Map<String, String> regionMap = new HashMap<String, String>();
        try{
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            sql = "select region_id, region_name from region";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()){
                regionMap.put(resultSet.getString("region_name"), resultSet.getString("region_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally {
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
        }
        return regionMap;
    }

    public boolean initTable() throws IOException, SQLException {
        boolean isSuccess = false;
        try{
            conn = dataSource.getConnection();

            sql = "insert into ATTRACTIONS(ID, NAME, DESCRIPTION, ADDRESS, TEL, PX, PY, OPENTIME, TRAVELING_INFO, TOTAL_NUMBER_ROOMS, SERVICE_INFO, REGION_NAME, TYPE) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            predStmt = conn.prepareStatement(sql);

            for (AttractionsDO attDO : listDO) {
                predStmt.setString(1, attDO.getId());
                predStmt.setString(2, attDO.getName());

                String temp = attDO.getDescription();
                predStmt.setString(3, String.valueOf(temp));

                temp = attDO.getAddress();
                predStmt.setString(4, String.valueOf(temp));

                temp = attDO.getTel();
                predStmt.setString(5, String.valueOf(temp));

                BigDecimal decimal = attDO.getPx();
                if(decimal != null) predStmt.setBigDecimal(6, decimal);

                decimal = attDO.getPx();
                if(decimal != null) predStmt.setBigDecimal(7, decimal);

                temp = attDO.getOpenTime();
                predStmt.setString(8, String.valueOf(temp));

                temp = attDO.getTravelingInfo();
                predStmt.setString(9, String.valueOf(temp));

                temp = attDO.getTotalNumberRooms();
                predStmt.setString(10, String.valueOf(temp));

                temp = attDO.getServiceInfo();
                predStmt.setString(11, String.valueOf(temp));

                temp = attDO.getRegion();
                predStmt.setString(12, String.valueOf(temp));

                decimal = attDO.getType();
                if(decimal != null) predStmt.setBigDecimal(13, decimal);

                predStmt.addBatch();
                predStmt.clearParameters();
            }
            int[] ints = predStmt.executeBatch();
            if(ints!=null && ints.length!=0) isSuccess = true;
            conn.commit();
        }catch (SQLException e){
            if(conn != null) conn.rollback();
            e.printStackTrace();
        }finally {
            if(predStmt != null) {
                predStmt.clearBatch();
                predStmt.close();
            }
            if(conn != null) conn.close();
        }
        return isSuccess;
    }
    public boolean updateRating() throws SQLException {
        boolean isSuccess = false;
        try{
            conn = dataSource.getConnection();

            sql = "update ATTRACTIONS set RATING = ? where ID=?";
            predStmt = conn.prepareStatement(sql);

            for (AttractionsDO attDO : listDO) {
                predStmt.setBigDecimal(1, rndRation());
                predStmt.setString(2, attDO.getId());

                predStmt.addBatch();
                predStmt.clearParameters();
            }
            int[] ints = predStmt.executeBatch();
            if(ints!=null && ints.length!=0) isSuccess = true;
            conn.commit();
        }catch (SQLException e){
            if(conn != null) conn.rollback();
            e.printStackTrace();
        }finally {
            if(predStmt != null) {
                predStmt.clearBatch();
                predStmt.close();
            }
            if(conn != null) conn.close();
        }
        return isSuccess;
    }
    private BigDecimal rndRation(){
        double rnd = ThreadLocalRandom.current().nextDouble(1,5.1);
        if(rnd >= 5.0) rnd = 5.0;
        return new BigDecimal(rnd).setScale(1, BigDecimal.ROUND_HALF_UP);
    }
}