package com.linus.json.gson.pojo;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class User {
	private static int count = 0; 
	
	private String username = "linus";
	
	@SerializedName("pw")
	private String password = null;
	
	@SerializedName("emailAddress")
	private String email = "linus.yan@hotmail.com";
	
	@SerializedName("birthday")
	private Date birthday = new Date();
	
	private transient String transientField = "fds";
	
	private User father;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getFather() {
		return father;
	}

	public void setFather(User father) {
		this.father = father;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/*public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}*/
	
}
