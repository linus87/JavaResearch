package com.linus.system;

import java.util.Enumeration;
import java.util.Properties;

public class SystemMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties properties = System.getProperties();
		@SuppressWarnings("unchecked")
		Enumeration<String> enu = (Enumeration<String>) properties.propertyNames();
		while (enu.hasMoreElements()) {
			String propertyName = (String)enu.nextElement();
			System.out.println(propertyName + ": " + properties.getProperty(propertyName));
		}
		
	}

}
