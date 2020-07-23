package com.linus.test.cipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

public class MessageDigestTest {

	@Test
	public void md5Test() throws NoSuchAlgorithmException {
		String plainText = "{\"appId\":\"dtw16010000\",\"partnerId\":\"23432444\",\"partnerName\":\"bestman\",\"backURL\":\"http://www.baidu.com\",\"promoId\":\"7010l000000NlHQAA0\"}";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(plainText.getBytes());
		
		byte[] digest = md.digest();
		String hash  = DatatypeConverter.printHexBinary(digest).toLowerCase();
		System.out.println(hash);
	}
}
