package com.studentManagement.services;

import java.util.List;

import com.studentManagement.entity.Student;

public interface StudentServices {
	String addStudent(Student s); 
	Student getStudent (String studentId);
	List<Student> getAllStudent();
	String upDateStudent(Student s);
	String deleteStudent(String stuentId);
	
//	int findMaxslno();
}
