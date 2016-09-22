package com.linus.test.xml;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.linus.xml.Course;
import com.linus.xml.SchoolClass;
import com.linus.xml.Student;
import com.linus.xml.Teacher;
import com.linus.xml.stream.StreamResolver;

public class StreamResolverTest {

	public static void main(String[] args) throws FileNotFoundException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, IntrospectionException {
		StreamResolver parser = new StreamResolver();

		File file = new File(DOMResolverTest.class.getResource(
				"/com/linus/xml/dom/students.xml").getFile());
		FileInputStream input = new FileInputStream(file);
		SchoolClass clazz = parser.parseXmlToObject(input, SchoolClass.class, "class");

		try {
			System.out.println(clazz.getClassName());
			System.out.println(clazz.getStudentNum());
			List<Student> students = clazz.getStudents();
			for (Student stu : students) {
				System.out.println("----------------------------");
				System.out.println("Student roll no : " + stu.getRollNo());
				System.out.println("First Name : " + stu.getFirstName());
				System.out.println("Last Name : " + stu.getLastName());
				System.out.println("Nick Name : " + stu.getNickName());
				System.out.println("Marks : " + stu.getMarks());
				if (stu.getGender() != null) {
					System.out.println("Gender : " + stu.getGender());
				}
			}
			
			List<Course> courses = clazz.getCourses();
			for (Course course : courses) {
				System.out.println("----------------------------");
				System.out.println("Course name : " + course.getName());
				System.out.println("Course time : " + course.getTime());
			}
			
			List<String> headmen = clazz.getHeadmans();
			for (String headman : headmen) {
				System.out.println("----------------------------");
				System.out.println("Headman name : " + headman);
			}

//			Teacher[] teachers = clazz.getTeachers();
//			for (Teacher stu : teachers) {
//				System.out.println("----------------------------");
//				System.out.println("Teacher roll no : " + stu.getRollNo());
//				System.out
//						.println("Teacher First Name : " + stu.getFirstName());
//				System.out.println("Teacher Last Name : " + stu.getLastName());
//				System.out.println("Teacher Nick Name : " + stu.getNickName());
//				System.out.println("Teacher Marks : " + stu.getMarks());
//			}

			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
