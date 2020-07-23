package com.linus.keywords;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

public class TransientTest {
	
	@Test
	public void test() throws ClassNotFoundException {
		Student student = new Student("Bill", 20, 8, "No comments from me!");
        System.out.println("Before serialization:\n\t" + student.toString());
         
         
        // Serialization of the object.
        try {
            FileOutputStream file = new FileOutputStream("student.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(student);
            
            System.out.printf("\nStudent serialized and saved.\n\n");
            
            out.close();
            file.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
         
         
        // Deserialization of the object.
        try {
            FileInputStream file = new FileInputStream("student.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            Student st = (Student) in.readObject();
             
            System.out.println("After serialization:\n\t" + st.toString());
             
            in.close();
            file.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public static class Student implements Serializable {
	     
	    private String name;
	    private int age;
	    private transient int semesters;
	    private transient String comments;
	     
	    public Student(String name, int age, int semesters, String comments) {
	        this.name = name;
	        this.age = age;
	        this.semesters = semesters;
	        this.comments = comments;
	    }
	     
	    @Override
	    public String toString() {
	        return "Name: " + name + 
	                ", age: " + age + 
	                ", semesters: " + semesters +
	                ", comments: " + comments;    
	    }
	}
}
