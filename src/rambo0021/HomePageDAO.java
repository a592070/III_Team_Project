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

import com.sun.org.apache.regexp.internal.recompile;

import controller.ConnectionPool;
import sun.nio.cs.ext.ISCII91;

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
			String email = rs.getString("email");
			account.setEmail(email);
			String nickname = rs.getString("nickname");
			account.setNickName(nickname);
			Date modify_date = rs.getDate("modify_date");
			account.setModify_Date(modify_date);
			Date register = rs.getDate("register");
			account.setRegister(register);
			InputStream is = rs.getBlob("picture").getBinaryStream();
//			InputStream picture=  blob.getBinaryStream();
			account.setPicture(is);
//			FileOutputStream fileOutputStream = new FileOutputStream(path+"/"+username+".png");
//			fileOutputStream.write(blob.getBytes(1, (int)blob.length()));
//			fileOutputStream.flush();
//			fileOutputStream.close();
			is.close();
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
