package com.linus.test.collection.array;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ArrayTest {
	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void main() throws IOException {
		String[] array = {"hello", "world"};
		
		System.out.println(array);
		System.out.println(String.valueOf(array));
		
		ObjectOutputStream oos = new ObjectOutputStream(System.out);
		oos.writeObject(array);
		
		System.out.println();		
		System.out.println(mapper.writeValueAsString(array));
	}

}

