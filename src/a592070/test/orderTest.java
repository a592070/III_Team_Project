package a592070.test;

import innocence741.model.T_Order_List;
import iring29.model.R_OrderBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import pojo.OrderTableBean;
import utils.HibernateUtil;

public class orderTest {
    @Test
    public void test1(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        OrderTableBean oTBean = session.get(OrderTableBean.class, 26);
        System.out.println(oTBean.getOrder_id());
        System.out.println(oTBean.getUser().getUserName());
        for (T_Order_List ele : oTBean.getT_Order_Lists()) {
            System.out.println(ele.getT_sn_order());
        }
        System.out.println("===================");
        for (R_OrderBean ele : oTBean.getR_OrderBeans()) {
            System.out.println(ele.getR_sn_order());
        }

    }
}
