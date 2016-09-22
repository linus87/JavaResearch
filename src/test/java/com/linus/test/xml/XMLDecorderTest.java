package com.linus.test.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JButton;

public class XMLDecorderTest {
	public static void main(String[] args) {
		createXML();
		readXML();
	}

	public static void createXML() {
		XMLEncoder e = null;
		try {
			e = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("XMLEncorderDemo.xml")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (e != null) {
			e.writeObject(new JButton("Hello, world"));
			e.close();
		}

	}
	
	public static void readXML() {
		try {
			File file = new File("XMLEncorderDemo.xml");
			XMLDecoder d = new XMLDecoder(new FileInputStream(file));
			Object result = d.readObject();
			System.out.println(result);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
