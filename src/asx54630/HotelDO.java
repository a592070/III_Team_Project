package asx54630;

import java.math.BigDecimal;

public class HotelDO{


    private String HOTEL_ID;
    private String NAME;
	private String REGION;              
    private String ADDRESS;
    private String TEL;
    private String DOUBLE_ROOM;        
    private String QUADRUPLE_ROOM;        
    private String DESCRIPTION;
    private String OPENTIME;			
    private String TYPE;                  
    private BigDecimal RATING;

    public HotelDO(String id, String name, String region, String address, String tel, String dbroom, String quadroom, String description, String openTime, String type, BigDecimal rating) {
        this.HOTEL_ID = id;
        this.NAME = name;
        this.REGION = region;
        this.ADDRESS = address;
        this.TEL = tel;
        this.DOUBLE_ROOM = dbroom;
        this.QUADRUPLE_ROOM = quadroom;
        this.DESCRIPTION = description;
        this.OPENTIME = openTime;
        this.TYPE = type;
        this.RATING = rating;
    }

   
    public HotelDO() {
		// TODO Auto-generated constructor stub
	}


	public String getHOTEL_ID() {
		return HOTEL_ID;
	}





	public void setHOTEL_ID(String hOTEL_ID) {
		HOTEL_ID = hOTEL_ID;
	}





	public String getNAME() {
		return NAME;
	}





	public void setNAME(String nAME) {
		NAME = nAME;
	}





	public String getREGION() {
		return REGION;
	}





	public void setREGION(String rEGION) {
		REGION = rEGION;
	}





	public String getADDRESS() {
		return ADDRESS;
	}





	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}





	public String getTEL() {
		return TEL;
	}





	public void setTEL(String tEL) {
		TEL = tEL;
	}





	public String getDOUBLE_ROOM() {
		return DOUBLE_ROOM;
	}





	public void setDOUBLE_ROOM(String dOUBLE_ROOM) {
		DOUBLE_ROOM = dOUBLE_ROOM;
	}





	public String getQUADRUPLE_ROOM() {
		return QUADRUPLE_ROOM;
	}





	public void setQUADRUPLE_ROOM(String qUADRUPLE_ROOM) {
		QUADRUPLE_ROOM = qUADRUPLE_ROOM;
	}





	public String getDESCRIPTION() {
		return DESCRIPTION;
	}





	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}





	public String getOPENTIME() {
		return OPENTIME;
	}





	public void setOPENTIME(String oPENTIME) {
		OPENTIME = oPENTIME;
	}





	public String getTYPE() {
		return TYPE;
	}





	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}





	public BigDecimal getRATING() {
		return RATING;
	}





	public void setRATING(BigDecimal rATING) {
		RATING = rATING;
	}



	@Override
    public String toString() {
        return "{" +
                "id='" + HOTEL_ID + '\'' +
                ", name='" + NAME + '\'' +
                ", region='" + REGION + '\'' +
                ", address='" + ADDRESS + '\'' +
                ", tel='" + TEL + '\'' +
                ", dbroom='" + DOUBLE_ROOM + '\'' +
                ", quadroom='" + QUADRUPLE_ROOM + '\'' +
                ", description='" + DESCRIPTION + '\'' +
                ", openTime='" + OPENTIME + '\'' +
                ", type=" + TYPE + '\'' +
                ", rating=" + RATING + 
                '}';
    }
}
