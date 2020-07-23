package com.linus.test.cipher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linus.cipher.MD5;

public class DESTest {
	
	private String key = "437f848b1c8e4d3275e753b5";
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testDES_EDE3() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, UnsupportedEncodingException, JsonProcessingException {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("appId", "dtw16010000");
		data.put("partnerId", "23432444");
		data.put("partnerName", "bestman");
		data.put("backURL", "http://www.baidu.com");
		data.put("promoId", "7010l000000NlHQAA0");
//		String source = "{\"appId\":\"dtw16010000\",\"partnerId\":\"23432444\",\"partnerName\":\"bestman\",\"backURL\":\"http:\\/\\/www.baidu.com\",\"promoId\":\"7010l000000NlHQAA0\"}";
		String source = mapper.writeValueAsString(data); 
		System.out.println("source :" + source);
		String hash = MD5.digest(source);
		
		System.out.println("MD5 :" + hash);
		System.out.println("PHP MD5 :" + "c4f76e6b5f641300b95bbda277b64aee");
		
		testDES_EDE3("DESede/CBC/NoPadding", key, hash);
		testDES_EDE3("DESede/CBC/PKCS5Padding", key, hash);
		testDES_EDE3("DESede/ECB/NoPadding", key, hash);
		testDES_EDE3("DESede/ECB/PKCS5Padding", key, hash);
	}

	public void testDES_EDE3(String scheme, String key, String plainText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, UnsupportedEncodingException {
		Cipher c = Cipher.getInstance(scheme);
		
		DESedeKeySpec desKey = new DESedeKeySpec(key.getBytes("UTF8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey secureKey = keyFactory.generateSecret(desKey);
		
		c.init(Cipher.ENCRYPT_MODE, secureKey);
		
		byte[] src = plainText.getBytes();
		byte[] cipher = c.doFinal(src);
		
		System.out.println("Scheme " + scheme + ":" + Base64.getEncoder().encodeToString(cipher));
	}
}
