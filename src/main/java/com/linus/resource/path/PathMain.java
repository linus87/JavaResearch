package com.linus.resource.path;

import java.io.File;

import com.linus.json.jackson.JacksonMain;

public class PathMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fromClass();
		
		fromClassLoader();
		
		fromFile();
	}
	
	public static void fromClass() {
		System.out.println("From Class.getResource(): " + PathMain.class.getResource("pathtest.properties"));
		System.out.println("From Class.getResource(): " + PathMain.class.getResource("pathtest.properties").getFile());
		
		System.out.println("From Class.getResource(): " + PathMain.class.getResource("/pathtest.properties"));
		System.out.println("From Class.getResource(): " + PathMain.class.getResource("/pathtest.properties").getFile());
	}
	
	public static void fromClassLoader() {
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println("From ClassLoader.getResource(): " + PathMain.class.getClassLoader().getResource("pathtest.properties"));
		System.out.println("From ClassLoader.getResource(): " + PathMain.class.getClassLoader().getResource("pathtest.properties").getFile());
		
//		System.out.println("From ClassLoader.getResource(): " + PathMain.class.getClassLoader().getResource("/pathtest.properties"));
//		System.out.println("From ClassLoader.getResource(): " + PathMain.class.getClassLoader().getResource("/pathtest.properties").getFile());
	}
	
	public static void fromFile() {
		System.out.println("------------------------------------------------------------------------------------------------------");
		File file = new File("pathtest.properties");
		System.out.println("From File: " + file.getAbsolutePath());
		System.out.println("From File, user.dir: " + System.getProperty("user.dir"));
	}

}
