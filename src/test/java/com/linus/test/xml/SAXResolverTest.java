package com.linus.test.xml;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.linus.xml.sax.SAXResolver;

public class SAXResolverTest {

	public static void main(String[] args) throws SAXException, IOException, InstantiationException, IllegalAccessException, IntrospectionException {
		SAXResolver parser = new SAXResolver();
		try {
			File file = new File(DOMResolverTest.class.getResource("/com/linus/xml/dom/students.xml").getFile());
			parser.parseFile(file);
			
		} catch (UnsupportedEncodingException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
