package global;

import utils.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class OpenSessionViewFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            System.out.println("CurrentSession beginTransaction");

            chain.doFilter(request, response);

            sessionFactory.getCurrentSession().getTransaction().commit();
            System.out.println("CurrentSession commit");
        }catch (Exception e){
            sessionFactory.getCurrentSession().getTransaction().rollback();
            System.out.println("CurrentSession rollback");
        }
    }
}
