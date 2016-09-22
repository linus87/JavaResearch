package com.linus.json.jackson.pojo;


public class UserUnderline {
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public byte[] getUser_image() {
		return user_image;
	}

	public void setUser_image(byte[] user_image) {
		this.user_image = user_image;
	}

	public int age;
	private Name name;
	private boolean verified;
	private byte[] user_image;
	private Gender gender;
	protected String email = "lyan2@ebay.com";
	
	public enum Gender {
		MALE, FEMALE
	}
}
