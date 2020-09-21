package iii.teamproject.initTable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import iii.teamproject.pojo.AttractionsDO;
import iii.teamproject.utils.StringUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Iterator;

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
    private JSONObject json;

    public DataInit(String dataUrl, int attraction_type) {
        this.dataUrl = dataUrl;
        this.type = attraction_type;
    }

    public JSONObject getJson() throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) new URL(dataUrl).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36");
        conn.setRequestMethod("GET");
        conn.connect();

        if(conn.getResponseCode() == 200){
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuffer stringBuffer = new StringBuffer();

            String temp;
            while((temp=bufIn.readLine()) != null){
                stringBuffer.append(temp);
            }

            bufIn.close();
            conn.disconnect();
            sJson = stringBuffer.toString();
            json = JSON.parseObject(sJson);
            return json;
        }else{
            conn.disconnect();
            return null;
        }
    }

    public AttractionsDO getAttractionsDO(){
        AttractionsDO attractionsDO = new AttractionsDO();
        if(json == null) return attractionsDO;

        JSONArray info = json.getJSONObject("XML_Head").getJSONObject("infos").getJSONArray("Info");

        Iterator<Object> iterator = info.iterator();
        while (iterator.hasNext()){
            JSONObject obj = JSON.parseObject(iterator.toString());
            attractionsDO.setId(obj.getString("Id"));
            attractionsDO.setName(obj.getString("Name"));

            String description = obj.getString("Description");
            if(!StringUtil.isEmpty(description)) attractionsDO.setDescription(description);

            String add = obj.getString("Add");
            if(!StringUtil.isEmpty(add)) attractionsDO.setAddress(add);

            String region = obj.getString("Region");
            if(!StringUtil.isEmpty(region)) attractionsDO.setRegion(region);

            String tel = obj.getString("Tel");
            if(!StringUtil.isEmpty(tel)) attractionsDO.setTel(tel);

            String opentime = obj.getString("Opentime");
            if(!StringUtil.isEmpty(opentime)) attractionsDO.setOpenTime(opentime);

            String px = obj.getString("Px");
            if(!StringUtil.isEmpty(px)) attractionsDO.setPx(new BigDecimal(px));

            String py = obj.getString("Py");
            if(!StringUtil.isEmpty(py)) attractionsDO.setPy(new BigDecimal(py));

            if(type == Attraction_Scenic_Spot){
                String travellinginfo = obj.getString("Travellinginfo");
                if(!StringUtil.isEmpty(travellinginfo)) attractionsDO.setTravelingInfo(travellinginfo);
            }
            if(type == Attraction_Hotel){
                String totalNumberofRooms = obj.getString("TotalNumberofRooms");
                if(!StringUtil.isEmpty(totalNumberofRooms)) attractionsDO.setTotalNumberRooms(totalNumberofRooms);

                String serviceinfo = obj.getString("Serviceinfo");
                if(!StringUtil.isEmpty(serviceinfo)) attractionsDO.setServiceInfo(serviceinfo);
            }

            attractionsDO.setType(type);
        }

    }

}
