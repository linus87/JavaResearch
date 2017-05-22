package com.linus.json.gson.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * You can't register primitive type adapter in old version GSON, if you meet
 * such exception, please upgrade your GSON version.
 * @author lyan2
 */
public class IntegerTypeAdapter extends TypeAdapter<Integer> {

    @Override
    public void write(JsonWriter out, Integer value) throws IOException {
	if (value == null) {
	    out.nullValue();
	    return;
	}

	out.value(value);
    }

    /**
     * If input is empty string "" or null, return null.
     * 
     * @throws IOException
     */
    @Override
    public Integer read(JsonReader reader) throws IOException {
	if (reader.peek() == JsonToken.NULL) {
	    reader.nextNull();
	    return null;
	}

	String stringValue = reader.nextString();
	try {
	    return Integer.valueOf(stringValue);
	} catch (NumberFormatException e) {
	    return null;
	}
    }
}
