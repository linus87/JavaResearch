package com.linus.test.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;

import com.google.gson.JsonSyntaxException;

public class DateFormatTest {
	// below three date formatters are used in com.google.gson.internal.bind.DateTypeAdaptor
	private final DateFormat enUsFormat = DateFormat.getDateTimeInstance(
			DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US);
	private final DateFormat localFormat = DateFormat.getDateTimeInstance(
			DateFormat.DEFAULT, DateFormat.DEFAULT);
	private final DateFormat iso8601Format = buildIso8601Format();

	private static DateFormat buildIso8601Format() {
		DateFormat iso8601Format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
		return iso8601Format;
	}

	@Test
	public void testDateTypeAdaptor() throws Exception {
		String json = "2018-02-14T12:49:46.000Z";

		try {
			System.out.println(localFormat.parse(json));
		} catch (ParseException ignored) {
		}
		try {
			System.out.println(enUsFormat.parse(json));
		} catch (ParseException ignored) {
		}
		try {
			System.out.println(iso8601Format.parse(json));
		} catch (ParseException e) {
			throw new JsonSyntaxException(json, e);
		}

	}
}
