package a592070.pojo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;


public class AttractionJsonDeserializer extends JsonDeserializer<AttractionDO> {
    /**
     *     private String id;
     *     private String name;
     *     private String toldescribe;
     *     private String description;
     *     private String tel;
     *     private String address;
     *     private BigDecimal px;
     *     private BigDecimal py;
     *     private String openTime;
     *     private byte[] picture;
     *     private String ticketInfo;
     *     private String travelingInfo;
     *     private String keywords;
     *     private String remarks;
     *     private BigDecimal rating;
     *     private String region;
     */
    @Override
    public AttractionDO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);

        AttractionDO attractionDO = new AttractionDO();

        attractionDO.setId(node.get("Id").textValue());

        attractionDO.setName(node.get("Name").textValue());
        attractionDO.setToldescribe(node.get("Toldescribe").textValue());
        attractionDO.setDescription(node.get("Description").textValue());
        attractionDO.setTel(node.get("Tel").textValue());
        attractionDO.setAddress(node.get("Add").textValue());

        attractionDO.setPx(node.get("Px").decimalValue());
        attractionDO.setPy(node.get("Py").decimalValue());


        attractionDO.setOpenTime(node.get("Opentime").textValue());
        attractionDO.setTravellingInfo(node.get("Travellinginfo").textValue());
        attractionDO.setTicketInfo(node.get("Ticketinfo").textValue());
        attractionDO.setKeywords(node.get("Keyword").textValue());
        attractionDO.setRemarks(node.get("Remarks").textValue());
        attractionDO.setRegion(node.get("Region").textValue());
        attractionDO.setPicture(node.get("Picture1").textValue());

        return attractionDO;
    }
}
