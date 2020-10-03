package rambo0021;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

public class AccountDO {

	private String userName;
	private String password;
	private int identity;
	private String email;
	private InputStream picture;
	private Date modify_Date;
	private String nickName;
	private Date register;
	private String favorite;
	private String attractions_Id;
	
	public AccountDO() {
		
	}
	
	
	public AccountDO(String username, String password, int identity, String email, InputStream picture, Date modify_Date,
			String nickname, Date register, String favorite, String attractions_Id) {
		super();
		this.userName = username;
		this.password = password;
		this.identity = identity;
		this.email = email;
		this.picture = picture;
		this.modify_Date = modify_Date;
		this.nickName = nickname;
		this.register = register;
		this.favorite = favorite;
		this.attractions_Id = attractions_Id;
	}
	
	


	@Override
	public String toString() {
		return "AccountDO [username=" + userName + ", password=" + password + ", identity=" + identity + ", email="
				+ email + ", picture=" + picture + ", modify_Date=" + modify_Date + ", nickname=" + nickName
				+ ", register=" + register + ", favorite=" + favorite + ", attractions_Id=" + attractions_Id
				+"]";
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	
}
