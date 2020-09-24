package pojo;

import java.math.BigDecimal;
import java.sql.Date;

public class AccountDO {
    private String username;
    private String password;
    private BigDecimal identity;
    private String email;

    private byte[] picture;

    private String nickname;
    private Date modifyDate;
    private Date register;
    private String favorite;
    private String attractionsId;

    public AccountDO(String username, String password, BigDecimal identity, String email, byte[] picture, String nickname, Date modifyDate, Date register, String favorite, String attractionsId) {
        this.username = username;
        this.password = password;
        this.identity = identity;
        this.email = email;
        this.picture = picture;
        this.nickname = nickname;
        this.modifyDate = modifyDate;
        this.register = register;
        this.favorite = favorite;
        this.attractionsId = attractionsId;
    }

    public AccountDO() {
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

    public BigDecimal getIdentity() {
        return identity;
    }

    public void setIdentity(BigDecimal identity) {
        this.identity = identity;
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
}
