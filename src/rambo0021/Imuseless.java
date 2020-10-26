package rambo0021;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import rambo0021.model.AccountBean;
import rambo0021.model.HomePage;
import rambo0021.model.Register;
import rambo0021.model.SHA2DAO;
import utils.HibernateUtil;

public class Imuseless {

	public static void main(String[] args) throws IOException {
          SessionFactory factory = HibernateUtil.getSessionFactory();
		
          Session session = factory.getCurrentSession();
          session.beginTransaction();
     
//          AccountBean aBean = session.get(AccountBean.class,"rambo005");
          
//          FileOutputStream fos = new FileOutputStream("D:\\test.png");
//          fos.write(aBean.getPicture());
          
          
       
//          AccountBean aBean = new AccountBean();
//          
//          FileInputStream fis = new FileInputStream("D:\\test.png");
//          aBean.setUserName("test8788");
//          aBean.setPassword("test8788");
//          aBean.setIdentity(1);
//          aBean.setPicture(fis.readAllBytes());
//          new Register(session).insertData(aBean);
////          
////          aBean.getUserName("rambo005");
////          aBean.setPassword(SHA2DAO.getSHA256("rambo0010"));
//          session.save(aBean);
//         fis.close();
          
          HomePage hDao = new HomePage(session);
          AccountBean aBean = hDao.selectUser("rambo005");
          System.out.println(aBean.toString());
          
        session.getTransaction().commit();  
		HibernateUtil.closeSessionFactory();;
	}
}
