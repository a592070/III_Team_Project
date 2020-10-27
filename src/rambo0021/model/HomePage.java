package rambo0021.model;

import org.hibernate.Session;


public class HomePage {
	private Session session;
	
	public HomePage() {
		
	}

	public HomePage(Session session) {
		this.session = session;
	}
	
	public AccountBean selectUser(String username) {
		return session.get(AccountBean.class,username);
	}
	public AccountBean update(AccountBean account) {
		AccountBean result = session.get(AccountBean.class,account.getUserName());
		if(result==null) {
			session.save(account);
		}else{
			session.merge(account);
		}
		return result;
	}
}
