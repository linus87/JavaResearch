package com.linus.json.jettison.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
	private String firstName = "Rockey";
	private String lastName = "Desousa";
	private int age;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ "]";
	}
}
