package a592070.dao;

import a592070.pojo.AttractionDO;
import a592070.pojo.AttractionVO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.StringUtil;

import java.util.List;

public class AttractionViewDAO {
    private Session session;

    public AttractionViewDAO(Session session) {
        this.session = session;
    }

    public int getSize() {
        return session.createQuery("select count(sn) from AttractionVO ", Integer.class).uniqueResult();
    }

    public AttractionVO getAttraction(int id) {
        return session.get(AttractionVO.class, id);
    }

    public List<AttractionVO> listAttractionLike(String fieldName, String fieldValue) {
        fieldValue = "%"+fieldValue+"%";
        String hql = "from AttractionVO where "+fieldName+" like ?1";
        Query<AttractionVO> query = session.createQuery(hql, AttractionVO.class);
        query.setParameter(1, fieldValue);

        return query.list();
    }

    public int getAttractionKeyWordsSize(String keyWords) {
        if(StringUtil.isEmpty(keyWords)) {
            keyWords="";
        }else {
            keyWords = "%"+keyWords+"%";
        }
        String hql = "select count(sn) from AttractionDO where name like ?1 or toldescribe like ?2 or description like ?3 or address like ?4 or keywords like ?5 ";
        Query<Integer> query = session.createQuery(hql, Integer.class);
        query.setParameter(1, keyWords);
        query.setParameter(2, keyWords);
        query.setParameter(3, keyWords);
        query.setParameter(4, keyWords);
        query.setParameter(5, keyWords);

        return query.uniqueResultOptional().orElse(0);
    }
    public List<AttractionVO> listAttractionLike(int startIndex, int endIndex, String keyWords) {
        if(StringUtil.isEmpty(keyWords)) {
            keyWords="";
        }else {
            keyWords = "%"+keyWords+"%";
        }
        String hql = "select vo from AttractionDO do , AttractionVO vo where (do.sn=vo.sn) and (do.name like ?1 or do.toldescribe like ?2 or do.description like ?3 or do.address like ?4 or do.keywords like ?5 )";

        Query<AttractionVO> query = session.createQuery(hql, AttractionVO.class);
        query.setParameter(1, keyWords);
        query.setParameter(2, keyWords);
        query.setParameter(3, keyWords);
        query.setParameter(4, keyWords);
        query.setParameter(5, keyWords);
        query.setFirstResult(startIndex);
        query.setMaxResults(endIndex);

        return query.list();
    }

    public int getAttractionRegionSize(String region) {
        if(StringUtil.isEmpty(region)){
            region = "%%";
        }else {
            region = "%" + region + "%";
        }
        String hql = "select count(sn) from AttractionDO where region like ?1 ";
        Query<Integer> query = session.createQuery(hql, Integer.class);
        query.setParameter(1, region);

        return query.uniqueResultOptional().orElse(0);
    }
    public List<AttractionVO> listAttractionByRownum(int startIndex, int endIndex, String region){
        if(StringUtil.isEmpty(region)){
            region = "%%";
        }else {
            region = "%" + region + "%";
        }

        String hql = "select vo from AttractionDO do, AttractionVO vo where do.sn=vo.sn and region like ?1 ";
        Query<AttractionVO> query = session.createQuery(hql, AttractionVO.class);
        query.setParameter(1, region);

        query.setFirstResult(startIndex);
        query.setMaxResults(endIndex);

        return query.list();
    }
}
