package com.linus.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalDemo {

	public static void main(String[] args) {
		testString();
		testLong();
		testFloat();
		testDouble();
		
		testScaleAndRounding();
		testRoundingMode();
		testExcatMathContext();
		testInexcatMathContext();
	}
	
	public static void testString() {
		System.out.println("----------------------------------------String-----------------------------------------");
		BigDecimal a = new BigDecimal("0.75");
		System.out.println(a.toString());
		
		BigDecimal b = new BigDecimal(3.18);
		System.out.println(b.toString());
		
		BigDecimal c = a.multiply(b);
		System.out.println(c.toString());
		
		BigDecimal d = new BigDecimal("3.18");
		System.out.println(d.toString());
		
		BigDecimal e = a.multiply(d);
		System.out.println(e.toString());
		
		System.out.println("----------------------------------------Separator-----------------------------------------");
	}
	
	public static void testLong() {
		System.out.println("----------------------------------------Long-----------------------------------------");
		BigDecimal a = new BigDecimal("0.751783");
		System.out.println(a.toString());
		
		BigDecimal b = new BigDecimal(213L);
		System.out.println(b.toString());
		
		BigDecimal c = a.multiply(b);
		System.out.println(c.toString());
		System.out.println("----------------------------------------Separator-----------------------------------------");
	}
	
	public static void testFloat() {
		System.out.println("----------------------------------------Float-----------------------------------------");
		System.out.println(3.14159265F);
		BigDecimal a = new BigDecimal(3.14159265F);
		System.out.println(a.toString());
		
		BigDecimal b = new BigDecimal(3.14159265F, new MathContext(0, RoundingMode.HALF_DOWN));
		System.out.println(b.toString());
		
		System.out.println("----------------------------------------Separator-----------------------------------------");
	}
	
	public static void testDouble() {
		System.out.println("----------------------------------------Double-----------------------------------------");
		System.out.println(3.14159265D);
		BigDecimal a = new BigDecimal(3.14159265D);
		System.out.println(a.toString());
		
		BigDecimal c = new BigDecimal(100.34);
		System.out.println(c.toString());
		
		System.out.println("----------------------------------------Separator-----------------------------------------");
	}
	
	public static void testScaleAndRounding() {
		System.out.println("----------------------------------------Scale and Rounding Mode-----------------------------------------");
		BigDecimal a = new BigDecimal(3.14159265D);
		System.out.println(a.toString());
		
		BigDecimal b = a.setScale(4, RoundingMode.HALF_UP);
		System.out.println(b.precision());
		System.out.println(b.toString());
		
		BigDecimal c = new BigDecimal(3.14159265D, new MathContext(6, RoundingMode.HALF_DOWN));
		System.out.println(c.precision());
		System.out.println(c.toString());
		System.out.println("----------------------------------------Separator-----------------------------------------");
	}
	
	public static void testRoundingMode() {
		System.out.println("----------------------------------------Rounding Mode-----------------------------------------");
		BigDecimal a = new BigDecimal(5);
		BigDecimal b = new BigDecimal(4);
		System.out.println("RoundingMode Ceiling: " + a.divide(b, RoundingMode.CEILING).toString());
		System.out.println("RoundingMode Floor: " + a.divide(b, RoundingMode.FLOOR).toString());
		System.out.println("RoundingMode Up: " + a.divide(b, RoundingMode.UP).toString());
		System.out.println("RoundingMode Down: " + a.divide(b, RoundingMode.DOWN).toString());
		System.out.println("RoundingMode Half Up: " + a.divide(b, RoundingMode.HALF_UP).toString());
		System.out.println("RoundingMode Half Down: " + a.divide(b, RoundingMode.HALF_DOWN).toString());
		System.out.println("RoundingMode Half Even: " + a.divide(b, RoundingMode.HALF_EVEN).toString());
		
		try {
			System.out.println("RoundingMode Unncessary: " + a.divide(b, RoundingMode.UNNECESSARY).toString());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			System.out.println("RoundingMode Unncessary: " + a.divide(b, new MathContext(0, RoundingMode.UNNECESSARY)).toString());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		System.out.println("----------------------------------------Separator-----------------------------------------");
	}
	
	public static void testExcatMathContext() {
		System.out.println("----------------------------------------Exact Math Context-----------------------------------------");
		BigDecimal a = new BigDecimal(7);
		BigDecimal b = new BigDecimal(4);
		System.out.println("MathContext DECIMAL128: " + a.divide(b, MathContext.DECIMAL128).toString());
		System.out.println("MathContext DECIMAL32: " + a.divide(b, MathContext.DECIMAL32).toString());
		System.out.println("MathContext DECIMAL64: " + a.divide(b, MathContext.DECIMAL64).toString());
		
		System.out.println("MathContext Default: " + a.divide(b).toString());
		
		System.out.println("MathContext UNLIMITED: " + a.divide(b, MathContext.UNLIMITED).toString());
		
		System.out.println("RoundingMode Unncessary: " + a.divide(b, new MathContext(0, RoundingMode.UNNECESSARY)).toString());
		
		System.out.println("----------------------------------------Separator-----------------------------------------");
	}
	
	public static void testInexcatMathContext() {
		System.out.println("----------------------------------------Inexact Math Context -----------------------------------------");
		BigDecimal a = new BigDecimal(5);
		BigDecimal b = new BigDecimal(3);
		System.out.println("MathContext DECIMAL128: " + a.divide(b, MathContext.DECIMAL128).toString());
		System.out.println("MathContext DECIMAL32: " + a.divide(b, MathContext.DECIMAL32).toString());
		System.out.println("MathContext DECIMAL64: " + a.divide(b, MathContext.DECIMAL64).toString());
		
		try {
			System.out.println("MathContext Default: " + a.divide(b).toString());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			System.out.println("MathContext UNLIMITED: " + a.divide(b, MathContext.UNLIMITED).toString());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			System.out.println("RoundingMode Unncessary: " + a.divide(b, new MathContext(0, RoundingMode.UNNECESSARY)).toString());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			System.out.println("MathContext Custom : " + a.divide(b, new MathContext(3, RoundingMode.HALF_UP)).toString());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		System.out.println("----------------------------------------Separator-----------------------------------------");
	}
	
	

}
