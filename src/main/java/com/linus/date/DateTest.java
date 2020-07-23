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
		
		testTimeZoneWithTime();
		
		testTimeZone();
		
		testCalendar();
	}
	
	public static void testGMTTime() {
		System.out.println("***************************** GMT Time **************************************");
		Date d = new Date();
		System.out.println("GMT    Time: " + d.toGMTString());
		System.out.println("Locale Time: " + d.toLocaleString());
		System.out.println("To String  : " + d.toString());
	}
	
	public static void testParse() throws ParseException {
		System.out.println("***************************** Simple Date Format **************************************");
		String dateStr = "2016-06-02T10:13:29.902Z";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		Date date = dateFormat.parse(dateStr);
		System.out.println(date);		
	}
	
	public static void testCompage() throws ParseException {
		System.out.println("***************************** Compare Date **************************************");
		String dateStr = "2016-06-02T10:13:29.902Z";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
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
	
	/**
	 * It date is given in time(milliseconds), it means zero-time zone's time.
	 * When Date object is created by this time, its string representation will be converted to local system time, but time value is not changed.
	 * @throws ParseException
	 */
	public static void testTimeZoneWithTime() throws ParseException {
		System.out.println("***************************** Timezone with timestamp Test **************************************");
		Long time = 0l;
		Date date = new Date(time);
		System.out.println("Local date  : " + date);
		System.out.println("Milliseconds: " + date.getTime());
	}
	
	/**
	 * If date is given in string, it means local system date. But its time(milliseconds) is still zero-time zone's time.
	 * For example, 1970-01-01T08:00:00.902Z, its time value is 902 if it's calculated in a server located in BeiJing.
	 * @throws ParseException
	 */
	public static void testTimeZone() throws ParseException {
		System.out.println("***************************** Timezone with string date Test **************************************");
		String dateStr = "1970-01-01T08:00:00.902Z";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date = dateFormat.parse(dateStr);
		System.out.println(date);
		System.out.println(date.getTime());
		
		TimeZone GMT_TIMEZONE = TimeZone.getTimeZone("GMT+8:00");
		dateFormat.setTimeZone(GMT_TIMEZONE);
		date = dateFormat.parse(dateStr);
		System.out.println(date);
		System.out.println(date.getTimezoneOffset());
		
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
