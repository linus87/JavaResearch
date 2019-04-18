package com.linus.test.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import junit.framework.Assert;

public class RegExpTest {
	public static void main(String[] args) {
		testCompile1();
	}
	
	public static void testCompile1() {
		Pattern p = Pattern.compile("yes|no");
		Matcher m = p.matcher("no");
		
		System.out.println(m.matches());
	}
	
	
	@Test
	public static void caseInsensitive() {
		Assert.assertTrue(Pattern.matches("(?i)zh_CN|zh_TW|zh_HK", "zh_hk"));
		
		Pattern p = Pattern.compile("zh_CN|zh_TW|zh_HK", Pattern.CASE_INSENSITIVE);
		
		Assert.assertTrue(p.matcher("zh_hk").matches());
	}
}
