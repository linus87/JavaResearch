package com.linus.primitive;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @author lyan2
 */
public class StringUtil {
    public static void main(String[] args) throws UnsupportedEncodingException {
	String old = "你好，中国";
	System.out.println(convertCharset(old, "GB2312"));
    }

    /**
     * Repeat a segment of text and return the result.
     * 
     * @param source
     * @param times
     * @return
     */
    public static String repeat(String source, int times) {
	StringBuffer buffer = new StringBuffer();
	while (times > 0) {
	    buffer.append(source);
	    times--;
	}

	return buffer.toString();
    }

    public static String convertCharset(String source, String newCharset) throws UnsupportedEncodingException {
	String output = null;
	byte[] bytes = source.getBytes(newCharset);
	output = new String(bytes, newCharset);
	return output;
    }
}
