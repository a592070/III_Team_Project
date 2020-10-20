package azaz4498;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import controller.ConnectionPool;

public class test {

	public static void main(String[] args) throws IOException, SQLException{
		ForumDAO2 forumDAO = new ForumDAO2(ConnectionPool.LOADING_WITHOUT_SERVER);
		
		
		
//		forumDAO.articleListInit();
//		System.out.println(forumDAO.artileList());
//		int commNum = forumDAO.getCommNumByArtId(2);
//		System.out.println(commNum);
		
//		System.out.println(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));

	}

}
