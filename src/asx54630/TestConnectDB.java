package asx54630;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import controller.ConnectionPool;

public class TestConnectDB {
	public static void main(String[] args) throws IOException, SQLException {

	
		HotelDO hotDO = new HotelDO();
		
		hotDO.setNAME("AB");
		hotDO.setREGION("臺中市"); //Hotel Table中的REGION指向
		hotDO.setADDRESS("test");
		hotDO.setTEL("test");
		hotDO.setDOUBLE_ROOM(BigDecimal.valueOf(3500));
		hotDO.setQUADRUPLE_ROOM(BigDecimal.valueOf(3500));
		hotDO.setDESCRIPTION("test");        
		hotDO.setOPENTIME("test");                                
		hotDO.setTYPE("test");                                
		hotDO.setRATING(BigDecimal.ZERO);
		hotDO.setACCOUNT("Brian");
        
        HotelDAO hotelDAO = new HotelDAO(ConnectionPool.LOADING_WITHOUT_SERVER);                        
        hotelDAO.createHotel(hotDO);
	}
}
