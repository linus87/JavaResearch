package com.linus.test.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

public class ConcurrentMapTest {
	private static int MAX_SIZE = 1000;

	@Test
	public void givenHashMap_whenSumParallel_thenError() throws Exception {
		Map<String, Integer> map = new HashMap<>();
		List<Integer> sumList = parallelSum100(map, 100);
		Assert.assertNotSame(1, sumList.stream().distinct().count());
		long wrongResultCount = sumList.stream().filter(num -> num != 100).count();

		System.out.println("Wrong result count: " + wrongResultCount);
		Assert.assertTrue(wrongResultCount > 0);
	}

	/**
	 * Actions in a thread prior to placing an object into a ConcurrentMap as a key
	 * or value happen-before actions subsequent to the access or removal of that
	 * object in another thread.
	 * 
	 * @throws Exception
	 */
	@Test
	public void givenConcurrentMap_whenSumParallel_thenCorrect() throws Exception {
		Map<String, Integer> map = new ConcurrentHashMap<>();
		List<Integer> sumList = parallelSum100(map, 1000);

		Assert.assertNotSame(1, sumList.stream().distinct().count());
		long wrongResultCount = sumList.stream().filter(num -> num != 100).count();

		Assert.assertEquals(0, wrongResultCount);
	}
	
	private List<Integer> parallelSum100(Map<String, Integer> map, int executionTimes) throws InterruptedException {
		List<Integer> sumList = new ArrayList<>(1000);
		for (int i = 0; i < executionTimes; i++) {
			map.put("test", 0);
			ExecutorService executorService = Executors.newFixedThreadPool(4);
			for (int j = 0; j < 10; j++) {
				executorService.execute(() -> {
					for (int k = 0; k < 10; k++)
						map.computeIfPresent("test", (key, value) -> value + 1);
				});
			}
			executorService.shutdown();
			executorService.awaitTermination(5, TimeUnit.SECONDS);
			sumList.add(map.get("test"));
		}
		return sumList;
	}

	/**
	 * Most APIs provided by ConcurrentMap does not allow null key or value
	 */
	@Test(expected = NullPointerException.class)
	public void givenConcurrentHashMap_whenPutWithNullKey_thenThrowsNPE() {
		Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
		concurrentMap.put(null, new Object());
	}

	/**
	 * Most APIs provided by ConcurrentMap does not allow null key or value
	 */
	@Test(expected = NullPointerException.class)
	public void givenConcurrentHashMap_whenPutNullValue_thenThrowsNPE() {
		Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
		concurrentMap.put("test", null);
	}

	/**
	 * for compute* and merge actions, the computed value can be null, which
	 * indicates the key-value mapping is removed if present or remains absent if
	 * previously absent.
	 */
	@Test
	public void givenKeyPresent_whenComputeRemappingNull_thenMappingRemoved() {
		Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
		Object oldValue = new Object();
		concurrentMap.put("test", oldValue);
		concurrentMap.compute("test", (s, o) -> null);

		Assert.assertNull(concurrentMap.get("test"));
	}

	@Test
	public void givenMaps_whenGetPut500KTimes_thenConcurrentMapFaster() throws Exception {
		Map<String, Object> hashtable = new Hashtable<>();
		Map<String, Object> synchronizedHashMap = Collections.synchronizedMap(new HashMap<>());
		Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

		long hashtableAvgRuntime = timeElapseForGetPut(hashtable);
		long syncHashMapAvgRuntime = timeElapseForGetPut(synchronizedHashMap);
		long concurrentHashMapAvgRuntime = timeElapseForGetPut(concurrentHashMap);

		System.out.println("HashTable average run time:" + hashtableAvgRuntime);
		System.out.println("Sync HashMap average run time:" + syncHashMapAvgRuntime);
		System.out.println("ConcurrentHashMap average run time:" + concurrentHashMapAvgRuntime);
		Assert.assertTrue(hashtableAvgRuntime > concurrentHashMapAvgRuntime);
		Assert.assertTrue(syncHashMapAvgRuntime > concurrentHashMapAvgRuntime);
	}

	private long timeElapseForGetPut(Map<String, Object> map) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		long startTime = System.nanoTime();
		for (int i = 0; i < 4; i++) {
			executorService.execute(() -> {
				for (int j = 0; j < 500_000; j++) {
					int value = ThreadLocalRandom.current().nextInt(10000);
					String key = String.valueOf(value);
					map.put(key, value);
					map.get(key);
				}
			});
		}
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);
		return (System.nanoTime() - startTime) / 500_000;
	}
	
	/**
	 * esults of aggregate status methods including size, isEmpty, and containsValue are typically useful only when a map is not undergoing concurrent updates in other threads.
	 * @throws InterruptedException
	 */
	@Test
	public void givenConcurrentMap_whenUpdatingAndGetSize_thenError() 
	  throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		Map<String, Object> concurrentMap = new ConcurrentHashMap<>();
		List<Integer> mapSizes = new ArrayList<Integer>(MAX_SIZE);
		
	    Runnable collectMapSizes = () -> {
	        for (int i = 0; i < MAX_SIZE; i++) {
	            mapSizes.add(concurrentMap.size());
	        }
	    };
	    Runnable updateMapData = () -> {
	        for (int i = 0; i < MAX_SIZE; i++) {
	            concurrentMap.put(String.valueOf(i), i);
	        }
	    };
	    executorService.execute(updateMapData);
	    executorService.execute(collectMapSizes);
	    executorService.shutdown();
	    executorService.awaitTermination(1, TimeUnit.MINUTES);
	 
	    Assert.assertNotSame(MAX_SIZE, mapSizes.get(MAX_SIZE - 1).intValue());
	    Assert.assertEquals(MAX_SIZE, concurrentMap.size());
	}
	
}
