package rambo0021.model;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import oracle.sql.DATE;
import pojo.OrderTableBean;

@Entity
@Table(name="account")
@DynamicInsert
@DynamicUpdate
public class AccountBean {

	private String userName;
	private String password;
	private int identity;
	private String email;
	private byte[] picture;
	private Date modify_Date;
	private String nickName;
	private Date register;
	private String identityString;
	private String getModify_DateString;
	private String getRegisterString;
	private IdentityBean identityBean;
	
	private OrderTableBean orderTableBean;
	
	public AccountBean() {

	}
	
    @Id @Column(name="USERNAME")
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public int getIdentity() {
		return identity;
	}


	public void setIdentity(int identity) {
		this.identity = identity;
	}

	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



    @Transient
	public String getModify_DateString() {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		String Date = sdf.format(modify_Date);
		return Date;
	}


	public void setModify_Date(Date modify_Date) {
		this.modify_Date = modify_Date;
	}

	@Column(name="NICKNAME")
	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Transient
	public String getRegisterString() {
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

	@Column(name="PICTURE")
	public byte[] getPicture() {
		return picture;
	}




	@Column(name="MODIFY_DATE")
	public Date getModify_Date() {
		return modify_Date;
	}

	@Column(name="REGISTER")
	public Date getRegister() {
		return register;
	}


	public void setPicture(byte[] picture) {
		this.picture =  picture;
	}

	@Transient
	public String getIdentityString() {
		return identityString;
	}

	
	public void setIdentityString(String identityString) {
		this.identityString = identityString;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDENTITY")
	public IdentityBean getIdentityBean() {
		return identityBean;
	}

	public void setIdentityBean(IdentityBean identityBean) {
		this.identityBean = identityBean;
	}

	@OneToOne(fetch = FetchType.LAZY,mappedBy = "orderTableBean",cascade = CascadeType.ALL)
	public OrderTableBean getOrderTableBean() {
		return orderTableBean;
	}

	public void setOrderTableBean(OrderTableBean orderTableBean) {
		this.orderTableBean = orderTableBean;
	}

	
	
	
}
