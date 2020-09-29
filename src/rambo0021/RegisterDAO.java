package rambo0021;

import controller.ConnectionPool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;



public class RegisterDAO {
	public void insertData(Account account) {
	try {
		DataSource dataSource = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITH_SERVER);
		Connection connection = dataSource.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("insert into account (username,password,email,picture) values(?,?,?,?)");
		prepareStatement.setString(1, account.getUsername());
		prepareStatement.setString(2, account.getPassword());
		prepareStatement.setString(3, account.getEmail());
		prepareStatement.setBinaryStream(4, account.getPicture());
		int updatecount = prepareStatement.executeUpdate();
		System.out.println("DB is on");
		
		if (updatecount==1) {
			connection.commit();
			prepareStatement.clearParameters();
			prepareStatement.close();
		}else {
			connection.rollback();
			prepareStatement.clearParameters();
			prepareStatement.close();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void selectPicture(String path) {
		try {
			Blob blob=null;
			DataSource dataSource = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITH_SERVER);
			Connection connection = dataSource.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("select picture from account where username=?");
			prepareStatement.setString(1, "123");
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
			blob = rs.getBlob("picture");}
			FileOutputStream fileOutputStream = new FileOutputStream(path+"/123.png");
			fileOutputStream.write(blob.getBytes(1, (int)blob.length()));
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
