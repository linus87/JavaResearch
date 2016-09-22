package com.linus.xml.sax;

import java.beans.IntrospectionException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXResolver {
	private Object result;
	private boolean parsing = false;
	

	public void parseText(String xmlContent)
			throws ParserConfigurationException, SAXException, IOException, IntrospectionException {
		if (parsing) {
			return;
		}
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		ByteArrayInputStream input = new ByteArrayInputStream(
				xmlContent.getBytes("UTF-8"));
		XMLToObjectHandler handler = new XMLToObjectHandler();
		parser.parse(input, handler);		
	}
	
	public void parseFile(File file)
			throws ParserConfigurationException, SAXException, IOException, IntrospectionException {
		if (parsing) {
			return;
		}
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		InputStream input = new FileInputStream(file);
		XMLToObjectHandler handler = new XMLToObjectHandler();
		parser.parse(input, handler);		
	}
	
	public boolean isParsing() {
		return parsing;
	}
	
	public Object getResult() {
		return result;
	}
	
	public class XMLToObjectHandler extends DefaultHandler {
		private Logger log = Logger.getLogger(XMLToObjectHandler.class.getName());
		private String value;

		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.startDocument();
			parsing = true;
			log.info("Document start: ");
		}

		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.endDocument();
			parsing = false;
			log.info("Document end: ");
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			super.startElement(uri, localName, qName, attributes);
			
			log.info("Element uri: " + uri + ", localName: " + localName + "qName:" + qName);
			for (int i = 0; i < attributes.getLength(); i++) {
				log.info("Attribute uri: " + attributes.getURI(i) + ", localName: " + attributes.getLocalName(i) + "qName:" + attributes.getQName(i));
			}
		}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub
			super.characters(ch, start, length);
			value = new String(ch, start, length);
			log.info("Element content: " + value);
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			super.endElement(uri, localName, qName);
			log.info("value: " + value);
		}
		
		public Object parseElement(String qName, Class<?> type) {
			return null;
		}
	}
}
