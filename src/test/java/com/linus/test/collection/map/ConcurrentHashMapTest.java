package com.linus.test.collection.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ConcurrentHashMapTest {
	ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

	@Test
	public void test() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

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
	
	@Test
	public void testSynchronize() {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		Runnable readTask = () -> {
			if (!isDoing("foo")) {
				System.out.println("hello world");
//				ConcurrentUtils.sleep(2);
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
	
	private synchronized boolean isDoing(String key) {
		boolean flag = map.containsKey(key);
		if (!flag) {
			map.put(key, "bar");
		}
		return flag;
	}
}
