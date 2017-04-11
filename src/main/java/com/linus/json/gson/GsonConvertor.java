package com.linus.json.gson;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.linus.json.gson.adapter.DoubleTypeAdapter;
import com.linus.json.gson.adapter.FloatTypeAdapter;
import com.linus.json.gson.adapter.ShortTypeAdapter;
import com.linus.json.gson.deserializer.DateDeserializer;

/**
 * 
 * @author lyan2
 */
public class GsonConvertor {
	public static Gson gson = createGsonInstance();
	
	public static void main(String[] args) {
		JsonObject obj = new JsonObject();
		obj.addProperty("time", 1483518702840l);
//		obj.addProperty("time", "2015-02-27");
		
		Date date = gson.fromJson(obj.get("time"), Date.class);
		System.out.println(date.getTime());
		
		User user = gson.fromJson(obj, User.class);
		System.out.println(user.getTime());
		
		TypeToken<User> type = new TypeToken<User>() {};
		user = gson.fromJson(obj, type.getType());
		System.out.println(user.getTime());
	}

	private static Gson createGsonInstance () {
		return new GsonBuilder().serializeNulls()
			.registerTypeAdapter(Double.class, new DoubleTypeAdapter())
			.registerTypeAdapter(Float.class, new FloatTypeAdapter())
			.registerTypeAdapter(Short.class, new ShortTypeAdapter())
			.registerTypeAdapter(Date.class, new DateDeserializer())
			.create();
	}
	
	public static <T> T convertJSonToObject(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}
	
	public static <T> T convertJSonToObject(String json, TypeToken <T> typeToken) {
		return gson.fromJson(json, typeToken.getType());
	}
	
}

class User {
	private Date time;

	public Date getTime() {
		return time;
	}
}