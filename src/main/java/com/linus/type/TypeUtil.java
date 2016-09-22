package com.linus.type;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.linus.enums.ICustomEnum;

public class TypeUtil {
	
	/**
	 * Return the mapped java class implementation of specified type. 
	 * This method support string, double, float, int, date, time, boolean, short, byte.
	 * 
	 * @param type String representation of a type.
	 * @return Java class implementation.
	 */
	public static Class<?> resolveType(String type) {
		if (type == null || type.isEmpty()) return String.class;
		
		switch (type.toLowerCase()) {
		case "string" : return String.class;
		case "double" : return Double.class;
		case "float"  : return Float.class;
		case "int"    : return Integer.class;
		case "date"   : return Date.class;
		case "time"   : return Time.class;
		case "boolean": return Boolean.class;
		case "short"  : return Short.class;
		case "byte"   : return Byte.class;
		default       : return String.class;
		}
	}
	
	/**
	 * Resolve ExcelEnum values.
	 * @param value
	 * @param type
	 * @return
	 */
	public static ICustomEnum resolveICustomEnum(String value, Class<ICustomEnum> type) {
		for (ICustomEnum constant : type.getEnumConstants()) {
			if (constant.value().equalsIgnoreCase(value)) {
				return constant;
			}
		}
		
		return null;
	}
	
	/**
	 * Resolve enum values.
	 * @param value
	 * @param type
	 * @return
	 */
	public static <T> T resolveEnumValue(String value, Class<T> type) {
		for (T constant : type.getEnumConstants()) {
			if (constant.toString().equalsIgnoreCase(value)) {
				return constant;
			}
		}
		
		return null;
	}
}
