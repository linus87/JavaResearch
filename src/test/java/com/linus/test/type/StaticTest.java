package com.linus.test.type;

import org.junit.Test;

public class StaticTest {
	
	@Test
	public void testStaticField() {
		Demo d = new Demo();
		System.out.println(d.b);
		
		d.a = 8;
		System.out.println(d.b);
		System.out.println(d.getB());
	}
}

class Demo {
	public static int a = 3;
	public static int b = a + 4;
	
	public static int getB() {
		return a + 4;
	}
}
