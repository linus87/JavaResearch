package com.linus.test.json.jackson;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
	private ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * readTree only accept "true", "false" and "null".
	 * 
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	@Test(expected=JsonParseException.class)
	public void readTreeTest() throws JsonProcessingException, IOException {
		mapper.readTree("MALE");
	}

}
