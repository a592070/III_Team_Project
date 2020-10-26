package a592070.dao;

import a592070.pojo.CarVO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CarViewDAO {
    private Session session;

    public CarViewDAO(Session session) {
        this.session = session;
    }

    public CarVO getEle(int id) {
        return session.get(CarVO.class, id);
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
