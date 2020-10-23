package rambo0021;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import rambo0021.model.AccountBean;
import rambo0021.model.SHA2DAO;
import utils.HibernateUtil;

public class Imuseless {

	public static void main(String[] args) {
          SessionFactory factory = HibernateUtil.getSessionFactory();
		
          Session session = factory.getCurrentSession();
          session.beginTransaction();
          
          AccountBean aBean = new AccountBean();
          
          aBean.setUserName("rambo0010");
          aBean.setPassword(SHA2DAO.getSHA256("rambo0010"));
          session.save(aBean);
          
          
        session.getTransaction().commit();  
		HibernateUtil.closeSessionFactory();;
	}
}
