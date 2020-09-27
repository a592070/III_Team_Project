package iring29;

import java.math.BigDecimal;
import java.sql.Date;

public class HPAttractionInfoVO {

	private String username;
    private String password;
    private String email;
    private byte[] picture;
    private String nickname;
    private Date modifyDate;
    private Date register;
    private String favorite;
    private String attractionsId;

    //constructor
    public HPAttractionInfoVO() {
    	
    }
    //constructor
    public HPAttractionInfoVO(String username, String password, BigDecimal identity, String email, byte[] picture, String nickname, Date modifyDate, Date register, String favorite, String attractionsId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.nickname = nickname;
        this.modifyDate = modifyDate;
        this.register = register;
        this.favorite = favorite;
        this.attractionsId = attractionsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getAttractionsId() {
        return attractionsId;
    }

    public void setAttractionsId(String attractionsId) {
        this.attractionsId = attractionsId;
    }
    
    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", name='" + password + '\'' +
                ", email='" + email+ '\'' +
                ", picture='" + picture+ '\'' +
                ", nickname=" + nickname+
                ", modifyDate=" + modifyDate +
                ", register+=" + register+
                ", favorite ='" + favorite + '\'' +
                ", attractionsId ='" + attractionsId +
                '}';
    }

	
}
