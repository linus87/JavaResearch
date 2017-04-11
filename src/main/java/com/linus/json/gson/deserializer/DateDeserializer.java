package com.linus.json.gson.deserializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.linus.date.DateUtil;

/**
 * Deserialize date of which format is like: yyyy-MM-dd, yyyy/MM/dd and yyyy.MM.dd.
 * 
 * @author lyan2
 */
public class DateDeserializer implements JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		try {
			Long time = json.getAsLong();
			return new Date(time);
		} catch (Exception e) {
			try {
				return DateUtil.parseDate(json.getAsString());
			} catch (ParseException pe) {
				throw new JsonSyntaxException(json.getAsString(), pe);
			}
		}
	}
	
}
