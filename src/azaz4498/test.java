package azaz4498;

import java.io.IOException;
import java.sql.SQLException;

import controller.ConnectionPool;

public class test {

	public static void main(String[] args) throws IOException, SQLException{
		AttractionsPageDAO attractionsPageDAO = new AttractionsPageDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
		attractionsPageDAO.pageListInit();
		

	}

}
