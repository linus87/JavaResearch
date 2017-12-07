package com.linus.https;

import java.io.IOException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.conn.ssl.X509HostnameVerifier;

public class X509HostnameVerifierImpl implements X509HostnameVerifier {

	public boolean verify(String hostname, SSLSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Apache will call this method before {@link #verify(String, SSLSession)}.
	 */
	public void verify(String host, SSLSocket ssl) throws IOException {
		ssl.setUseClientMode(true);
		String[] embededCipherSuites = ssl.getEnabledCipherSuites();
		for (String suit:embededCipherSuites) {
			System.out.println("embeded: " + suit);
		}
		
		String[] supportedCipherSuites = ssl.getSupportedCipherSuites();
		for (String suit:supportedCipherSuites) {
			System.out.println("supported: " + suit);
		}
	}

	public void verify(String host, X509Certificate cert) throws SSLException {
		// TODO Auto-generated method stub

	}

	public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
		// TODO Auto-generated method stub

	}

}
