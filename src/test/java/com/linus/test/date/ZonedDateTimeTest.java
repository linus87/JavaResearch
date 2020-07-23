package com.linus.test.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Test;

public class ZonedDateTimeTest {
	
	@Test
	public void testNow() {
		ZonedDateTime now = ZonedDateTime.now();
		System.out.println(now.toString());
		
		System.out.println(now.getZone().getId());
		
		LocalDateTime localNow = LocalDateTime.now();
		System.out.println(localNow.toString());
		
	}
	
	@Test
	public void testInstant() {
		Date date = new Date(0);
		Instant instant  = date.toInstant();
		System.out.println(instant.toString());
		System.out.println(Instant.now().toString());
	}
	
	@Test
	public void testZonedDateTimeWithDate() {
		Date date = new Date(0);
		ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC+8"));
		
		System.out.println(zonedDateTime.toString());
		
		// Used the epoch millisecond
		System.out.println((Date.from(zonedDateTime.toInstant())).toString());
		System.out.println((Date.from(zonedDateTime.toInstant())).getTime());
		System.out.println(zonedDateTime.toInstant().toEpochMilli());
	}
}
