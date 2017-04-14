package com.linus.cipher;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * DES implementation.
 * 
 * @author linus.yan@hotmail.com
 */
public class DES {
    private KeyGenerator keygen;

    private SecretKey secureKey;

    public static void main(String[] args) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
	    NoSuchAlgorithmException, NoSuchPaddingException {
	DES des = new DES();
	String result = des.encypt("hello world, you are perfect, 你好，中国");
	System.out.println("Cipher Text: " + result);

	System.out.println("Plain Text : " + des.decrypt(result));
    }

    public DES() throws NoSuchAlgorithmException, NoSuchPaddingException {
	keygen = KeyGenerator.getInstance("DES");

	secureKey = keygen.generateKey();
    }
    
    public DES(String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
	DESKeySpec desKey = new DESKeySpec(key.getBytes());
	SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	secureKey = keyFactory.generateSecret(desKey);
    }

    public String encypt(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
	SecureRandom random = new SecureRandom();
	Cipher c = Cipher.getInstance("DES");
	c.init(Cipher.ENCRYPT_MODE, secureKey, random);
	
	byte[] src = str.getBytes();
	byte[] cipher = c.doFinal(src);

	String result = Base64.encodeBase64String(cipher);
	return result;
    }

    public String decrypt(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
	SecureRandom random = new SecureRandom();
	Cipher c = Cipher.getInstance("DES");
	byte[] cipher = Base64.decodeBase64(str);
	c.init(Cipher.DECRYPT_MODE, secureKey, random);
	byte[] plain = c.doFinal(cipher);

	return new String(plain);
    }
}
