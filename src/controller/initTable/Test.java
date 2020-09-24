package controller.initTable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.StringUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) throws IOException, SQLException {
//        new AttractionsInit(AttractionsInit.Scenic_Spot_URL).initTable();
//        new AttractionsInit(AttractionsInit.Restaurant_URL).initTable();
//        new AttractionsInit(AttractionsInit.Hotel_URL).initTable();

        String str = "{\"abc\":\"123\", \"def\":[\"1\", \"2\"], \"qwe\":{\"a\":[\"1\", \"2\"], \"b\":\"3\"}}";

        /**
         * {
         *     abc:123,
         *     def:[1,2],
         *     qwe:{
         *          a:[1,2],
         *          b:3
         *     }
         * }
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


    }
}

