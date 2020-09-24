package pojo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import controller.initTable.DataInit;

import java.io.IOException;
import java.math.BigDecimal;

public class AttractionsDOJsonDeserializer extends JsonDeserializer<AttractionsDO> {



    @Override
    public AttractionsDO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);

        AttractionsDO attractionsDO = new AttractionsDO();

        String id = node.get("Id").textValue();

        attractionsDO.setType(new BigDecimal(id.substring(1,2)));
        attractionsDO.setId(id);

        attractionsDO.setName(node.get("Name").textValue());
        attractionsDO.setDescription(node.get("Description").textValue());
        attractionsDO.setTel(node.get("Tel").textValue());
        attractionsDO.setAddress(node.get("Add").textValue());
        attractionsDO.setRegion(node.get("Region").textValue());

        attractionsDO.setPx(node.get("Px").decimalValue());
        attractionsDO.setPy(node.get("Py").decimalValue());


        if(node.has("Opentime")) attractionsDO.setOpenTime(node.get("Opentime").textValue());
        if(node.has("Travellinginfo")) attractionsDO.setTravelingInfo(node.get("Travellinginfo").textValue());
        if(node.has("TotalNumberofRooms")) attractionsDO.setTotalNumberRooms(node.get("TotalNumberofRooms").textValue());
        if(node.has("Serviceinfo")) attractionsDO.setServiceInfo(node.get("Serviceinfo").textValue());

        return attractionsDO;
    }
}
