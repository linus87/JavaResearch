package com.linus.test.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class HashMapTest {
	HashMap<String, String> map = new HashMap<String, String>();
	
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
	
	@Test
	public void test() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		HashMap<String, String> map = new HashMap<String, String>();

		Runnable readTask = () -> {
			if (!map.containsKey("foo")) {
				map.put("foo", "bar");
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
	
	@Test
	public void testSynchronize() {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		Runnable readTask = () -> {
			if (!isDoing("foo")) {
				System.out.println("hello world");
//					ConcurrentUtils.sleep(2);
				map.remove("foo");
				System.out.println("after remove");
			} else {
				System.out.println("is doing");
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
	
	public synchronized boolean isDoing(String key) {
		boolean flag = map.containsKey(key);
		if (!flag) {
			map.put(key, "bar");
		}
		return flag;
	}

}
