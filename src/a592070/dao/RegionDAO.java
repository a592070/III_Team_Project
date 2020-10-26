package a592070.dao;

import a592070.pojo.RegionDO;
import org.hibernate.Session;

import java.util.List;

public class RegionDAO {
    private Session session;

    public RegionDAO(Session session){
        this.session = session;
    }
    public List<RegionDO> listRegion(){
        return session.createQuery("from RegionDO order by area", RegionDO.class).list();
    }

}
