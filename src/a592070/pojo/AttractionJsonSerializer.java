package a592070.pojo;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class AttractionJsonSerializer extends JsonSerializer<AttractionDO> {
    @Override
    public void serialize(AttractionDO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("sn", value.getSn());
        gen.writeStringField("name", value.getName());
        gen.writeStringField("address", value.getAddress());
        gen.writeStringField("description", value.getDescription());
        gen.writeStringField("ticketInfo", value.getTicketInfo());
        gen.writeStringField("picture", value.getPicture());
        gen.writeEndObject();
    }
}
