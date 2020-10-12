package azaz4498;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import controller.ConnectionPool;

public class test {

	public static void main(String[] args) throws IOException, SQLException{
		ForumDAO forumDAO = new ForumDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
		
		
		forumDAO.articleListInit();
//		System.out.println(forumDAO.artileList());
		
		
//		System.out.println(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));

	}

}
