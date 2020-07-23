package com.linus.cipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class MD5 {
	
	public static String digest(String plainText) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(plainText.getBytes());
		
		byte[] digest = md.digest();
		String hash  = DatatypeConverter.printHexBinary(digest).toLowerCase();
		
		return hash;
	}
}
