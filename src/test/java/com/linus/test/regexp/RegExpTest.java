package com.linus.test.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpTest {
	public static void main(String[] args) {
		testCompile1();
		testCompile2();
		testMatches();
	}
	
	public static void testCompile1() {
		Pattern p = Pattern.compile("yes|no");
		Matcher m = p.matcher("no");
		
		System.out.println(m.matches());
	}
	
	public static void testCompile2() {
		Pattern p = Pattern.compile("zh_CN|zh_TW|zh_HK", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher("zh_hk");
		
		System.out.println(m.matches());
	}
	
	public static void testMatches() {
		boolean b = Pattern.matches("zh_CN|zh_TW|zh_HK/i", "zh_hk");
		
		System.out.println(b);
	}
}
