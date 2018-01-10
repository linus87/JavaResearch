package com.linus.test.date;

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
	}
}
