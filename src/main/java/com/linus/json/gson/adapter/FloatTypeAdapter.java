package com.linus.json.gson.adapter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * You can't register primitive type adapter in old version GSON, if you meet such exception, please upgrade your GSON version.
 * 
 * @author lyan2
 */
public class FloatTypeAdapter extends TypeAdapter<Float> {
	private static NumberFormat percentFormat = NumberFormat.getPercentInstance();

	@Override
	public void write(JsonWriter out, Float value) throws IOException {
		if (value == null) {
			out.nullValue();
			return;
		}
		
		out.value(value);
	}

	/**
	 * If input is empty string "" or null, return null. 
	 */
	@Override
	public Float read(JsonReader reader) throws IOException {
		if(reader.peek() == JsonToken.NULL){
            reader.nextNull();
            return null;
        }
		
		String stringValue = reader.nextString();
        try{
        	if (stringValue.indexOf("%") >= 0) {
        		Number num = percentFormat.parse(stringValue);
        		return num.floatValue();
        	} else {
        		return Float.valueOf(stringValue);
        	}
        }catch(NumberFormatException | ParseException e){
            return null;
        }
	}

}
