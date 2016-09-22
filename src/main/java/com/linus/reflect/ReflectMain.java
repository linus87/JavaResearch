package com.linus.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.linus.xml.SchoolClass;
import com.linus.xml.Student;

public class ReflectMain {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		introspect(User.class);

		reflectDeclared(ReflectMain.User.class);
		reflectPublic(User.class);

		SchoolClass n = newInstance(SchoolClass.class);
		System.out.println(n);
		
		User u = newInstance(User.class);
		System.out.println(u);
		
//		Number num = newInstance(Number.class);
//		System.out.println(num);
		
		List<Student> list = new ArrayList<Student>();
		System.out.println(list.getClass().getComponentType());
		
		Student[] students = new Student[10];
		System.out.println("Component Type: " + students.getClass().getComponentType().getName());
	}
	
	public static <T> void introspect(Class<T> clazz) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			for (PropertyDescriptor property : beanInfo
					.getPropertyDescriptors()) {
				// only properties with getter or setter will be returned
				Class<?> type = property.getPropertyType();
				System.out.println("Property " + property.getDisplayName()
						+ ", type " + type + ": " + type.isPrimitive());
				System.out
						.println("Write Method: " + property.getWriteMethod());
				System.out.println("Read Method: " + property.getReadMethod());
				System.out
						.println("-----------------------------------------------------------------------------------------");
			}

		} catch (IntrospectionException ie) {

		}
	}

	public static <T> void reflectDeclared(Class<T> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		System.out.println("Public Field length: " + fields.length);
		for (Field field : fields) {
			System.out.println("Field Name: " + field.getName()
					+ ", Field Type: " + field.getType().getName());
		}
	}

	public static <T> void reflectPublic(Class<T> clazz) {
		Field[] fields = clazz.getFields();
		System.out.println("Public Field length: " + fields.length);
		for (Field field : fields) {
			System.out.println("Public Field Name: " + field.getName()
					+ ", Field Type: " + field.getType().getName());
		}
	}
	
	public static <T> T newInstance(Class<T> clazz) {
		T obj = null;
		try {
			// only inner static class could be instantiated.
			obj = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}

	public static class User {
		public User() {}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAge() {
			return Integer.toString(age);
		}

		// public void setAge(int age) {
		// this.age = age;
		// }

		public void setAge(String age) {
			this.age = Integer.parseInt(age);
		}

		public Integer getAgeInt() {
			return ageInt;
		}

		public void setAgeInt(Integer ageInt) {
			this.ageInt = ageInt;
		}

		private String name;
		private int age;
		private Integer ageInt;
		// public property
		public String email;
		
	}

}
