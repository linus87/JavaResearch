package com.linus.test.format;

import java.text.NumberFormat;
import java.util.Locale;

import org.junit.Test;

public class NumberFormatTest {
	@Test
	public void currencyFormat() {
		NumberFormat chinaFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
		NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);
		
		System.out.println("Chinese: " + chinaFormat.format(100.45));
		System.out.println("American: " + usFormat.format(100.45));
	}
	
	@Test
	public void percentageFormat() {
		NumberFormat chinaFormat = NumberFormat.getPercentInstance(Locale.CHINA);
		NumberFormat usFormat = NumberFormat.getPercentInstance(Locale.US);
		
		System.out.println("***************Percentage format***************");
		System.out.println("Chinese: " + chinaFormat.format(0.4556));
		System.out.println("American: " + usFormat.format(0.4556));
	}
	
	@Test
	public void numberFormat() {
		NumberFormat chinaFormat = NumberFormat.getNumberInstance(Locale.CHINA);
		NumberFormat usFormat = NumberFormat.getNumberInstance(Locale.US);
		
		System.out.println("***************Number format***************");
		System.out.println("Chinese: " + chinaFormat.format(4328590.4556));
		System.out.println("American: " + usFormat.format(4328590.4556));
	}
	
	@Test
	public void integerFormat() {
		NumberFormat chinaFormat = NumberFormat.getIntegerInstance(Locale.CHINA);
		NumberFormat usFormat = NumberFormat.getIntegerInstance(Locale.US);
		
		System.out.println("***************Integer format***************");
		System.out.println("Chinese: " + chinaFormat.format(4328590.4556));
		System.out.println("American: " + usFormat.format(4328590.4556));
	}
	
	@Test
	public void defaultFormat() {
		NumberFormat chinaFormat = NumberFormat.getInstance(Locale.CHINA);
		NumberFormat usFormat = NumberFormat.getInstance(Locale.US);
		
		System.out.println("***************Default format***************");
		System.out.println("Chinese: " + chinaFormat.format(4328590.4556));
		System.out.println("American: " + usFormat.format(4328590.4556));
	}
}
