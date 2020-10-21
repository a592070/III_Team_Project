package a592070.dao;

import a592070.pojo.AttractionDO;
import controller.ConnectionPool;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.StringUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class AttractionDAO {
    private DataSource ds;
    private Connection conn;
    private String sql;
    private PreparedStatement predStmt;
    private ResultSet rs;
    private int size;

    private Session session;

    public AttractionDAO(Session session) {
        this.session = session;
    }
    public AttractionDAO(int type) throws IOException {
        this.ds = ConnectionPool.getDataSource(type);
    }

    public int getSize() {
        return session.createQuery("select count(sn) from AttractionDO", Integer.class).uniqueResult();
    }

    public AttractionDO getAttraction(int id) {
        return session.get(AttractionDO.class, id);
    }

    public AttractionDO getAttraction(String fieldName, Object fieldValue){
        String hql = "from AttractionDO where "+fieldName+" = ? ";
        Query<AttractionDO> query = session.createQuery(hql, AttractionDO.class);
        query.setParameter(0, fieldValue);

        return query.uniqueResultOptional().orElse(null);
    }
    public List<AttractionDO> listAttractionLike(String fieldName, String fieldValue) {
        fieldValue = "%"+fieldValue+"%";
        String hql = "from AttractionDO where "+fieldName+" like ?";
        Query<AttractionDO> query = session.createQuery(hql, AttractionDO.class);
        query.setParameter(0, fieldValue);

        return query.list();
    }

    public int getAttractionKeyWordsSize(String keyWords) {
        if(StringUtil.isEmpty(keyWords)) {
            keyWords="";
        }else {
            keyWords = "%"+keyWords+"%";
        }
        String hql = "select count(sn) from AttractionDO where name like ? or toldescribe like ? or description like ? or address like ? or keywords like ? ";
        Query<Integer> query = session.createQuery(hql, Integer.class);
        query.setParameter(0, keyWords);
        query.setParameter(1, keyWords);
        query.setParameter(2, keyWords);
        query.setParameter(3, keyWords);
        query.setParameter(4, keyWords);

        return query.uniqueResultOptional().orElse(0);
    }
    public List<AttractionDO> listAttractionLike(int startIndex, int endIndex, String keyWords) {
        if(StringUtil.isEmpty(keyWords)) {
            keyWords="";
        }else {
            keyWords = "%"+keyWords+"%";
        }
        String hql = "from AttractionDO where name like ? or toldescribe like ? or description like ? or address like ? or keywords like ? ";

        Query<AttractionDO> query = session.createQuery(hql, AttractionDO.class);
        query.setParameter(0, keyWords);
        query.setParameter(1, keyWords);
        query.setParameter(2, keyWords);
        query.setParameter(3, keyWords);
        query.setParameter(4, keyWords);
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
        String hql = "select count(sn) from AttractionDO where region like ? ";
        Query<Integer> query = session.createQuery(hql, Integer.class);
        query.setParameter(0, region);

        return query.uniqueResultOptional().orElse(0);
    }
    public List<AttractionDO> listAttractionByRownum(int startIndex, int endIndex, String region){
        if(StringUtil.isEmpty(region)){
            region = "%%";
        }else {
            region = "%" + region + "%";
        }

        String hql = "from AttractionDO where region like ? ";
        Query<AttractionDO> query = session.createQuery(hql, AttractionDO.class);
        query.setParameter(0, region);

        query.setFirstResult(startIndex);
        query.setMaxResults(endIndex);

        return query.list();
    }

}
