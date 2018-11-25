package com.linus.test.format;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class DateFormatTest {
	
	@Test
	public void dateFormat() {
		DateFormat chinaFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);
		DateFormat usFormat = DateFormat.getDateInstance(DateFormat.FULL, Locale.US);
		
		Date now = new Date();
		
		System.out.println("***************Date format***************");
		System.out.println("Chinese: " + chinaFormat.format(now));
		System.out.println("American: " + usFormat.format(now));
	}
	
	@Test
	public void dateTimeFormat() {
		DateFormat chinaFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
		DateFormat usFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.US);
		
		Date now = new Date();
		
		System.out.println("***************DateTime format***************");
		System.out.println("Chinese: " + chinaFormat.format(now));
		System.out.println("American: " + usFormat.format(now));
	}
	
	@Test
	public void timeFormat() {
		DateFormat chinaFormat = DateFormat.getTimeInstance(DateFormat.FULL, Locale.CHINA);
		DateFormat usFormat = DateFormat.getTimeInstance(DateFormat.FULL, Locale.US);
		
		Date now = new Date();
		
		System.out.println("***************Time format***************");
		System.out.println("Chinese: " + chinaFormat.format(now));
		System.out.println("American: " + usFormat.format(now));
	}

}
