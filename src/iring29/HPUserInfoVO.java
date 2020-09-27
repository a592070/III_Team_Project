package iring29;

import java.math.BigDecimal;
import java.sql.Date;

public class HPUserInfoVO {
	private String username;
    private String password;
    private String email;
    private byte[] picture;
    private String nickname;
    private Date register;
    private String favorite;
    
    //constructor
    public HPUserInfoVO() {
    	
    }
    //constructor
    public HPUserInfoVO(String username, String password, String email, byte[] picture, String nickname, Date register, String favorite) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.nickname = nickname;
        this.register = register;
        this.favorite = favorite;
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

    
    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", name='" + password + '\'' +
                ", email='" + email+ '\'' +
                ", picture='" + picture+ '\'' +
                ", nickname=" + nickname+
                ", register+=" + register+
                ", favorite ='" + favorite + '\'' +
                '}';
    }
    
}
