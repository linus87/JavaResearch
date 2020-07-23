package com.linus.date;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * All kinds of date operation.
 * 
 * @author lyan2
 */
public class DateUtil {
	private static final Logger logger = Logger.getLogger(DateUtil.class.getName());
	
	// Database time format
	private static final String TIME_FORMAT = "HH:mm:ss";
	
	// Database datetime format
	private static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	// Excel date format may be following three types.
	private static final String DOT_DATE_FORMAT = "yyyy.MM.dd";
	private static final String SLASH_DATE_FORMAT = "yyyy/MM/dd";
	private static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
	private static final String AMERICAN_DATE_FORMAT = "MM/dd/yyyy";
	
	// CS API and SaleForce use this time format, in fact, it's ISO 8601 Date and time format.'Z' means UTC time.
	private static final String UTC_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	// Chinese bei jing time zone
	public final static TimeZone BEIJING_TIMEZONE = TimeZone.getTimeZone("GMT+8:00");
	public static final TimeZone GMT_TIMEZONE =  TimeZone.getTimeZone("GMT");

	private static ThreadLocal<DateFormat> timeThread = new ThreadLocal<DateFormat>();
	
	private static ThreadLocal<DateFormat> simpleDateThread = new ThreadLocal<DateFormat>();
	private static ThreadLocal<DateFormat> slashDateThread = new ThreadLocal<DateFormat>();
	private static ThreadLocal<DateFormat> isoDateThread = new ThreadLocal<DateFormat>();
	private static ThreadLocal<DateFormat> americanDateThread = new ThreadLocal<DateFormat>();
	
	private static ThreadLocal<DateFormat> isoDateTimeThread = new ThreadLocal<DateFormat>();
	private static ThreadLocal<DateFormat> isoDateTimeZoneThread = new ThreadLocal<DateFormat>();
	
	public static void main(String[] args) throws ParseException  {
		System.out.println(parseSlashDate("2/21/2016"));
		System.out.println(parseAmericanDate("2/21/2016"));
		
		Date date = parseISODateTime("1970-01-01 00:00:00", null);
		System.out.println(date.getTime());
		
		System.out.println(formatISODateTime(new Date(), GMT_TIMEZONE));
		System.out.println(formatISODateTime(new Date(), TimeZone.getTimeZone("GMT+8:00")));
	}
	
	/***************************************** American date format: mm/dd/yyyy *********************************************************/
    public static DateFormat getAmericanDateFormat() {
    	DateFormat df = americanDateThread.get();
    	if (df == null) {
    		df = new SimpleDateFormat(AMERICAN_DATE_FORMAT);
    		americanDateThread.set(df);
    	}
    	
    	return df;
    }
    
    public static Date parseAmericanDate (String dateStr) throws ParseException {
        return getAmericanDateFormat().parse(dateStr);
    }
    
    public static String formatAmericanDate (Date date) {
        return getAmericanDateFormat().format(date);
    }
	
    /***************************************** Time format: HH:mm:ss *********************************************************/
	/**
	 * Get date format "HH:mm:ss"
	 * @return
	 */
	public static DateFormat getTimeFormat() {
		DateFormat df = timeThread.get();
		if (df == null) {
			df = new SimpleDateFormat(TIME_FORMAT);
			timeThread.set(df);
		}

		return df;
	}
	
	/**
	 * Format date to "HH:mm:ss"
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date) {
		DateFormat df = getTimeFormat();
		
		return df.format(date);
	}
	
	/***************************************** date format: yyyy/MM/dd *********************************************************/
	public static DateFormat getSlashDateFormat() {
		DateFormat df = slashDateThread.get();
		if (df == null) {
			df = new SimpleDateFormat(SLASH_DATE_FORMAT);
			slashDateThread.set(df);
		}

		return df;
	}
    
    public static Date parseSlashDate (String dateStr) throws ParseException {
        return getSlashDateFormat().parse(dateStr);
    }
    
    public static String formatSlashDate (Date date) {
        return getSlashDateFormat().format(date);
    }

	/**
	 * DateFormat for parsing date like: yyyy.MM.dd
	 * @return
	 */
	public static DateFormat getDotDateFormat() {
		DateFormat df = simpleDateThread.get();
		if (df == null) {
			df = new SimpleDateFormat(DOT_DATE_FORMAT);
			simpleDateThread.set(df);
		}

		return df;
	}
	
	
	
	/**
	 * DateFormat for parsing date like: yyyy-MM-dd
	 * @return
	 */
	public static DateFormat getISODateFormat() {
		DateFormat df = isoDateThread.get();
		if (df == null) {
			df = new SimpleDateFormat(ISO_DATE_FORMAT);
			isoDateThread.set(df);
		}

		return df;
	}
	
	/**
	 * DateFormat for parsing date like: yyyy-MM-dd-HH:mm:ss
	 * @return
	 */
	public static DateFormat getISODateTimeFormat() {
		DateFormat df = isoDateTimeThread.get();
		if (df == null) {
			df = new SimpleDateFormat(ISO_DATETIME_FORMAT);
			isoDateTimeThread.set(df);
		}

		return df;
	}
	
	// date format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	public static DateFormat getISODateTimeZoneFormat() {
		DateFormat df = isoDateTimeZoneThread.get();
		if (df == null) {
			df = new SimpleDateFormat(UTC_DATETIME_FORMAT);
			isoDateTimeZoneThread.set(df);
		}

		return df;
	}

	/**
	 * Parse date format: yyyy.MM.dd
	 * @param dateStr
	 * @param zone
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDotDate(String dateStr, TimeZone zone) throws ParseException {
		DateFormat df = getDotDateFormat();
		
		if (zone != null) {
			df.setTimeZone(zone);
		} else {
			df.setTimeZone(GMT_TIMEZONE);
		}
		
		return df.parse(dateStr);
	}
	
	/**
	 * Parse date format: yyyy-MM-dd
	 * @param dateStr
	 * @param zone
	 * @return
	 * @throws ParseException
	 */
	public static Date parseISODate(String dateStr, TimeZone zone) throws ParseException {
		DateFormat df = getISODateFormat();
		
		if (zone != null) {
			df.setTimeZone(zone);
		} else {
			df.setTimeZone(GMT_TIMEZONE);
		}
		
		return df.parse(dateStr);
	}
	
	/**
	 * Date format: yyyy-MM-dd-HH:mm:ss
	 * @param dateStr
	 * @param zone
	 * @return
	 * @throws ParseException
	 */
	public static Date parseISODateTime(String dateStr, TimeZone zone) throws ParseException {
		DateFormat df = getISODateTimeFormat();
		
		if (zone != null) {
			df.setTimeZone(zone);
		} else {
			df.setTimeZone(GMT_TIMEZONE);
		}
		
		return df.parse(dateStr);
	}
	
	/**
	 * Parse date format: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	 * @param dateStr
	 * @param zone
	 * @return
	 * @throws ParseException
	 */
	public static Date parseUTCDateTime(String dateStr, TimeZone zone) throws ParseException {
		DateFormat format = getISODateTimeZoneFormat();
		
		if (zone != null) {
			format.setTimeZone(zone);
		} else {
			format.setTimeZone(GMT_TIMEZONE);
		}
		
		return format.parse(dateStr);
	}
	

	/**
	 * Format date to : yyyy.MM.dd
	 * @param date
	 * @param zone
	 * @return
	 */
	public static String formatDotDate(Date date, TimeZone zone) {
		DateFormat df = getDotDateFormat();
		
		if (zone != null) {
			df.setTimeZone(zone);
		} else {
			df.setTimeZone(GMT_TIMEZONE);
		}
		
		return df.format(date);
	}

	/**
	 * Format date to : yyyy-MM-dd
	 * @param date
	 * @param zone
	 * @return
	 */
	public static String formatISODate(Date date, TimeZone zone) {
		DateFormat df = getISODateFormat();
		
		if (zone != null) {
			df.setTimeZone(zone);
		} else {
			df.setTimeZone(GMT_TIMEZONE);
		}
		
		return df.format(date);
	}

	/**
	 * Format date time to : yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @param zone
	 * @return
	 */
	public static String formatISODateTime(Date date, TimeZone zone) {
		DateFormat df = getISODateTimeFormat();
		
		if (zone != null) {
			df.setTimeZone(zone);
		} else {
			df.setTimeZone(GMT_TIMEZONE);
		}
		
		return df.format(date);
	}
	
	/**
	 * Format date time to : yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	 * @param date
	 * @param zone
	 * @return
	 */
	public static String formatUTCDateTime(Date date, TimeZone zone) {
		DateFormat format = getISODateTimeZoneFormat();
		
		if (zone != null) {
			format.setTimeZone(zone);
		} else {
			format.setTimeZone(GMT_TIMEZONE);
		}
		
		return format.format(date);
	}
	
    /**************************************************************************************************/
    public static Date parseDate (String dateStr) throws ParseException {
    	if (dateStr == null || dateStr.isEmpty()) return null;
    	
    	try {
    		return getISODateFormat().parse(dateStr);
    	} catch (Exception e) {
    		try {
    			return getSlashDateFormat().parse(dateStr);
    		} catch (Exception e2) {
    			return getDotDateFormat().parse(dateStr);
    		}
    	}
    }
    /**************************************************************************************************/
	/**
	 * Date don't have time zone information. So, it's always a local time. If you want to convert date from one time zone to another,
	 * you have to provide the source time zone and the destination time zone.
	 * 
	 * @param date Local time
	 * @param fromZone, the original time zone of date
	 * @param toZone the time zone will converted to.
	 * @return new date in toZone.
	 */
	public static Date convert(Date date, TimeZone fromZone, TimeZone toZone) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// get the time representation of destination time zone from local time.
		format.setTimeZone(toZone);
		String newDate = format.format(date);
		
		// correct new date with source time zone 
		format.setTimeZone(fromZone);
		try {
			return format.parse(newDate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * This method works in most situations. But it may fail when it's less than an hour before the daylight saving time start day.
	 * @param date Local time
	 * @param fromZone, the original time zone of date
	 * @param toZone the time zone will converted to.
	 * @return new date in toZone.
	 */
	@Deprecated
	public static Date convert2(Date date, TimeZone fromZone, TimeZone toZone) {
		
		if (fromZone.equals(toZone)) {
			return date;
		} else {
			long gap = toZone.getRawOffset() - fromZone.getRawOffset();
			if (fromZone.inDaylightTime(date)) {
				gap -= fromZone.getDSTSavings();
			}
			
			if (toZone.inDaylightTime(date)) {
				gap += toZone.getDSTSavings();
			}
			
			return new Date(date.getTime() + gap);
		}
	}

	/**
	 * Get the beginning datetime of this week.
	 *
	 * @return
	 */
	public static Date getStartDateTimeOfThisWeek(Date today) {
		Calendar cal = Calendar.getInstance();
		if (today != null) {
			cal.setTime(today);
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Get the end datetime of this week.
	 *
	 * @return
	 */
	public static Date getEndDateTimeOfThisWeek(Date today) {
		Calendar cal = Calendar.getInstance();
		if (today != null) {
			cal.setTime(today);
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * Get the date seven days ago.
	 *
	 * @return
	 */
	public static Date getSevenDaysAgoDate(Date today) {
		Calendar cal = Calendar.getInstance();
		if (today != null) {
			cal.setTime(today);
		}
		cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 7);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Parse a date string into a Date object.
	 * 
	 * @param text
	 *            Date string representation.
	 * @return Date Object
	 */
	public static Date resolveDate(String text) {
		Date date = null;
		try {
			date = getISODateFormat().parse(text);
		} catch (ParseException e) {
			try {
				date = getSlashDateFormat().parse(text);
			} catch (ParseException e2) {
				try {
					date = getDotDateFormat().parse(text);
				} catch (ParseException e3) {
					logger.log(Level.WARNING,
							"Failed to parse text into Date object. Inputted text is: "
									+ text);
				}
			}
		}

		return date;
	}

	/**
	 * Get a date's time.
	 * @param date
	 * @return
	 */
	public static Time resolveTime(Date date) {
		String text = getTimeFormat().format(date);
		return Time.valueOf(text);
	}
	
	/**
	 * Return the absolute time lag between two dates.
	 * @param date1
	 * @param date2
	 * @return absolute milliseconds between two dates.
	 */
	public static long timeLag(Date date1, Date date2) {
		return Math.abs(date1.getTime() - date2.getTime());
	}

}
