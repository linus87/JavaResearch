package com.linus.json.jackson.pojo;

public class Name {
	public Name() {}
	
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	private String first;
	private String last;
}
