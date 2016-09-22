package com.linus.test.xml;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.linus.xml.Course;
import com.linus.xml.SchoolClass;
import com.linus.xml.Student;
import com.linus.xml.Teacher;
import com.linus.xml.dom.DOMResolver;

public class DOMResolverTest {

	public static void main(String[] args) throws SAXException, IOException, InstantiationException, IllegalAccessException {
		DOMResolver parser = new DOMResolver();
		try {
			File file = new File(DOMResolverTest.class.getResource("/com/linus/xml/dom/students.xml").getFile());
			Document doc = parser.createDocument(file);
			
			SchoolClass clazz;
			try {
				clazz = parser.parseElementToObject(doc.getDocumentElement(), SchoolClass.class);
				
				System.out.println(clazz.getClassName());
				System.out.println(clazz.getStudentNum());
				List<Student> students = clazz.getStudents(); 
				Iterator<Student> iter = students.iterator();
				while(iter.hasNext()) {
					System.out.println("----------------------------");
					Student stu = iter.next();
					System.out.println("Student roll no : "	+ stu.getRollNo());
					System.out.println("First Name : " + stu.getFirstName());
					System.out.println("Last Name : " + stu.getLastName());
					System.out.println("Nick Name : " + stu.getNickName());
					System.out.println("Marks : " + stu.getMarks());
					if (stu.getGender() != null) {
						System.out.println("Gender : " + stu.getGender());
					}
				}
				
				Teacher[] teachers = clazz.getTeachers(); 
				for (Teacher stu : teachers) {
					System.out.println("----------------------------");
					System.out.println("Teacher roll no : "	+ stu.getRollNo());
					System.out.println("Teacher First Name : " + stu.getFirstName());
					System.out.println("Teacher Last Name : " + stu.getLastName());
					System.out.println("Teacher Nick Name : " + stu.getNickName());
					System.out.println("Teacher Marks : " + stu.getMarks());
				}
				
				List<Course> courses = clazz.getCourses(); 
				for (Course course : courses) {
					System.out.println("----------------------------");
					System.out.println("Course name : "	+ course.getName());
					System.out.println("Course time : " + course.getTime());
				}
				
				List<String> headmen = clazz.getHeadmans();
				for (String headman : headmen) {
					System.out.println("----------------------------");
					System.out.println("Headman name : " + headman);
				}
			} catch (IllegalArgumentException | InvocationTargetException
					| DOMException | IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
