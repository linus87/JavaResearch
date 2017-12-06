package com.linus.https;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.net.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class TelesignService {
	private static Logger m_logger = Logger.getLogger(TelesignService.class.getName());
	private static final String TELESIGN_BASE_URL = "https://rest.telesign.com";
	private static final String SMS_SUB_URL = "/v1/verify/sms";
	private static final String SMS_URL = TELESIGN_BASE_URL + SMS_SUB_URL;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z",
			Locale.ENGLISH);

	private static final String MESSAGE_IN_PROGRESS = "290";

	private static final String QUEUED_BY_TELESIGN = "291";

	private static final String QUEUED_AT_GATEWAY = "292";

	private static final String CALL_IN_PROGRESS = "103";

	private static final String DELIVERED_TO_HANDSET = "200";

	private static final String CALL_ANSWERED = "100";

	private static final String content_type = "application/x-www-form-urlencoded";
	
	private static final String CONSUMER_ID = "4AD1E34B-1B43-4B76-82A4-99617CDAEF11";
	
	private static final String SECRET_KEY = "ZVAbDa7JWLVyqugm62tyjmeWXVAyZVdoB3QuA/7wQ3JwEwElD1NAIsVhorRhKwRwHr6WVVGmhkwJ/uyYIkH+PA==";

	// TODO Auto-generated method stub
	public static TelesignResult sendSMS(boolean useProxy, String countryCode, String phoneNumber, String verifyCode,
			String message, String language) {
		TelesignResult result = new TelesignResult();

		String x_ts_nonce = UUID.randomUUID().toString();
		String date = dateFormat.format(new Date());
		phoneNumber = countryCode + phoneNumber;

		String jsonString = "";
		try {
			String stringToSign = "POST\n" + content_type + "\n" + date + "\nx-ts-nonce:" + x_ts_nonce
					+ "\nphone_number=" + phoneNumber + "&language=" + language + "&verify_code=" + verifyCode
					+ "&template=" + message + "\n" + SMS_SUB_URL;
			System.out.println(stringToSign);
			String signature = signString(stringToSign);
			System.out.println(signature);

			HttpClient httpclient = getHttpClient(useProxy);

			HttpPost httpPost = new HttpPost(SMS_URL);
			httpPost.addHeader("Date", date);
			httpPost.addHeader("Content-Type", content_type);
			httpPost.addHeader("X-TS-Nonce", x_ts_nonce);
			httpPost.addHeader("Authorization", "TSA " + CONSUMER_ID + ":" + signature);

			httpPost.setEntity(new StringEntity("phone_number=" + phoneNumber + "&language=" + language
					+ "&verify_code=" + verifyCode + "&template=" + message));

			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			entity = new BufferedHttpEntity(entity);
			jsonString = EntityUtils.toString(entity);
			m_logger.log(Level.INFO, "Telesign response:" + jsonString);
			JSONObject jsonResponse = new JSONObject(jsonString);
			JSONObject jsonStatus = jsonResponse.getJSONObject("status");
			String statusCode = jsonStatus.getString("code");
			result.setStatusCode(statusCode);
			if (statusCode.equals(MESSAGE_IN_PROGRESS) || statusCode.equals(QUEUED_BY_TELESIGN)
					|| statusCode.equals(QUEUED_AT_GATEWAY)) {
				result.setSuccess(true);
				result.setErrorMessgae("");
				result.setReferenceId(jsonResponse.getString("reference_id"));
			} else {
				JSONArray jsonErrors = jsonResponse.getJSONArray("errors");
				result.setSuccess(false);
				result.setErrorMessgae("status code:" + statusCode + ", errors:" + jsonErrors);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			m_logger.log(Level.SEVERE, "Unexpect error:" + e.getMessage());
			m_logger.log(Level.SEVERE, "Telesign response:" + jsonString);
			result.setSuccess(false);
			result.setErrorMessgae(e.getMessage());
		}

		return result;
	}

	private static String signString(String stringToSign) throws Throwable {

		byte[] decodeSecretkeyBytes = Base64
				.decodeBase64(SECRET_KEY);
		byte[] UTF8EncodingStringToSignBytes = stringToSign.getBytes("UTF-8");

		byte[] byteHMAC = null;
		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec spec = new SecretKeySpec(decodeSecretkeyBytes, "HmacSHA1");
		mac.init(spec);
		byteHMAC = mac.doFinal(UTF8EncodingStringToSignBytes);

		return Base64.encodeBase64String(byteHMAC);
	}
	
	public static HttpClient getHttpClient(boolean useProxy) {
		HttpClient httpclient = new DefaultHttpClient();
		
		return wrapClientToFixSSLIssue(httpclient);
	}
	
	/**
	 * Fix "javax.net.ssl.SSLPeerUnverifiedException: peer not authenticated" issue.
	 * @param base
	 * @return
	 */
	public static HttpClient wrapClientToFixSSLIssue (HttpClient base) {
		SSLSocketFactory ssf = DefaultSSLSocketFactory.getInstance();
        try {
            ClientConnectionManager ccm = base.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            Scheme https = new Scheme("https", 443, ssf);
            sr.register(https);
            return new DefaultHttpClient(ccm, base.getParams());
        } catch (Exception ex) {
            ex.printStackTrace();
            m_logger.log(Level.SEVERE, "Unexpect error:"+ex.getMessage());
            return null;
        }
    }

	public static void main(String[] args) {
		System.setProperty("javax.net.debug","ssl");
		sendSMS(false, "86", "13585979772", "12345","xxxxx", "en-US");
	}

}
