package com.linus.json.jettison.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	private int id;

    @XmlElement(name="first-name")
    private String firstName;

    @XmlElement(name="last-name")
    private String lastName;
 
    private Address address;

    @XmlElement(name="phone-number")
    private List<PhoneNumber> phoneNumbers;
}
