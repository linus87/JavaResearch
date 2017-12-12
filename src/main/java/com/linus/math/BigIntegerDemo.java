package com.linus.math;

import java.math.BigInteger;

public class BigIntegerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testEmbeded();
		testArithematic();
	}
	
	public static void testEmbeded() {
		System.out.println(BigInteger.ONE);
		System.out.println(BigInteger.TEN);
		System.out.println(BigInteger.ZERO);
		
		System.out.println("----------------------------------------Separator-----------------------------------------");
	}
	
	public static void testArithematic() {
		BigInteger a = new BigInteger("1");
		BigInteger b = new BigInteger("3");
		System.out.println(a.divide(b));
		
		System.out.println("----------------------------------------Separator-----------------------------------------");
	}

}
