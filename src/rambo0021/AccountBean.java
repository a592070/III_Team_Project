package rambo0021;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.sql.DATE;

public class AccountBean {

	private String userName;
	private String password;
	private int identity;
	private String email;
	private Blob picture;
	private Date modify_Date = new Date();
	private String nickName;
	private Date register = new Date();
	private String identityString;
	
	public AccountBean() {
		
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




//	public Date getModify_Date() {
//		return modify_Date;
//	}
	public String getModify_Date() {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		String Date = sdf.format(modify_Date);
		return Date;
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


//	public Date getRegister() {
//		return register;
//	}
	public String getRegister() {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		String Date2 = sdf.format(register);
		return Date2;
	}


	public void setRegister(Date register) {
		this.register = register;
	}

	@Override
	public String toString() {
		return "AccountBean [userName=" + userName + ", password=" + password + ", identity=" + identity + ", email="
				+ email + ", picture=" + picture + ", modify_Date=" + modify_Date + ", nickName=" + nickName
				+ ", register=" + register + ", identityString=" + identityString + "]";
	}


	public Blob getPicture() {
		return picture;
	}





	public void setPicture(Blob picture) {
		this.picture =  picture;
	}


	public String getIdentityString() {
		return identityString;
	}


	public void setIdentityString(String identityString) {
		this.identityString = identityString;
	}
	
	
}
