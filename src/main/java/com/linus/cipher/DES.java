package com.linus.cipher;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class DES {
	private KeyGenerator keygen;
	
	private SecretKey seckey;
	
	private Cipher c;
	
	public DES() throws NoSuchAlgorithmException, NoSuchPaddingException {
		keygen = KeyGenerator.getInstance("DES");
		
		seckey = keygen.generateKey();
		
		c = Cipher.getInstance("DES");
	}
	
	public String encypt(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		c.init(Cipher.ENCRYPT_MODE, seckey);
		byte[] src = str.getBytes();
		byte[] cipher = c.doFinal(src);
		
		String result = new String(cipher);
		return result;
	}
	
	public String decrypt(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] cipher = str.getBytes();
		c.init(Cipher.DECRYPT_MODE, seckey);
		byte[] plain = c.doFinal(cipher);
		
		return new String(plain); 
	}
}
