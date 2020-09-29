package rambo0021;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

public class Account {

	private String username;
	private String password;
	private int identity;
	private String email;
	private InputStream picture;
	private Date modify_Date;
	private String nickname;
	private Date register;
	private String favorite;
	private String attractions_Id;
	
	public Account() {
		
	}
	
	
	public Account(String username, String password, int identity, String email, InputStream picture, Date modify_Date,
			String nickname, Date register, String favorite, String attractions_Id) {
		super();
		this.username = username;
		this.password = password;
		this.identity = identity;
		this.email = email;
		this.picture = picture;
		this.modify_Date = modify_Date;
		this.nickname = nickname;
		this.register = register;
		this.favorite = favorite;
		this.attractions_Id = attractions_Id;
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
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public InputStream getPicture() {
		return picture;
	}
	public void setPicture(InputStream picture) {
		this.picture = picture;
	}
	public Date getModify_Date() {
		return modify_Date;
	}
	public void setModify_Date(Date modify_Date) {
		this.modify_Date = modify_Date;
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
	public String getAttractions_Id() {
		return attractions_Id;
	}
	public void setAttractions_Id(String attractions_Id) {
		this.attractions_Id = attractions_Id;
	}
	
	

	@Override
	public String toString() {
		return "AccountDO [username=" + username + ", password=" + password + ", identity=" + identity + ", email="
				+ email + ", picture=" + picture + ", modify_Date=" + modify_Date + ", nickname=" + nickname
				+ ", register=" + register + ", favorite=" + favorite + ", attractions_Id=" + attractions_Id
				+"]";
	}
	
}
