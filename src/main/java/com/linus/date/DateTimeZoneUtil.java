package com.linus.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeZoneUtil {
	private static final String UTC_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	/**
	 * Date don't have time zone information. So, it's always a local time. If
	 * you want to convert date from one time zone to another, you have to
	 * provide the source time zone and the destination time zone.
	 * 
	 * 
	 * @param date
	 *            Local time
	 * @param fromZone
	 *            , the original time zone of date
	 * @param toZone
	 *            the time zone will converted to.
	 * @return new date in toZone.
	 */
	public static Date convert(Date date, TimeZone fromZone, TimeZone toZone) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

		// get the time representation of destination time zone from local time.
		format.setTimeZone(toZone);
		String newDate = format.format(date);

		// convert destination time
		format.setTimeZone(fromZone);
		try {
			return format.parse(newDate);
		} catch (ParseException e) {
			return null;
		}
	}

	// date format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	public static DateFormat getUTCDateFormat() {
		return new SimpleDateFormat(UTC_DATETIME_FORMAT);
	}

	/**
	 * Format date time to : yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	 * 
	 * @param date
	 * @param zone
	 * @return
	 */
	public static String formatUTCDateTime(Date date, TimeZone zone) {
		DateFormat format = getUTCDateFormat();
		format.setTimeZone(zone);
		return format.format(date);
	}

	/**
	 * Parse date format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	 * 
	 * @param dateStr
	 * @param zone
	 * @return
	 * @throws ParseException
	 */
	public static Date parseUTCDateTime(String dateStr, TimeZone zone)
			throws ParseException {
		DateFormat format = getUTCDateFormat();
		format.setTimeZone(zone);
		return format.parse(dateStr);
	}
	
	public static void main(String args[]) {
		System.out.println(formatUTCDateTime(new Date(), TimeZone.getDefault()));
	}
}
