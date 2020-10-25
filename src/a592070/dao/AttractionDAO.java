package a592070.dao;

import a592070.pojo.AttractionDO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.StringUtil;

import java.util.List;

public class AttractionDAO {
    private Session session;

    public AttractionDAO(Session session) {
        this.session = session;
    }

    public int getSize() {
        return session.createQuery("select count(*) from AttractionDO", Long.class).uniqueResult().intValue();
    }

    public AttractionDO getAttraction(int id) {
        return session.get(AttractionDO.class, id);
    }

    public AttractionDO getAttraction(String fieldName, Object fieldValue){
        String hql = "from AttractionDO where "+fieldName+" = ?1 ";
        Query<AttractionDO> query = session.createQuery(hql, AttractionDO.class);
        query.setParameter(1, fieldValue);

        return query.uniqueResultOptional().orElse(null);
    }

    public int getAttractionKeyWordsSize(String keyWords) {
        if(StringUtil.isEmpty(keyWords)) {
            keyWords="%%";
        }else {
            keyWords = "%"+keyWords+"%";
        }
        String hql = "select count(*) from AttractionDO where name like ?1 or toldescribe like ?2 or description like ?3 or address like ?4 or keywords like ?5 ";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter(1, keyWords);
        query.setParameter(2, keyWords);
        query.setParameter(3, keyWords);
        query.setParameter(4, keyWords);
        query.setParameter(5, keyWords);

        return query.uniqueResult().intValue();
    }
    public List<AttractionDO> listAttractionLike(int startIndex, int size, String keyWords) {
        if(StringUtil.isEmpty(keyWords)) {
            keyWords="%%";
        }else {
            keyWords = "%"+keyWords+"%";
        }
        String hql = "from AttractionDO where name like ?1 or toldescribe like ?2 or description like ?3 or address like ?4 or keywords like ?5 order by picture, sn";

        Query<AttractionDO> query = session.createQuery(hql, AttractionDO.class);
        query.setParameter(1, keyWords);
        query.setParameter(2, keyWords);
        query.setParameter(3, keyWords);
        query.setParameter(4, keyWords);
        query.setParameter(5, keyWords);
        query.setFirstResult(startIndex);
        query.setMaxResults(size);

        return query.list();
    }

    public int getAttractionRegionSize(String region) {
        if(StringUtil.isEmpty(region)){
            region = "%%";
        }else {
            region = "%" + region + "%";
        }
        String hql = "select count(*) from AttractionDO where region like ?1 ";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter(1, region);

        return query.uniqueResult().intValue();
    }
    public List<AttractionDO> listAttractionByRownum(int startIndex, int size, String region){
        region = "%" + region + "%";

        String hql = "from AttractionDO where region like ?1  order by picture, sn ";
        Query<AttractionDO> query = session.createQuery(hql, AttractionDO.class);
        query.setParameter(1, region);

        query.setFirstResult(startIndex);
        query.setMaxResults(size);

        return query.list();
    }

}
