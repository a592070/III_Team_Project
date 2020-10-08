package a592070.initTable;

import a592070.pojo.AttractionDO;
import controller.ConnectionPool;

import javax.sql.DataSource;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AttractionTableInit {
    public static final String Scenic_Spot_URL = "https://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json";
    public static final String Restaurant_URL = "https://gis.taiwan.net.tw/XMLReleaseALL_public/restaurant_C_f.json";
    public static final String Hotel_URL = "https://gis.taiwan.net.tw/XMLReleaseALL_public/hotel_C_f.json";
    private String sourceURL;
    private String sql;
    private Statement stmt;
    private PreparedStatement predStmt;
    private List<AttractionDO> listDO;
    private AttractionDO attractionsDO;
    private DataSource dataSource;
    private Connection conn;

    public AttractionTableInit(String sourceURLType) throws IOException {
        dataSource = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
        this.sourceURL = sourceURLType;
        listDO = new DataInit(sourceURL).getListDO();
    }
    public boolean initTable() throws IOException, SQLException {
        boolean isSuccess = false;
        try{
            conn = dataSource.getConnection();

            sql = "insert into ATTRACTION(ID, NAME, TOLDESCRIBE, DESCRIPTION, TEL, ADDRESS, REGION, TRAVELLINGINFO, OPENTIME, PICTURE, PX, PY, TICKETINFO, KEYWORDS, REMARKS, RATING) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            predStmt = conn.prepareStatement(sql);

            for (AttractionDO attDO : listDO) {
                predStmt.setInt(1, attDO.getSn());
                predStmt.setString(2, attDO.getName());
                predStmt.setString(3, attDO.getToldescribe());
                predStmt.setString(4, attDO.getDescription());
                predStmt.setString(5, attDO.getTel());
                predStmt.setString(6, attDO.getAddress());
                predStmt.setString(7, attDO.getRegion());
                predStmt.setString(8, attDO.getTravellingInfo());
                predStmt.setString(9, attDO.getOpenTime());
                predStmt.setString(10, attDO.getPicture());

                predStmt.setBigDecimal(11, attDO.getPx());
                predStmt.setBigDecimal(12, attDO.getPy());
                predStmt.setString(13, attDO.getTicketInfo());
                predStmt.setString(14, attDO.getKeywords());
                predStmt.setString(15, attDO.getRemarks());
                predStmt.setBigDecimal(16, rndRation());

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
            ConnectionPool.closeResources(conn, predStmt, null);
        }
        return isSuccess;
    }
    public boolean updateRating() throws SQLException {
        boolean isSuccess = false;
        try{
            conn = dataSource.getConnection();

            sql = "update ATTRACTIONS set RATING = ? where ID=?";
            predStmt = conn.prepareStatement(sql);

            for (AttractionDO attDO : listDO) {
                predStmt.setBigDecimal(1, rndRation());
                predStmt.setInt(2, attDO.getSn());

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
        double rnd = ThreadLocalRandom.current().nextDouble(0,5.1);
        if(rnd >= 5.0) rnd = 5.0;
        return new BigDecimal(rnd).setScale(1, BigDecimal.ROUND_HALF_UP);
    }
}
