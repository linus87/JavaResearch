package com.linus.test.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import junit.framework.Assert;

public class DateTest {
	
	@Test
	public void testDate() {
		System.out.println(new Date().toLocaleString());
	}

	@Test
	public void testDateCompare() {
		Date a = new Date();
		
		Date b = new Date(a.getTime());
		
		Assert.assertEquals(a, b);
		Assert.assertTrue(a.compareTo(b) == 0);;
		
		// Can't compare Dates with == operator.
		Assert.assertFalse(a == b);
	}
	
	@Test
	public void testUTCDate() throws ParseException {
		System.out.println("***************************** Simple Date Format **************************************");
		String dateStr = "2016-06-02T10:13:29.902";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		
		Date date = dateFormat.parse(dateStr);
		System.out.println(date);
	}
	
	@Test
	public void testGMTDate() throws ParseException {
		System.out.println("***************************** GMT Zero **************************************");
		Date zero = new Date(0);
		System.out.println(zero);
		
		String dateStr = "1970-01-01T00:00:00.000";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		
		Date date = dateFormat.parse(dateStr);
		System.out.println(date);
	}
}
