package com.linus.test.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

import com.google.gson.JsonSyntaxException;

public class DateFormatTest {
	// below three date formatters are used in com.google.gson.internal.bind.DateTypeAdaptor
	private final DateFormat usFormat = DateFormat.getDateTimeInstance(
			DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US);
	private final DateFormat chinaFormat = DateFormat.getDateTimeInstance(
			DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
	
	private final DateFormat iso8601Format = buildIso8601Format();

	private static DateFormat buildIso8601Format() {
		DateFormat iso8601Format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		iso8601Format.setTimeZone(TimeZone.getTimeZone("GMT+2"));
		return iso8601Format;
	}
	
	@Test
	public void localeImpact() {
		Date now = new Date();
		System.out.println("Chinese time: " + chinaFormat.format(now));
		System.out.println("American time: " + usFormat.format(now));
	}

	@Test
	public void testDateTypeAdaptor() throws Exception {
		String json = "2018-02-14T12:49:46.000Z";

		try {
			System.out.println("Locale:" + chinaFormat.parse(json));
		} catch (ParseException ignored) {
		}
		
		try {
			System.out.println("US: " + usFormat.parse(json));
		} catch (ParseException ignored) {
		}
		
		try {
			System.out.println("ISO: " + iso8601Format.parse(json));
		} catch (ParseException e) {
			throw new JsonSyntaxException(json, e);
		}

	}
	
	@Test
	public void testForamt() throws Exception {
		String json = "20180214";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		formatter.setTimeZone(TimeZone.getTimeZone("PST"));
		Date date = formatter.parse(json);
		System.out.println(date);
	}
	
}
