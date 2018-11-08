package com.linus.test.collection.map;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

public class HashTableTest {
	Hashtable<String, String> map = new Hashtable<String, String>();
	
	@Before
	public void beforeClass() {
		map.put("foo", "bar");
	}
	
	@Test
	public void test() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		Runnable readTask = () -> {
			if (map.containsKey("foo")) {
				System.out.println("hello world");
				map.remove("foo");
			}
		};

		for (int i = 0; i < 10; i++) {
			executor.execute(readTask);
		}
	}
}
