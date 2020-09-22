package controller.initTable;

import java.io.IOException;
import java.util.List;

import pojo.AttractionsDO;

public class Test {
    public static void main(String[] args) throws IOException {
    	List<AttractionsDO> list = new DataInit("https://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json").getListDO();
    	System.out.println(list);
    }
}
//try