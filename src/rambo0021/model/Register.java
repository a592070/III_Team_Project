package rambo0021.model;

import org.hibernate.Session;

public class Register {
	
	private Session session;
	
	public Register(Session session) {
		this.session = session;
	}
	public AccountBean insertData(AccountBean account) {
		IdentityBean identityBean = session.get(IdentityBean.class,account.getIdentity());
		 AccountBean rs = session.get(AccountBean.class,account.getUserName());
	    if(rs==null) {
	    	account.setIdentityBean(identityBean);
	    	session.save(account);
	    	return account;
	    }else {
	    	return null;
	    }
	}
	public boolean checkusr(String username) {
		AccountBean accountBean = session.get(AccountBean.class,username);
		if(accountBean!=null) {
			return true;
		}else {
			return false;
		}
	}
}
