package com.linus.cipher;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


/**
 * Advanced Encryption Standard as specified by NIST in a draft FIPS. Based on
 * the Rijndael algorithm by Joan Daemen and Vincent Rijmen, AES is a 128-bit
 * block cipher supporting keys of 128, 192, and 256 bits.
 * 
 * It's used to replace DES.
 * 
 * @author lyan2
 *
 */
public class AES {
	private KeyGenerator keygen;

	private SecretKey seckey;
	private IvParameterSpec ivSpec;
	
	private String key = "W@1sw8cUaw32!O5f";
	private String iv   = "aabbccddeeffgghh";

	public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
		AES aes = new AES();
		String result = aes.encyptWidthCBC_PKCS5Padding("hello world, you are perfect");
		System.out.print("After crypt:" + result);
		
		System.out.print(aes.decryptWithCBC_PKCS5Padding(result));
	}

	public AES() throws NoSuchAlgorithmException, NoSuchPaddingException {
		seckey = new SecretKeySpec(key.getBytes(), "AES");
		ivSpec = new IvParameterSpec(iv.getBytes());
		seckey = new SecretKeySpec(key.getBytes(), "AES");
	}

	public String encyptWidthCBC_PKCS5Padding(String str) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		c.init(Cipher.ENCRYPT_MODE, seckey, ivSpec);
		byte[] src = str.getBytes();
		byte[] cipher = c.doFinal(src);
		
		String result = Base64.encodeBase64String(cipher);
		return result;
	}

	public String decryptWithCBC_PKCS5Padding(String base64Str) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
		byte[] cipher = Base64.decodeBase64(base64Str);
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKey seckey = new SecretKeySpec(key.getBytes(), "AES");
		if (seckey == null) {
			System.out.println("key is empty");
		}
		c.init(Cipher.DECRYPT_MODE, seckey, ivSpec);
		byte[] plain = c.doFinal(cipher);

		return new String(plain);
	}
}
