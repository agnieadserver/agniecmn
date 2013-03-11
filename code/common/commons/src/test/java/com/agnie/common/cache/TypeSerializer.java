package com.agnie.common.cache;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class TypeSerializer extends JsonSerializer<Type> {

	@Override
	public void serialize(Type value, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		generator.writeString(value.name());
	}

}
