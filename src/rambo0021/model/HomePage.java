package rambo0021.model;

import org.hibernate.Session;

public class HomePage {
	private Session session;

	public HomePage(Session session) {
		this.session = session;
	}
	
	public void selectUserData(String username) {
		
	}

}
