package com.linus.https;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class HttpsPost {
	/**
	 * 获得KeyStore.
	 * 
	 * @param keyStorePath
	 *            密钥库路径
	 * @param password
	 *            密码
	 * @return 密钥库
	 * @throws Exception
	 */
	public static KeyStore getKeyStore(String password, String keyStorePath) throws Exception {
		// 实例化密钥库
		KeyStore ks = KeyStore.getInstance("JKS");
		// 获得密钥库文件流
		InputStream is = HttpsPost.class.getResourceAsStream(keyStorePath);
//		FileInputStream is = new FileInputStream(keyStorePath);
		// 加载密钥库
		ks.load(is, password.toCharArray());
		// 关闭密钥库文件流
		is.close();
		return ks;
	}

	/**
	 * 获得SSLSocketFactory.
	 * 
	 * @param password
	 *            密码
	 * @param keyStorePath
	 *            密钥库路径
	 * @param trustStorePath
	 *            信任库路径
	 * @return SSLSocketFactory
	 * @throws Exception
	 */
	public static SSLContext getSSLContext(String password, String keyStorePath, String trustStorePath)
			throws Exception {
		// 实例化密钥库
		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		// 获得密钥库
		KeyStore keyStore = getKeyStore(password, keyStorePath);
		// 初始化密钥工厂
		keyManagerFactory.init(keyStore, password.toCharArray());

		// 实例化信任库
		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory
				.getDefaultAlgorithm());
		// 获得信任库
		KeyStore trustStore = getKeyStore(password, trustStorePath);
		// 初始化信任库
		trustManagerFactory.init(trustStore);
		// 实例化SSL上下文
		SSLContext ctx = SSLContext.getInstance("TLS");
		// 初始化SSL上下文
		ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
		// 获得SSLSocketFactory
		return ctx;
	}

	/**
	 * 初始化HttpsURLConnection.
	 * 
	 * @param password
	 *            密码
	 * @param keyStorePath
	 *            密钥库路径
	 * @param trustStorePath
	 *            信任库路径
	 * @throws Exception
	 */
	public static void initHttpsURLConnection(String password, String keyStorePath, String trustStorePath)
			throws Exception {
		// 声明SSL上下文
		SSLContext sslContext = null;
		// 实例化主机名验证接口
		HostnameVerifier hnv = new MyHostnameVerifier();
		try {
			sslContext = getSSLContext(password, keyStorePath, trustStorePath);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		if (sslContext != null) {
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		}
		HttpsURLConnection.setDefaultHostnameVerifier(hnv);
	}

	/**
	 * 发送请求.
	 * 
	 * @param httpsUrl
	 *            请求的地址
	 * @param xmlStr
	 *            请求的数据
	 */
	public static void post(String httpsUrl, String xmlStr) {
		HttpsURLConnection urlCon = null;
		try {
			urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();

			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("Content-Length", String.valueOf(xmlStr.getBytes().length));
			urlCon.setUseCaches(false);
			// 设置为gbk可以解决服务器接收时读取的数据中文乱码问题
			urlCon.getOutputStream().write(xmlStr.getBytes("gbk"));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试方法.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 密码
		String password = "changeit";
		// 密钥库
		String keyStorePath = "/security/tomcat.keystore";
		// 信任库
		String trustStorePath = "/security/tomcat.keystore";
		// 本地起的https服务
		String httpsUrl = "https://jkbis.wanlitong.com/bis/service";
		// 传输文本
		String xmlStr = "Jmx0O2Jpc2RhdGEmZ3Q7Jmx0O3JlcUlkJmd0O1AxMDAwNDc1Jmx0Oy9yZXFJZCZndDsmbHQ7c2VydmljZUlkJmd0OzEwMDAwMTAwMDAwMDAwNzcmbHQ7L3NlcnZpY2VJZCZndDsmbHQ7Yml6ZGF0YSZndDsmbHQ7ZGF0YSZndDsmbHQ7cGFydG5lcklkJmd0O2tleWhlcmUmbHQ7L3BhcnRuZXJJZCZndDsmbHQ7cGFydG5lclR5cGUmZ3Q7UDM0OTQ2Jmx0Oy9wYXJ0bmVyVHlwZSZndDsmbHQ7L2RhdGEmZ3Q7Jmx0O3NpZ24mZ3Q7Jmx0O2lkeCZndDswMSZsdDsvaWR4Jmd0OyZsdDt2YWx1ZSZndDtkODA4YzdmZjExMGI4ODQ3YjdmM2JhMzg3NDgwNDhiZTcwNTM4YWM4Jmx0Oy92YWx1ZSZndDsmbHQ7L3NpZ24mZ3Q7Jmx0Oy9iaXpkYXRhJmd0OyZsdDsvYmlzZGF0YSZndDs=";
		HttpsPost.initHttpsURLConnection(password, keyStorePath, trustStorePath);
		// 发起请求
		HttpsPost.post(httpsUrl, xmlStr);
	}
}
