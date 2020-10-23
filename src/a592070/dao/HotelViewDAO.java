package a592070.dao;

import a592070.pojo.CarVO;
import a592070.pojo.HotelVO;
import controller.ConnectionPool;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelViewDAO {
    private Session session;

    public HotelViewDAO(Session session) {
        this.session = session;
    }

    public HotelVO getEle(int id){
        return session.get(HotelVO.class, id);
    }
    public HotelVO getEle(String fieldName, Object fieldValue){
        fieldValue = "%"+fieldValue+"%";
        String hql = "from HotelVO where "+fieldName+" like ?1";
        Query<HotelVO> query = session.createQuery(hql, HotelVO.class);
        query.setParameter(1, fieldValue);

        return query.uniqueResultOptional().orElse(null);
    }

    public List<HotelVO> listEle() {
        return session.createQuery("from HotelVO order by sn", HotelVO.class).list();
    }

    public List<HotelVO> listEleByRownum(int startIndex, int endIndex){
        Query<HotelVO> query = session.createQuery("from HotelVO order by sn", HotelVO.class);
        query.setFirstResult(startIndex);
        query.setMaxResults(endIndex);
        return query.list();
    }
}
