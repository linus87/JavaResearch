package com.linus.test.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.linus.concurrent.ConcurrentUtils;

/**
 * After the write lock has been released both read tasks are executed in
 * parallel and print the result simultaneously to the console. They don't have
 * to wait for each other to finish because read-locks can safely be acquired
 * concurrently as long as no write-lock is held by another thread.
 * 
 * @author lyan2
 * @since 2018-10-09
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Map<String, String> map = new HashMap<>();
		ReadWriteLock lock = new ReentrantReadWriteLock();

		executor.submit(() -> {
			lock.writeLock().lock();
			try {
				ConcurrentUtils.sleep(1);
				map.put("foo", "bar");
			} finally {
				lock.writeLock().unlock();
			}
		});

		Runnable readTask = () -> {
			lock.readLock().lock();
			try {
				System.out.println(map.get("foo"));
				ConcurrentUtils.sleep(1);
			} finally {
				lock.readLock().unlock();
			}
		};

		/**
		 * both read tasks have to wait the whole second until the write task has
		 * finished.
		 **/
		executor.submit(readTask);
		executor.submit(readTask);

		ConcurrentUtils.stop(executor);
	}

}
