package rambo0021.model;

import org.hibernate.Session;

public class HomePage {
	private Session session;

	public HomePage(Session session) {
		this.session = session;
	}
	
	public AccountBean selectUserData(String username) {
		return session.get(AccountBean.class,username);
	}

}
