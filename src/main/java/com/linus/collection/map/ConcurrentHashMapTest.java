package com.linus.collection.map;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
	public static void main(String[] args) {
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>(
				16);
		Thread addRunnable = new MapAddThread(map);
		Thread clearRunnable = new MapClearThread(map);
		Thread readRunnable = new MapReadThread(map);

		addRunnable.start();
		readRunnable.start();
//		clearRunnable.start();
	}
}

class MapAddThread extends Thread {
	Map map = null;

	public MapAddThread(Map map) {
		this.map = map;
	}

	@Override
	public void run() {
		System.out.println("Map add thread");
		
		for (int i = 0; i < 32; i++) {
			map.put(i, i);
			System.out.println("add:" + i);
			try {
				this.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

class MapClearThread extends Thread {
	Map map = null;

	public MapClearThread(Map map) {
		this.map = map;
	}

	@Override
	public void run() {
		System.out.println("Map clear thread");
		
		for (int i = 0; i < 32; i++) {
			System.out.println("clear");
			map.clear();
		}

	}

}

class MapReadThread extends Thread {
	Map map = null;

	public MapReadThread(Map map) {
		this.map = map;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Map read thread");
			Iterator iter = map.keySet().iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				System.out.println(key + ": " + map.get(key));

				try {
					this.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (map.keySet().size() == 32) {
				break;
			}
		}
		
	}

}