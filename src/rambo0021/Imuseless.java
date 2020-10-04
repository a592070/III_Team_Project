package rambo0021;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Imuseless {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		RegisterDAO registerDAO = new RegisterDAO();
//		registerDAO.selectPicture();
		ArrayList<AccountBean> selectUserData = registerDAO.selectUserData("");
		SimpleDateFormat aaa=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}

}
