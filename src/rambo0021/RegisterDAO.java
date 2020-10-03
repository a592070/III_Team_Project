package rambo0021;

import controller.ConnectionPool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;



public class RegisterDAO {
	public void insertData(AccountBean account) {
	try {
		DataSource dataSource = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITH_SERVER);
		Connection connection = dataSource.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("insert into account (username,password,identity,email,picture,modify_date,nickname,register) values(?,?,?,?,?,?,?,?)");
		//1.username(str)2.password(srt)3.identity(int)4.email(str)5.picture(inputStream)6.modify_date(date)7.nickname(str)8.register(date)
		prepareStatement.setString(1, account.getUserName());   
		prepareStatement.setString(2, account.getPassword());
		prepareStatement.setInt(3, account.getIdentity());
		prepareStatement.setString(4, account.getEmail());
		prepareStatement.setBinaryStream(5, account.getPicture());
		Date date = new Date(account.getModify_Date().getTime());
		prepareStatement.setDate(6, date);
		prepareStatement.setString(7, account.getNickName());
		prepareStatement.setDate(8, date);
		
		int updatecount = prepareStatement.executeUpdate();
		
		
		if (updatecount==1) {
			connection.commit();
			prepareStatement.clearParameters();
			prepareStatement.close();
			connection.close();
		}else {
			connection.rollback();
			prepareStatement.clearParameters();
			prepareStatement.close();
			connection.close();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void selectUserData(String path) {}
}
