package controller.initTable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.StringUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

import javax.sql.DataSource;

import controller.ConnectionPool;

public class Test {
	public static void main(String[] args) throws IOException, SQLException {
//        AttractionsInit attractionsInit = new AttractionsInit(AttractionsInit.Scenic_Spot_URL);
//        attractionsInit.initTable();
//        attractionsInit.updateRating();

//        attractionsInit = new AttractionsInit(AttractionsInit.Restaurant_URL);
//        attractionsInit.initTable();
//        attractionsInit.updateRating();

//        attractionsInit = new AttractionsInit(AttractionsInit.Hotel_URL);
//        attractionsInit.initTable();
//        attractionsInit.updateRating();

		String str = "{\"abc\":\"123\", \"def\":[\"1\", \"2\"], \"qwe\":{\"a\":[\"1\", \"2\"], \"b\":\"3\"}}";

		/**
		 * { abc:123, def:[1,2], qwe:{ a:[1,2], b:3 } }
		 *
		 */

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(str);

		JsonNode node = jsonNode.path("qwe");
		System.out.println(node);

//        Iterator<JsonNode> iterator = node.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next().textValue());
//        }
//        System.out.println(node.get(0));

		
		// get DB connection
		DataSource ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
		Connection conn = ds.getConnection();

	}
}
