package a592070.dao;

import a592070.pojo.AttractionDO;
import a592070.pojo.CarVO;
import controller.ConnectionPool;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarViewDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;

    private Session session;
    public CarViewDAO(int connType) throws IOException {
        ds = ConnectionPool.getDataSource(connType);
    }
    public CarViewDAO(Session session) {
        this.session = session;
    }

    public CarVO getEle(int id) {
        return session.get(CarVO.class, id);
    }
    public CarVO getEle(String fieldName, Object fieldValue) {
        sql = "select * " +
                "from cartype c1, carrentalcompany c2 " +
                "where c1."+fieldName.toUpperCase()+"=? and c1.sn_rentcarcompany=c2.sn_rentalcompany " +
                "order by sn_cartype";
//                "select * from attraction where \""+columnName.toUpperCase()+"\"=? ";
        fieldValue = "%"+fieldValue+"%";
        String hql = "from CarVO where "+fieldName+" like ?";
        Query<CarVO> query = session.createQuery(hql, CarVO.class);
        query.setParameter(0, fieldValue);

        return query.uniqueResultOptional().orElse(null);
    }

    public List<CarVO> listEle(){
        return session.createQuery("from CarVO order by sn", CarVO.class).list();
    }

    public List<CarVO> listEleByRownum(int startIndex, int endIndex) {
        Query<CarVO> query = session.createQuery("from CarVO order by sn", CarVO.class);
        query.setFirstResult(startIndex);
        query.setMaxResults(endIndex);

        return query.list();
    }

}
