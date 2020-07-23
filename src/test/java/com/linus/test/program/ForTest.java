package com.linus.test.program;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ForTest {

	@Test
	public void testForStatement() {
		String[] array = {"hello", "world"};
		
		for (String value : array) {
			System.out.println(value);
			if ("hello".equalsIgnoreCase(value)) {
				break;
			}
		}
	}
	
	@Test
	public void testForEachFunction() {
		List<String> array = new ArrayList() {{this.add("Hello"); this.add("World");}};
		
		array.forEach((value) -> {
			if ("hello".equalsIgnoreCase(value)) {
//				break;
			}
		});
		
	}
}
