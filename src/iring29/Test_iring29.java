package iring29;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import controller.ConnectionPool;
import pojo.AccountDO;

public class Test_iring29 {
	public static void main(String[] args) throws IOException, SQLException {

		//TEST1
//		HomepageInit hpInit = new HomepageInit();
//		AccountDO accDo = new AccountDO();
//		hpInit.searchUsername(accDo);
//		System.out.println(hpInit.password);
//		String param1 = hpInit.password;
//		System.out.println(param1);

		//TEST2
		HomepageDAO homepageDAO = new HomepageDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
		//記得要改LOADING_WITHOUT_SERVER
		 System.out.println(homepageDAO.listAccDO().get(0));
//homepageDAO.listAccInit();


		
	}
}
