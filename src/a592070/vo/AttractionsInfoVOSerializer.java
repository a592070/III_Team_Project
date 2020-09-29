package a592070.vo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AttractionsInfoVOSerializer extends JsonSerializer<AttractionsInfoVO> {

    // mapping AttractionsInfoVO to json
    // output likes {"name":xxx, "address":xxx, "opentime":xxx, "region":xxx, "area":xxx, "type":xxx, "rating":xxx}
    // name address opentime region area type rating
    @Override
    public void serialize(AttractionsInfoVO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", value.getName());
        gen.writeStringField("address", value.getAddress());
        gen.writeStringField("opentime", value.getOpentime());
        gen.writeNumberField("rating", value.getRating());
        gen.writeStringField("area", value.getArea());
        gen.writeStringField("type", value.getType());
        gen.writeEndObject();
    }
}
