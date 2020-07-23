package com.linus.test.date;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class CalendarTest {
	
	/**
	 * /* Date represents a specific instant in time UTC, but always shown as the local time zone time.
	 */
	@Test
	public void dateAndCalendar() {
		Calendar d = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		d.clear();
		d.set(2015, 0, 3, 0, 0,  0);
		
		System.out.println(d.getTime());
	}
	
	/**
	 * CTT means Asia/Shanghai, CST means America/Chicago.
	 * @see java.time.ZoneId
	 */
	@Test
	public void timeZone() {
		
		Calendar d1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		d1.set(2015, 0, 3, 0, 0,  0);
		
		System.out.println(d1.getTime());
		
		/* America/Chicago */
		Calendar d2 = Calendar.getInstance(TimeZone.getTimeZone("CST"));
		d2.set(2015, 0, 3, 0, 0,  0);
		
		System.out.println(d2.getTime());
		
		/* Asia/Shanghai */
		Calendar d3 = Calendar.getInstance(TimeZone.getTimeZone("CTT"));
		d3.set(2015, 0, 3, 0, 0,  0);
		System.out.println(d3.getTime());
		
		/* Asia/Shanghai */
		Calendar d4 = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
		d4.set(2015, 0, 3, 0, 0,  0);
		
		System.out.println(d4.getTime());
		
		/* DON'T WORK */
		Calendar d5 = Calendar.getInstance(TimeZone.getTimeZone("UTC+8"));
		d5.set(2015, 0, 3, 0, 0,  0);
		
		System.out.println(d5.getTime());
	}
	
	/**
	 * /* Date represents a specific instant in time UTC, but always shown as the local time zone time.
	 */
	@Test
	public void calendarDifferentTimeZoneCompare() {
		Calendar d1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		d1.set(2015, 0, 3, 0, 0,  0);
		
		System.out.println(d1.getTime());
		
		Calendar d2 = Calendar.getInstance(TimeZone.getTimeZone("CTT"));
		d2.set(2015, 0, 3, 0, 0,  0);
		
		System.out.println(d2.getTime());
		
		Assert.assertTrue(d1.after(d2));
	}

	@Test
	public void showCalendar() {
		Calendar d = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		d.clear();
		d.set(2015, 0, 3, 0, 0,  0);
		
		/* Date represents a specific instant in time UTC, but always shown as the local time zone time */
		System.out.println(d.getTime());

		
		System.out.println(d.toString());
		System.out.println("Month: " + d.get(Calendar.MONTH)); // 1- 31
		System.out.println("Day of year: " + d.get(Calendar.DAY_OF_YEAR)); // 1- 31
		System.out.println("Day of month: " + d.get(Calendar.DAY_OF_MONTH)); // 1- 31
		System.out.println("Day of week: " + d.get(Calendar.DAY_OF_WEEK));  // 1 - 7
		System.out.println("Day of week in month: " + d.get(Calendar.DAY_OF_WEEK_IN_MONTH)); // 0 -11
		System.out.println("Day of year: " + d.get(Calendar.DAY_OF_YEAR)); // 0 -11
		System.out.println("First day of week: " + d.getFirstDayOfWeek());
		System.out.println("Minimal days in first week: " + d.getMinimalDaysInFirstWeek());  // how many days are needed to be the first week of this year.
		System.out.println("Date Actual Maximum:" + d.getActualMaximum(Calendar.DATE));
		System.out.println("Date actual minimum: " + d.getActualMinimum(Calendar.DATE));
		System.out.println("Date greatest minimum: " + d.getGreatestMinimum(Calendar.DATE));
		System.out.println("Day of week: " + d.getGreatestMinimum(Calendar.DAY_OF_WEEK));
	}
	
	@Test
	public void testCalendarAddMethod() {
		Calendar d = Calendar.getInstance();
		d.set(2019, 0, 18);
//		d.setTime(null);
		d.add(Calendar.MONTH, -1);
		d.set(Calendar.DATE, d.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		System.out.println(d.toString());
		System.out.println("Year: " + d.get(Calendar.YEAR)); // 1- 31
		System.out.println("Month: " + d.get(Calendar.MONTH)); // 1- 31
		System.out.println("Day of year: " + d.get(Calendar.DAY_OF_YEAR)); // 1- 31
		System.out.println("Day of month: " + d.get(Calendar.DAY_OF_MONTH)); // 1- 31
		System.out.println("Day of week: " + d.get(Calendar.DAY_OF_WEEK));  // 1 - 7
		System.out.println("Day of week in month: " + d.get(Calendar.DAY_OF_WEEK_IN_MONTH)); // 0 -11
		System.out.println("Day of year: " + d.get(Calendar.DAY_OF_YEAR)); // 0 -11
		System.out.println("First day of week: " + d.getFirstDayOfWeek());
		System.out.println("Minimal days in first week: " + d.getMinimalDaysInFirstWeek());  // how many days are needed to be the first week of this year.
		System.out.println("Date Actual Maximum:" + d.getActualMaximum(Calendar.DATE));
		System.out.println("Date actual minimum: " + d.getActualMinimum(Calendar.DATE));
		System.out.println("Date greatest minimum: " + d.getGreatestMinimum(Calendar.DATE));
		System.out.println("Day of week: " + d.getGreatestMinimum(Calendar.DAY_OF_WEEK));
	}
	
	@Test
	public void lastDate() {
		System.out.println(getStoreBalanceDate());
	}
	
	public Date getStoreBalanceDate() {
		Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DAY_OF_MONTH) > 25) {
			c.add(Calendar.MONTH, -1);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 24, 0, 0);
			c.set(Calendar.MILLISECOND, 0);
		} else {
			c.add(Calendar.MONTH, -2);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 24, 0, 0);
			c.set(Calendar.MILLISECOND, 0);
		}
		
		return c.getTime();
	}
}
