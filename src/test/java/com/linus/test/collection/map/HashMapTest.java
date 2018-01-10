package com.linus.test.collection.map;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HashMapTest {

	@Test
	public void main() {
		Map<Object, Object> existedValues = new HashMap<Object, Object>();
		existedValues.put(new String("hello"), null);
		
		if (existedValues.containsKey("hello")) {
			System.out.println("World");
		}
	}
	
	@Test
	public void testPutMethod() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("hello", null);
		map.put("hello", "world");
		
		System.out.println(map.get("hello"));
	}

}
