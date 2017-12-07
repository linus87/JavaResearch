package com.linus.https;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/**
 * 
 * @author lyan2
 */
public class DefaultSSLSocketFactory {
	
	/**
	 * Get a SSLSocketFactory that skips CA Certificate verification and Host
	 * name verification.
	 * 
	 * SSLContext algorithms used TLSv1.2.
	 * 
	 * @return 
	 */
	public static SSLSocketFactory getInstance(final String algorithm) {
		SSLSocketFactory ssf = null;
		try {
			SSLContext ctx = SSLContext.getInstance(algorithm);

			// skip CA verification
			X509TrustManager tm = new X509TrustAllManager();

			ctx.init(null, new TrustManager[] { tm }, new SecureRandom());

			// skip hostname verification
			X509HostnameVerifier verifier = new X509HostnameVerifierImpl();

			ssf = new SSLSocketFactory(ctx, verifier);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

		return ssf;
	}
}
