package com.linus.json.jettison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamReader;

import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;

import com.linus.json.jettison.pojo.Student;

public class Main {
	public static void main(String[] args) {
		Integer.parseInt(null);
		testJSONObjectWithMap();
		testJSONObjectFromString();
		testJSONObjectWithStAX();
	}

	public static void testJSONObjectWithMap() {
		System.out.println("Method testJSONObjectWithMap: ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstName", "Li`n`us");
		map.put("lastName", "Yan");
		map.put("age", 30);
		map.put("admin", false);
		
		// JSONObject can't handle array
		String[] studentIds = {"1", "2", "3"};
		map.put("studentIds", studentIds);
		
		List<String> list = new ArrayList<String>();
		list.add("eleme`nt 1");
		list.add("element 2");
		list.add("element 3");
		map.put("list", list);

		JSONObject obj = new JSONObject(map);
		System.out.println(obj.toString());
	}
	
	public static void testJSONObjectFromString() {
		System.out.println("Method testJSONObjectFromString: ");

		try {
			JSONObject obj = new JSONObject("{\"lastName\":\"Yan\",\"age\":30,\"list\":[\"element 1\",\"element 2\",\"element 3\"],\"firstName\":\"Linus\", \"admin\":false}");
			System.out.println(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testJSONObjectWithStAX() {
		System.out.println("Method testJSONObjectWithStAX: ");
		try {
			JAXBContext jc = JAXBContext.newInstance(Student.class);

			JSONObject obj = new JSONObject(
					"{\"student\":{\"firstname\":\"Rockey\",\"lastname\":\"Desousa\"}}");
			Configuration config = new Configuration();
			MappedNamespaceConvention con = new MappedNamespaceConvention(config);
			XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Student student = (Student) unmarshaller.unmarshal(xmlStreamReader);

			System.out.println("Student " + student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
