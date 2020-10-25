package a592070.dao;

import a592070.pojo.RestaurantVO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.StringUtil;

import java.util.List;

public class RestaurantViewDAO {
    private Session session;

    public RestaurantViewDAO(Session session) {
        this.session = session;
    }

    public RestaurantVO getEle(int id) {
        return session.get(RestaurantVO.class, id);
    }
    public RestaurantVO getEle(String fieldName, Object fieldValue){
        fieldValue = "%"+fieldValue+"%";
        String hql = "from RestaurantVO where "+fieldName+" like ?1";
        Query<RestaurantVO> query = session.createQuery(hql, RestaurantVO.class);
        query.setParameter(1, fieldValue);

        return query.uniqueResultOptional().orElse(null);
    }

    public List<RestaurantVO> listEle(String region) {
        region = "%"+region+"%";
        String hql = "from RestaurantVO where address like ?1 or region like ?2 order by sn";
        Query<RestaurantVO> query = session.createQuery(hql, RestaurantVO.class);
        query.setParameter(1, region);
        query.setParameter(2, region);

        return query.list();
    }

    public List<RestaurantVO> listEle() {
        return listEle("");
    }
    public int getSize(){
        return session.createQuery("select count(*) from RestaurantVO ", Long.class).uniqueResult().intValue();
    }

    public int getRestaurantRegionSize(String region){
        if(StringUtil.isEmpty(region)){
            region = "%%";
        }else {
            region = "%" + region + "%";
        }

        String hql = "select count(*) from RestaurantVO where region like ?1 ";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter(1, region);

        return query.uniqueResult().intValue();
    }
    public List<RestaurantVO> listEleByRownum(int startIndex, int endIndex) {
        return listEleByRownum(startIndex, endIndex, "");
    }
    public List<RestaurantVO> listEleByRownum(int startIndex, int endIndex, String region) {
        region = "%"+region+"%";
        String hql = "from RestaurantVO where region like ?1 order by sn";
        Query<RestaurantVO> query = session.createQuery(hql, RestaurantVO.class);
        query.setParameter(1, region);

        query.setFirstResult(startIndex);
        query.setMaxResults(endIndex);

        return query.list();
    }
}
