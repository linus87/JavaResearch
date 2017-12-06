package com.linus.https;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

public class DefaultSSLSocketFactory {

	/**
	 * Get a SSLSocketFactory that skips CA Certificate verification and Host
	 * name verification.
	 * 
	 * @return
	 */
	public static SSLSocketFactory getInstance() {
		SSLSocketFactory ssf = null;
		try {
			SSLContext ctx = SSLContext.getInstance("TLSV2");

			// skip CA verification
			X509TrustManager tm = new X509TrustAllManager();

			ctx.init(null, new TrustManager[] { tm }, new SecureRandom());

			// skip hostname verification
			X509HostnameVerifier verifier = new X509HostnameVerifier() {

				public void verify(String string, SSLSocket ssls) throws IOException {
				}

				public void verify(String string, X509Certificate xc) throws SSLException {
				}

				public void verify(String string, String[] strings, String[] strings1) throws SSLException {
				}

				public boolean verify(String string, SSLSession ssls) {
					return true;
				}
			};

			ssf = new SSLSocketFactory(ctx, verifier);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

		return ssf;
	}
}
