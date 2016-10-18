package com.linus.primitive;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 
 * @author lyan2
 */
public class NumberUtil {
	private static String poundSource = ".#####################";
			
	public static void main(String[] args) throws ParseException {
		System.out.println(toPrecision(3.4482432d, 2));
		System.out.println(toPercentage(3.4432432d, 2));
		System.out.println(percentToDouble("3.4532%"));
	}
	
	/**
	 * Invocation toPrecision(3.4432432, 2) will output "3.45".
	 * @param value
	 * @param precision
	 * @return
	 */
	public static double toPrecision(double value, int precision) {
		String format = "#" + ((precision > 0) ? (poundSource.substring(0, precision + 1)) : "");
		DecimalFormat df = new DecimalFormat(format);
		return Double.parseDouble(df.format(value));
	}
	
	/**
	 * Invocation toPercentage(3.4432432, 2) will output "344.32%".
	 * @param value
	 * @param precision
	 * @return
	 */
	public static String toPercentage(double value, int precision) {
		String format = "#" + ((precision > 0) ? (poundSource.substring(0, precision + 1)) : "") + "%";
		DecimalFormat df = new DecimalFormat(format);
		return df.format(value);
	}
	
	/**
	 * Parse "3.4532%" to double value 0.034532.
	 * @param value
	 * @return
	 * @throws ParseException 
	 */
	public static double percentToDouble(String value) throws ParseException {
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		Number num = percentFormat.parse(value);
		return num.doubleValue();
	}
}
