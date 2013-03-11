package com.agnie.common.cache;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class TypeDeserializer extends JsonDeserializer<Type> {

	@Override
	public Type deserialize(JsonParser parser, DeserializationContext arg1) throws IOException, JsonProcessingException {
		String val = parser.getText();
		if (val != null) {
			return Type.valueOf(val);
		}
		return null;
	}
}
