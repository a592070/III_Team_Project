package asx54630;

import java.math.BigDecimal;

public class HotelDO{


    private BigDecimal SN;
    private String NAME;
	private String REGION;              
    private String ADDRESS;
    private String TEL;
    private BigDecimal DOUBLE_ROOM;        
    private BigDecimal QUADRUPLE_ROOM;        
    private String DESCRIPTION;
    private String OPENTIME;			
    private String TYPE;                  
    private BigDecimal RATING;
    private String ACCOUNT;
    

    public HotelDO(BigDecimal id, String name, String region, String address, String tel, BigDecimal dbroom, BigDecimal quadroom, String description, String openTime, String type, BigDecimal rating, String account) {
        this.SN = id;
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
        this.ACCOUNT = account;
    }

   
    



	public HotelDO() {
		
	}






	public BigDecimal getSN() {
		return SN;
	}






	public void setSN(BigDecimal sN) {
		SN = sN;
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






	public BigDecimal getDOUBLE_ROOM() {
		return DOUBLE_ROOM;
	}






	public void setDOUBLE_ROOM(BigDecimal dOUBLE_ROOM) {
		DOUBLE_ROOM = dOUBLE_ROOM;
	}






	public BigDecimal getQUADRUPLE_ROOM() {
		return QUADRUPLE_ROOM;
	}






	public void setQUADRUPLE_ROOM(BigDecimal qUADRUPLE_ROOM) {
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






	public String getACCOUNT() {
		return ACCOUNT;
	}






	public void setACCOUNT(String aCCOUNT) {
		ACCOUNT = aCCOUNT;
	}






	@Override
    public String toString() {
        return "{" +
                "id='" + SN + '\'' +
                ", name='" + NAME + '\'' +
                ", region='" + REGION + '\'' +
                ", address='" + ADDRESS + '\'' +
                ", tel='" + TEL + '\'' +
                ", dbroom='" + DOUBLE_ROOM + '\'' +
                ", quadroom='" + QUADRUPLE_ROOM + '\'' +
                ", description='" + DESCRIPTION + '\'' +
                ", openTime='" + OPENTIME + '\'' +
                ", type=" + TYPE + '\'' +
                ", rating=" + RATING + '\'' +
                ", account=" + ACCOUNT + 
                '}';
    }
}
