package com.linus.test.collection.map;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class HashTableTest {
	@Test
	public void test() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Hashtable<String, String> map = new Hashtable<String, String>();

		Runnable readTask = () -> {
			if (!map.containsKey("foo")) {
				map.put("foo", "bar");
				System.out.println("hello world");
				map.remove("foo");
			}
		};

		/**
		 * both read tasks have to wait the whole second until the write task has
		 * finished.
		 **/
		for (int i = 0; i < 10; i++) {
			executor.execute(readTask);
		}
	}
}
