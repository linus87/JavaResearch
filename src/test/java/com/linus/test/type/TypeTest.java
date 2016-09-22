package com.linus.test.type;

import java.util.Date;

import org.junit.Test;

public class TypeTest {
	@Test
	public void test() {
		Object o = null;
		Double d = 35.23;
		double text = 35.23;
		method(o = d);
		method(o = text);
		method(o = new Date(1111111111));
	}
	
	public void method(Object o) {
		System.out.println("Method object: " + (o instanceof Double));
		System.out.println("Method object: " + (o instanceof Date));
		System.out.println("Method object: " + o);
	}
	
	public void method(Double o) {
		System.out.println("Method double: " + o);
	}
	
	
	public void method(String o) {
		System.out.println("Method string: " + o);
	}
	
	
	public void method(Date o) {
		System.out.println("Method date: " + o);
	}
	
	@Test
	public void twoDateCompare() {
		Date a = new Date(1111111111);
		Date b = new Date(1111111111);
		
		System.out.println("Two date compare: " + a.equals(b));
	}
	
	@Test
	public void twoDoubleCompare() {
		Double a = 12.34;
		double b = 12.34;
		
		System.out.println("Two double compare: " + a.equals(b));
	}
}
