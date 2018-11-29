package com.linus.test.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import com.linus.date.DateUtil;

public class DateUtilTest {
	DateFormat dateFormat = null;
	TimeZone systemTimeZone = TimeZone.getTimeZone("America/Los_Angeles");
	
	@Before
	public void before() {
		// pretend running in Los Angeles
		TimeZone.setDefault(systemTimeZone);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Test
	public void pstToCTT () throws ParseException {
		TimeZone pstZone = TimeZone.getTimeZone("America/Los_Angeles");
		TimeZone cttZone = TimeZone.getTimeZone("Asia/Shanghai");
		
		System.out.println("=======================PST To CTT=====================");
		System.out.println("Is PST Using Daylight Saving Time:" + pstZone.useDaylightTime());
		System.out.println("Is CTT Using Daylight Saving Time:" + cttZone.useDaylightTime());
		
		// 16 hours gap
		Date now = dateFormat.parse("2018-11-29 01:32:00");
		Date newDate = DateUtil.convert(now, pstZone, cttZone);
		print(now, newDate);
		
		// 15 hours gap
		now = dateFormat.parse("2018-08-01 01:32:00");
		newDate = DateUtil.convert(now, pstZone, cttZone);
		
		print(now, newDate);
	}
	
	@Test
	public void cttToPST () throws ParseException {
		TimeZone pstZone = TimeZone.getTimeZone("America/Los_Angeles");
		TimeZone cttZone = TimeZone.getTimeZone("Asia/Shanghai");
		
		System.out.println("=======================CTT To PST=====================");
		System.out.println("Is PST Using Daylight Saving Time:" + pstZone.useDaylightTime());
		System.out.println("Is CTT Using Daylight Saving Time:" + cttZone.useDaylightTime());
		
		Date now = dateFormat.parse("2018-11-29 17:32:00");
		Date newDate = DateUtil.convert(now, cttZone, pstZone);
		print(now, newDate);
		
		now = dateFormat.parse("2018-08-01 16:32:00");
		newDate = DateUtil.convert(now, cttZone, pstZone);
		
		print(now, newDate);
	}
	
	@Test
	public void gmtToCtt () throws ParseException {
		TimeZone gmtZone = TimeZone.getTimeZone("GMT");
		TimeZone cttZone = TimeZone.getTimeZone("Asia/Shanghai");
		System.out.println("=======================GMT To CTT=====================");
		
		Date now = dateFormat.parse("2018-11-29 01:32:00");
		
		Date newDate = DateUtil.convert(now, gmtZone, cttZone);
		print(now, newDate);
	}
	
	@Test
	public void gmtToPST () throws ParseException {
		TimeZone gmtZone = TimeZone.getTimeZone("GMT");
		TimeZone pstZone = TimeZone.getTimeZone("America/Los_Angeles");
		System.out.println("=======================GMT To PST=====================");
		
		Date now = dateFormat.parse("2018-11-29 17:32:00");
		
		Date newDate = DateUtil.convert(now, gmtZone, pstZone);
		print(now, newDate);
	}
	
	private void print(Date now, Date newDate) {
		System.out.println("now:" + now);
		System.out.println("new:" + newDate);
	}
	
}
