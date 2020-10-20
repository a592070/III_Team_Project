package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static Session getSession(){
        return sessionFactory.openSession();
    }
    public static void closeSession(Session session){
        if(session.getTransaction() != null) session.getTransaction().commit();
        session.close();
    }
    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            synchronized (HibernateUtil.class) {
                if (sessionFactory == null) {
                    StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure().build();
                    sessionFactory = new MetadataSources(build).buildMetadata().buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }
    public static void closeSessionFactory(){
        if(sessionFactory != null) sessionFactory.close();
    }
}
