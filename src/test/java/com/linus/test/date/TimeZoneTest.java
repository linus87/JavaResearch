package com.linus.test.date;

import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class TimeZoneTest {
	
	/**
	 * 默认取的是系统时区。
	 */
	@Test
	public void defaultTimeZone() {
		TimeZone defaultZone = TimeZone.getDefault();
		
		System.out.println("=======================Default Zone=====================");
		System.out.println("Current timezone: " + defaultZone.getDisplayName());
		System.out.println("Current timezone raw offset: " + defaultZone.getRawOffset());
		System.out.println("defaultZone DST: " + defaultZone.getDSTSavings());
	}

	/**
	 * 都在美国，但MST没有Daily Saving Time。
	 */
	@Test
	public void timeZoneCompare() {
		TimeZone pstZone = TimeZone.getTimeZone("PST");
		TimeZone mstZone = TimeZone.getTimeZone("MST");
		TimeZone phoenixZone = TimeZone.getTimeZone("America/Phoenix");
		
		Assert.assertNotSame(mstZone, phoenixZone);
		System.out.println("=======================Same Zone Compare=====================");
		System.out.println("MST raw offset: " + mstZone.getRawOffset());
		System.out.println("Phoenix raw offset: " + phoenixZone.getRawOffset());
		System.out.println("MST DST: " + mstZone.getDSTSavings());
		System.out.println("Phoenix DST: " + phoenixZone.getDSTSavings());
		
		System.out.println("=======================TimeZone Compare=====================");
		System.out.println("PST raw offset: " + pstZone.getRawOffset());
		System.out.println("MST raw offset: " + mstZone.getRawOffset());
		System.out.println("PST DST: " + pstZone.getDSTSavings());
		System.out.println("MST DST: " + mstZone.getDSTSavings());
		
		Assert.assertNotSame(pstZone.getDSTSavings(), mstZone.getDSTSavings());
		Assert.assertNotSame(pstZone, mstZone);
	}
}
