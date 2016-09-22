package com.linus.json.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linus.json.jackson.pojo.UserUnderline;

public class UnderlineTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		// By default, JsonGenerator will close the underlying output target that is not owned by the generator.
		mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		// By default, ObjectMapper will throw  UnrecognizedPropertyException if encounter unknown properties in JSON string.
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,	false);
		
		String json = "{\"age\":28,\"name\":{\"first\":\"joe\",\"last\":\"Sixpack\"},\"verified\":true,\"user_image\":\"Rm9vYmFyIQ==\",\"gender\":\"FEMALE\"}";
		
		UserUnderline uu = mapper.readValue(json, UserUnderline.class);
		System.out.println(mapper.writeValueAsString(uu));
	}

}
