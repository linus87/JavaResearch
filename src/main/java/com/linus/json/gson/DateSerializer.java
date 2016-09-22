package com.linus.json.gson;

import java.lang.reflect.Type;
import java.util.Date;

import javax.swing.text.DateFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class DateSerializer implements JsonSerializer<Date> {

	public JsonElement serialize(Date src, Type typeOfSrc,
			JsonSerializationContext context) {
		// TODO Auto-generated method stub
		
		return new JsonPrimitive(src.toString());
	}

}
