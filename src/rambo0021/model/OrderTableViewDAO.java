package rambo0021.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class OrderTableViewDAO {

	private Session session;

	public OrderTableViewDAO() {
	}
	public OrderTableViewDAO(Session session) {
		this.session = session;
	}
	public List<OrderTableVO> selectOrdertable(String username){
		Query query = session.createQuery("from model.OrderTableVO where username=?0");
		query.setParameter(0, username);
		List<OrderTableVO> list = query.list();
		return list;
	}
}
