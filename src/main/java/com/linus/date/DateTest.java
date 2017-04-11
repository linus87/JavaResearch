package com.linus.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTest {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException  {
		// TODO Auto-generated method stub
		Calendar d = Calendar.getInstance();
		d.set(2015, 0, 3);
		
		d.setTime(null);
		
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
		
		double result = 1;
		System.out.println(result);
		DecimalFormat df = new DecimalFormat("#0%");
		df.setMaximumFractionDigits(0);
		System.out.println(df.format(result));
		
		testGMTTime();
		
		testParse();
//		
		testCompage();
		
		testTimestamp();
		
		testGMVDate();
		
		testTimeZone();
		
		testCalendar();
	}
	
	public static void testGMTTime() {
		System.out.println("***************************** GMT Time **************************************");
		Date d = new Date();
		System.out.println(d.toGMTString());
		System.out.println(d.toLocaleString());
		System.out.println(d.toString());
	}
	
	public static void testParse() throws ParseException {
		String dateStr = "2016-06-02T10:13:29.902Z";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
//		Date date = new Date(dateStr);
//		System.out.println(date);
		
		Date date = dateFormat.parse(dateStr);
		System.out.println(date);		
		
		System.out.println(date);	
	}
	
	public static void testCompage() throws ParseException {
		String dateStr = "2016-06-02T10:13:29.902Z";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		/*Date date = new Date(dateStr);
		System.out.println(date);*/
		System.out.println("Test time compare: ");
		
		Date date = dateFormat.parse(dateStr);
		System.out.println(date);
		
		Date now = new Date();
		System.out.println(now.compareTo(date));	
	}
	
	public static void testTimestamp() throws ParseException {
		String dateStr = "2016-06-02T10:13:29.902Z";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		System.out.println("Test time stamp: ");
		
		Date date = dateFormat.parse(dateStr);
		System.out.println(date);
		
		Timestamp time = new Timestamp(date.getTime());
		System.out.println(time);
		
		Date now = new Date();
		time = new Timestamp(now.getTime());
		System.out.println(time);
	}
	
	public static void testGMVDate() throws ParseException {
		String dateStr = "2016/6/02";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		Date date = dateFormat.parse(dateStr);
		System.out.println(date);

	}
	
	public static void testTimeZone() throws ParseException {
		String dateStr = "2016-06-02T10:13:29.902Z";
		TimeZone GMT_TIMEZONE = TimeZone.getTimeZone("GMT+9:00");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		System.out.println("Test time zone: ");
		
		Date date = dateFormat.parse(dateStr);
		System.out.println(date);
		System.out.println(date.getTimezoneOffset());
		System.out.println(dateFormat.format(date));
		
		dateFormat.setTimeZone(GMT_TIMEZONE);
		date = dateFormat.parse(dateStr);
		System.out.println(date);
		System.out.println(date.getTimezoneOffset());
		System.out.println(dateFormat.format(date));
		
		TimeZone UK_TIMEZONE = TimeZone.getTimeZone("GMT+0:00");
		Locale locale = Locale.UK;
//		Calendar c = Calendar.getInstance(UK_TIMEZONE, locale);
		Calendar c = Calendar.getInstance(locale);
		Date d = c.getTime();
		System.out.println(d.getTimezoneOffset());
		System.out.println(d);
		dateFormat.setTimeZone(c.getTimeZone());
		System.out.println(dateFormat.format(d));
		System.out.println(c.getTimeZone().getDisplayName());		
	}
	
	public static void testCalendar() throws ParseException {	
		System.out.println("\nTest calendar: ");
		DateFormat systemDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		TimeZone UK_TIMEZONE = TimeZone.getTimeZone("GMT+0:00");
		Locale locale = Locale.UK;
//		Calendar c = Calendar.getInstance(UK_TIMEZONE, locale);
		Calendar c = Calendar.getInstance(locale);
		Date d = c.getTime();
		System.out.println(d.getTimezoneOffset());
		System.out.println(d);
		System.out.println(c.getTimeZone().getDisplayName());
		
		Calendar c2 = Calendar.getInstance(UK_TIMEZONE);
		c2.setTime(new Date());
		System.out.println(c2.getTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dateFormat.setTimeZone(c2.getTimeZone());
		System.out.println(dateFormat.format(c2.getTime()));
		
		Date date = new Date();
		long gap = UK_TIMEZONE.getRawOffset() - TimeZone.getDefault().getRawOffset();
		date.setTime(date.getTime() + gap);
		System.out.println(date);
	}
	

}
