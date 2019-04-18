package com.linus.test.primitive;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringTest {
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void main() {
		String format = "hello %s";
		String result = String.format(format, "world!");
		System.out.println(result);
		
		char c = '3';
		System.out.println(c);
		
		Double d = (Double) null;
		System.out.println(d);
	}
	
	@Test
	public void compare() {
		System.out.println("Compare two string:---------------------------------");
		String scheme = "http";
		String scheme2 = new String("http");
		System.out.println("http" == "http");
		System.out.println("http" == scheme);
		System.out.println(scheme2 == scheme);
		System.out.println(new String("http") == scheme);
		System.out.println(scheme.equalsIgnoreCase(new String("http")));		
	}
	
	@Test
	public void split() throws JsonProcessingException {
		System.out.println("Split a string:---------------------------------");
		String text = "Draft>Nomination eDM in approve flow>Nomination eDM approved>Seller nomination_Need approve>Promotion Submitted>Promotion Approved>Notification eDM in approve flow>Notification eDM approved>Seller Feedback>Promotion in progress>Promotion in validation>Promotion validated";
		System.out.println(mapper.writeValueAsString(text.split(">")));
	}
	
	@Test
	public void runRegexp() {
		System.out.println("Regexp Test");
		String pattern = "^.*(\\.png|\\.css|\\.jpg|\\.ico|\\.gif|\\.js)$|^.*(\\.png|\\.css|\\.jpg|\\.ico|\\.gif|\\.js)\\?.*$";
		String header = "^image\\/.*$|^text\\/css.*$";
		
		System.out.println("/J2EEProject/js/jquery.easyui.min.js".matches(pattern));
		System.out.println("/J2EEProject/js/jquery.easyui.min.js?showRow=true".matches(pattern));
		System.out.println("/J2EEProject/login.jsp?showRow=true".matches(pattern));
		
		System.out.println("image/webp,image/*,*/*;q=0.8".matches(header));
		System.out.println("text/css,*/*;q=0.1".matches(header));
		
		String a = null;
	}
	
	@Test
	public void URLEncoderTest() throws UnsupportedEncodingException {
		String results = URLDecoder.decode("%2c  100% Xeon ", "UTF-8");
		String str = "%2c";
		System.out.println(results);
	}
	
}
