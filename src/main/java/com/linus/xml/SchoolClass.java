package com.linus.xml;

import java.util.List;

public class SchoolClass {
	public String className;
	public int studentNum;
	public List<Student> students;
	public Teacher[] teachers;
	public List<Course> courses;
	public List<String> headmans;
	
	public SchoolClass() {}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public Teacher[] getTeachers() {
		return teachers;
	}
	public void setTeachers(Teacher[] teachers) {
		this.teachers = teachers;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public List<String> getHeadmans() {
		return headmans;
	}
	public void setHeadmans(List<String> headmans) {
		this.headmans = headmans;
	}
	
	
	
}
