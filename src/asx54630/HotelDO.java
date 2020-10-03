package asx54630;

import java.math.BigDecimal;

public class HotelDO{


    private String HOTEL_ID;
    private String NAME;
    private String REGION;              
    private String ADDRESS;
    private String TEL;
    private String ROOMTYPE;         
    private String DESCRIPTION;
    private String OPENTIME;			
    private String TYPE;                  
    private BigDecimal RATING;

    public HotelDO(String id, String name, String region, String address, String tel, String description, String openTime, String type, BigDecimal rating) {
        this.HOTEL_ID = id;
        this.NAME = name;
        this.REGION = region;
        this.ADDRESS = address;
        this.TEL = tel;
        this.DESCRIPTION = description;
        this.OPENTIME = openTime;
        this.TYPE = type;
        this.RATING = rating;
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


	public String getROOMTYPE() {
		return ROOMTYPE;
	}


	public void setROOMTYPE(String rOOMTYPE) {
		ROOMTYPE = rOOMTYPE;
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
                ", description='" + DESCRIPTION + '\'' +
                ", address='" + ADDRESS + '\'' +
                ", region='" + REGION + '\'' +
                ", tel='" + TEL + '\'' +
                ", openTime='" + OPENTIME + '\'' +
                ", type=" + TYPE +
                '}';
    }
}
