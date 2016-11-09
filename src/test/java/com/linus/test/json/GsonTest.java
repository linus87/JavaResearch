package com.linus.test.json;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.linus.json.gson.deserializer.UserDeserializer;
import com.linus.json.gson.pojo.Book;
import com.linus.json.gson.pojo.User;

public class GsonTest {
	
	@Test
	public void testPrimitiveType() {
		System.out.println("######################## Primitive Type Test: #####################");
		Gson gson = new Gson();
		
		// serialization
		System.out.println(gson.toJson(1));
		
		System.out.println(gson.toJson("abc"));
		
		System.out.println(gson.toJson(new Long(10)));		
		
		// deserialization
		Assert.assertEquals(1, (int)gson.fromJson("1", int.class));
		
		Assert.assertEquals(false, (boolean)gson.fromJson("false", boolean.class));
	}
	
	@Test
	public void testObjectType() {
		System.out.println("######################## Object Type Test: ########################");
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		User u = new User();
//		u.setPassword("world");
		u.setPassword(null);
		
		// serialization
		String json = gson.toJson(u);
		System.out.println(json);
		
		// deserialization
		User u2 = gson.fromJson(json, User.class);
		System.out.println(gson.toJson(u2));
		System.out.println(u2.getBirthday());
	}
	
	@Test
	public void testObjectField() {
		System.out.println("######################## Object field Test: ########################");
		Gson gson = new Gson();
		
		User u = new User();
		User father = new User();
		u.setFather(father);
		
		// serialization
		String json = gson.toJson(u);
		System.out.println(json);
		
		// deserialization
		User u2 = gson.fromJson(json, User.class);
	}
	
	@Test
	public void testObjectExclusiveField() {
		System.out.println("Object exclusive field Test:");
		Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).create();
		
		User u = new User();
		User father = new User();
//		u.setFather(father);
		
		// serialization
		String json = gson.toJson(u);
		System.out.println(json);
		
		// deserialization
		User u2 = gson.fromJson(json, User.class);
//		Assert.assertEquals("helloworld", u2.getPassword());
	}
	
	@Test
	public void testInnerClass() {
		System.out.println("Inner Class Test:");
		Gson gson = new Gson();
		
		Book book = new Book();
		
		// serialization
		String json = gson.toJson(book);
		System.out.println(json);
		
		Gson gson2 = new GsonBuilder().disableInnerClassSerialization().create();
		json = gson2.toJson(book);
		System.out.println(json);
		
		// deserialization
		Book book2 = gson.fromJson(json, Book.class);
		System.out.println(gson.toJson(book2));
		System.out.println(gson.toJson(book2.getAuthor()));
	}
	
	@Test
	public void testCollections() {
		System.out.println("Collection Test:");
		Gson gson = new Gson();
		
		int[] values = {1};
		System.out.println(gson.toJson(values));

		System.out.println(gson.toJson(gson.fromJson("[\"abc\"]", String[].class)));
		
		Collection<Integer> ints = new ArrayList();
		ints.add(1);
		ints.add(2);
		ints.add(3);
		System.out.println(gson.toJson(ints));
		
		Collection<String> strs = new ArrayList();
		strs.add("a");
		strs.add("b");
		strs.add("c");
		System.out.println(gson.toJson(strs));
		
		Collection<User> users = new ArrayList();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		
		String usersJson = gson.toJson(users);
		System.out.println(usersJson);
		
		Collection<User> users2 = gson.fromJson(usersJson, Collection.class);
		Assert.assertEquals(3, users.size());
		System.out.println(gson.toJson(users2));
	}
	
	@Test
	public void testGenericType() {
		System.out.println("Generic Test:");
		Gson gson = new Gson();
		
		Collection<User> users = new ArrayList();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		
		Type type = new TypeToken<Collection<User>>(){}.getType();
		System.out.println(type.toString());
		
		String usersJson = gson.toJson(users, type);
		System.out.println(usersJson);
		
		Collection<User> users2 = gson.fromJson(usersJson, type);
		Assert.assertEquals(3, users.size());
		System.out.println(gson.toJson(users2));
	}
	
	@Test
	public void testCustomSerializer() {
		System.out.println("Customer Serializer Test:");
		Gson gson = new Gson();
		
		Book book = new Book();
		book.publishDate = new Date();
		
		String usersJson = gson.toJson(book);
		System.out.println(usersJson);
		
		Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		usersJson = gson2.toJson(book);
		System.out.println(usersJson);
	}
	
	@Test
	public void testCustomDeserializer() {
		System.out.println("Customer Deserializer Test:");
		
		// deserialization
		String json = new String("{'name':'Linus Yan','password':'helloworld','email':'linus.yan@hotmail.com', 'father':{'name':'Linus'}}");
		
		Gson gson = new GsonBuilder().registerTypeAdapter(User.class, new UserDeserializer()).create();
		User u = gson.fromJson(json, User.class);
		System.out.println(u.getUsername());
		
		System.out.println(gson.toJson(u));
	}
}
