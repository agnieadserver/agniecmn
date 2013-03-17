package com.agnie.common.cache;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class EnumSerializer extends JsonSerializer<Enum<?>> {

	@Override
	public void serialize(Enum<?> value, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		generator.writeString(value.name());
	}

}
