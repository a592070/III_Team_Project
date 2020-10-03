package a592070.initTable;

import a592070.pojo.AttractionDO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.AttractionsDO;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class DataInit {
    // https://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json
    // https://gis.taiwan.net.tw/XMLReleaseALL_public/restaurant_C_f.json
    // https://gis.taiwan.net.tw/XMLReleaseALL_public/hotel_C_f.json

    public static final int Attraction_Scenic_Spot = 1;
    public static final int Attraction_Restaurant = 3;
    public static final int Attraction_Hotel = 4;

    private String dataUrl;
    private int type;

    private String sJson;

    public DataInit(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    private String getJsonString() throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) new URL(dataUrl).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36");
        conn.setRequestMethod("GET");
        conn.connect();

        if(conn.getResponseCode() == 200){
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder stringBuffer = new StringBuilder();

            char[] buf = new char[1024*1024];
            int len;
            while((len = bufIn.read(buf)) > 0){
                stringBuffer.append(buf, 0, len);
            }

            bufIn.close();
            conn.disconnect();
            sJson = stringBuffer.toString().trim().replaceFirst("\ufeff", "");

            return sJson;
        }else{
            conn.disconnect();
            return null;
        }
    }

    public List<AttractionDO> getListDO() throws IOException {
        getJsonString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        JsonNode jsonNode = mapper.readTree(sJson);
        JsonNode info = jsonNode.path("XML_Head").path("Infos").path("Info");

        List<AttractionDO> list = mapper.readerForListOf(AttractionDO.class).readValue(info);

        return list;
    }
}
