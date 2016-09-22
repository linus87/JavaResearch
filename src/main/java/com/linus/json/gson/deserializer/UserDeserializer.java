package com.linus.json.gson.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.linus.json.gson.pojo.User;

public class UserDeserializer implements JsonDeserializer<User> {

	public User deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		User user = new User();
		User father;
		
		JsonObject jObject = json.getAsJsonObject();
		
		user.setUsername(jObject.get("name").getAsString());
		father = context.deserialize(jObject.get("father"), typeOfT);
		user.setFather(father);
		
		return user;
	}

}
