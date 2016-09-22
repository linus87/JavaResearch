package com.linus.enumerate;

public enum Gender {
	// Gender("male", 1), Gender("female", 0)
	MALE("male", 1), FEMALE("female", 0);
	
	private final String name;
	
	private final int id;
	
	Gender(String name, int id){
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
}
