package com.linus.collection.spliterator;

import java.util.Arrays;
import java.util.List;

public class SpliteratorDemo {

	public static void main(String[] args) {
		Double[] elements = {10D, 11.3D, null, 14D};
		List<Double> list = Arrays.asList(elements);
		
		list.spliterator().tryAdvance(element -> {
			System.out.println(element.doubleValue());
		});
	}

}
