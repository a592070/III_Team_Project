package rambo0021;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;



import controller.ConnectionPool;


public class HomePageDAO {
	public void selectUserData(AccountBean account) {
		 
		try {
			DataSource dataSource = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITH_SERVER);
			Connection connection = dataSource.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("select * from account where username=?");
			System.out.println("db2 is on");
			prepareStatement.setString(1, account.getUserName());
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {	
			String password = rs.getString("password");
			account.setPassword(password);
			int identity = rs.getInt("identity");
			account.setIdentity(identity);
			if (identity==1) {
				account.setIdentityString("管理員");
			}else if(identity==2){
				account.setIdentityString("一般會員");
			}else if(identity==3) {
				account.setIdentityString("餐廳業者");
			}else if(identity==4) {
				account.setIdentityString("住宿業者");
			}else if(identity==5) {
				account.setIdentityString("交通業者");
			}
			String email = rs.getString("email");
			account.setEmail(email);
			String nickname = rs.getString("nickname");
			account.setNickName(nickname);
			Date modify_date = rs.getDate("modify_date");
			account.setModify_Date(modify_date);
			Date register = rs.getDate("register");
			account.setRegister(register);
//			InputStream is = rs.getBlob("picture");
//			InputStream picture=  blob.getBinaryStream();
			account.setPicture(rs.getBlob("picture"));
//			FileOutputStream fileOutputStream = new FileOutputStream(path+"/"+username+".png");
//			fileOutputStream.write(blob.getBytes(1, (int)blob.length()));
//			fileOutputStream.flush();
//			fileOutputStream.close();
//			is.close();
			}
			prepareStatement.clearParameters();
			prepareStatement.close();
			connection.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	
		
	}

}
