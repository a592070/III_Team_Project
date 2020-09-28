package rambo0021;

import controller.ConnectionPool;

import java.io.IOException;

import javax.sql.DataSource;

public class RegisterDB {
	public void insertData() {
	try {
		ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
