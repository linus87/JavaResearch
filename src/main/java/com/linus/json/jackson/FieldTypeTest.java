package com.linus.json.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linus.json.jackson.pojo.Sample;

public class FieldTypeTest {

    public static void main(String[] args) throws IOException {
	ObjectMapper mapper = new ObjectMapper();

	// By default, JsonGenerator will close the underlying output target that is not owned by the generator.
	mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
	// By default, ObjectMapper will throw  UnrecognizedPropertyException if encounter unknown properties in JSON string.
	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	Sample sample = new Sample();
	
	String json = mapper.writeValueAsString(sample);
	System.out.println(json);
	
	json = "{\"smallB\":null, \"isReversed\":true}";
	Sample sample2 = mapper.readValue(json, Sample.class);
	System.out.println(sample2.isSmallB());
	System.out.println(sample2.getIsReversed());
    }

}
