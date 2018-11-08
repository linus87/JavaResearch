package com.linus.test.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

public class DateTest {

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
}
