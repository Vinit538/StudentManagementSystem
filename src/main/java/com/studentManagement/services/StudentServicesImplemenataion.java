package com.studentManagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentManagement.entity.Student;
import com.studentManagement.repository.StudentRepository;
@Service
public class StudentServicesImplemenataion implements StudentServices {
	@Autowired
	StudentRepository srepo;

	public StudentServicesImplemenataion(StudentRepository srepo) {
		this.srepo = srepo;
	}

	@Override
	public String addStudent(Student s) {
		// TODO Auto-generated method stub
		srepo.save(s);
		System.out.println("Student Added Sucessfully");
		return "Student Added Sucessfully";
	}

	@Override
	public Student getStudent(String studentId) {
		Student st=srepo.findById(studentId).get();
		return st;
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> slist=srepo.findAll();
		return slist;
	}

	@Override
	public String upDateStudent(Student s) {
		srepo.save(s);
		return "Student Details Updated";
	}

	@Override
	public String deleteStudent(String stuentId) {
		srepo.deleteById(stuentId);
		return "Student deleted Sucessfully";
	}
	 
	
//	@Override
//	public int findMaxslno() {
//		Integer lastGeneratedId = srepo.findMaxslno();
//        return lastGeneratedId != null ? lastGeneratedId : 0;
//	}
}
