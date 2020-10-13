package asx54630;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import controller.ConnectionPool;

public class H_HomePageDAO {
	
	private DataSource ds;
    private static Connection conn;
    private String sql;
    private static PreparedStatement predStmt;
    private ResultSet rs;

	public H_HomePageDAO(int connType) throws IOException {
        ds = ConnectionPool.getDataSource(connType);
    }

	
	public HotelDO HomepageUpdate(String hpname,String hpaddress,String hptel,int hpdroom,int hpqroom,String hpdescription,String hpopentime,String hptype) throws SQLException {
        try {
        	
        	HotelDO hoteldo = null;
          conn = ds.getConnection();
    	  ResultSet rs = predStmt.executeQuery();
    	  
    	  if (hpname !=null) {
    		  predStmt = conn.prepareStatement("update HOTEL set NAME=? where account=?");
    		  
    		  predStmt.setString(1, hpname);
    		  
    	  }else if(hpaddress !=null) {
    		  predStmt = conn.prepareStatement("update HOTEL set ADDRESS=? where account=?");
    		  
    		  predStmt.setString(1, hpname);
    		  
    	  }else if(hptel !=null) {
    		  predStmt = conn.prepareStatement("update HOTEL set TEL=? where account=?");
    		  
    		  predStmt.setString(1, hpname);
    		  
    	  }else if(hpdroom !=0) {
    		  predStmt = conn.prepareStatement("update HOTEL set DOUBLE_ROOM=? where account=?");
    		  
    		  predStmt.setString(1, hpname);
    		  
    	  }else if(hpqroom !=0) {
    		  predStmt = conn.prepareStatement("update HOTEL set QUADRUPLE_ROOM=? where account=?");
    		  
    		  predStmt.setString(1, hpname);
    		  
    	  }else if(hpdescription !=null) {
    		  predStmt = conn.prepareStatement("update HOTEL set DESCRIPTION=? where account=?");
    		  
    		  predStmt.setString(1, hpname);
    		  
    	  }else if(hpopentime !=null) {
    		  predStmt = conn.prepareStatement("update HOTEL set OPENTIME=? where account=?");
    		  
    		  predStmt.setString(1, hpname);
    		  
    	  }else if(hptype !=null) {
    		  predStmt = conn.prepareStatement("update HOTEL set TYPE=? where account=?");
    		  
    		  predStmt.setString(1, hpname);
    		  
    	  }
    	  rs.close();
    	  predStmt.close();
    	  return hoteldo;
    	  
        } catch (Exception e) {
    	    System.err.println("修改住宿資料時發生錯誤:" + e);
    	    return null;
        }finally {
			if (conn != null) {
				conn.close();
			}
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
