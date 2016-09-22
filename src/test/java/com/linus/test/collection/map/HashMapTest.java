package com.linus.test.collection.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	public static void main(String[] args) {
		Map<Object, Object> existedValues = new HashMap<Object, Object>();
		existedValues.put(new String("hello"), null);
		
		if (existedValues.containsKey("hello")) {
			System.out.println("World");
		}

	}

}
