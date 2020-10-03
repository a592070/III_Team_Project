package a592070.initTable;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class InitTableTest {
    @Test
    public void test() throws IOException, SQLException {
        new AttractionTableInit(AttractionTableInit.Scenic_Spot_URL).initTable();
    }
}
