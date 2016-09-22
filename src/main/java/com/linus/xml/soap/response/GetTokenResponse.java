package com.linus.xml.soap.response;

public class GetTokenResponse {
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getAck() {
		return ack;
	}
	public void setAck(String ack) {
		this.ack = ack;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	public String geteBayAuthToken() {
		return eBayAuthToken;
	}
	public void seteBayAuthToken(String eBayAuthToken) {
		this.eBayAuthToken = eBayAuthToken;
	}
	public String getHardExpirationTime() {
		return hardExpirationTime;
	}
	public void setHardExpirationTime(String hardExpirationTime) {
		this.hardExpirationTime = hardExpirationTime;
	}

	private String timeStamp;
	private String ack;
	private String version;
	private String build;
	private String eBayAuthToken;
	private String hardExpirationTime;
}
