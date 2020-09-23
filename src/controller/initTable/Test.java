package controller.initTable;

import java.io.IOException;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws IOException, SQLException {
        new AttractionsInit(AttractionsInit.Scenic_Spot_URL).initTable();
        new AttractionsInit(AttractionsInit.Restaurant_URL).initTable();
        new AttractionsInit(AttractionsInit.Hotel_URL).initTable();
    }
}
