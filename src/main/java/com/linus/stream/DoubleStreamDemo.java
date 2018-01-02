package com.linus.stream;

import java.util.Arrays;
import java.util.List;

public class DoubleStreamDemo {

	public static void main(String[] args) {
		Double[] elements = {10D, 11.3D, null, 14D};
		List<Double> list = Arrays.asList(elements);
		System.out.println(list.stream().mapToDouble(item -> item != null ? item : 0).sum());
	}

}
