package com.linus.charset;

import java.io.UnsupportedEncodingException;

public class UnicodeDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
	// TODO Auto-generated method stub
	String s = "中国";
	byte[] bytes= s.getBytes("UTF-8");
	System.out.println("UTF-8:");
	System.out.println(bytes.length);
	
	System.out.println("Unicode:");
	String us = new String(bytes, "Unicode");
	System.out.println(us.length());
	System.out.println(us);	
	
	System.out.println("Reverse:");
	byte[] bytes2 = us.getBytes("Unicode");
	System.out.println(bytes2.length);
	String os = new String(bytes2, "UTF-8");	
	
	System.out.println(os);
    }

}
