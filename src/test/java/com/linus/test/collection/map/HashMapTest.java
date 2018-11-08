package com.linus.test.collection.map;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.linus.concurrent.ConcurrentUtils;

public class HashMapTest {
	Set<String> set = new HashSet<String>();
	
	/**
	 * If we don't use synchronized to check, you may get several "remove". It means
	 * there are several threads running between "!set.contains()" and "set.add()".
	 * @throws InterruptedException
	 */
	@Test
	public void test() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		Runnable readTask = () -> {
			if (!set.contains("foo")) {
				set.add("foo");
				ConcurrentUtils.sleep(1);
				set.remove("foo");
				System.out.println("remove");
			}
		};

		for (int i = 0; i < 20; i++) {
			executor.execute(readTask);
		}
		
		executor.awaitTermination(3, TimeUnit.SECONDS);
		executor.shutdown();
	}
	
	/**
	 * We use synchronized method to check if "bar" is doing something, you can't do it frequently.
	 * @throws InterruptedException
	 */
	@Test
	public void testSynchronize() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		Runnable readTask = () -> {
			if (toHandle("bar")) {
				ConcurrentUtils.sleep(1);
				set.remove("bar");
				System.out.println("remove");
			}
		};

		/**
		 * both read tasks have to wait the whole second until the write task has
		 * finished.
		 **/
		for (int i = 0; i < 10; i++) {
			executor.execute(readTask);
		}
		executor.awaitTermination(3, TimeUnit.SECONDS);
		executor.shutdown();
	}
	
	public synchronized boolean toHandle(String key) {
		boolean flag = set.contains(key);
		if (flag == false) {
			set.add(key);
		}
		return !flag;
	}

}
