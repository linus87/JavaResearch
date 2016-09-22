package com.linus.test.collection.array;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.derby.iapi.services.io.ArrayUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ArrayTest {
	private static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws IOException {
		String[] array = {"hello", "world"};
		
		System.out.println(array);
		System.out.println(String.valueOf(array));
		
		ObjectOutputStream oos = new ObjectOutputStream(System.out);
		ArrayUtil.writeArray(oos, array);
		
		System.out.println();		
		System.out.println(mapper.writeValueAsString(array));
	}

}

