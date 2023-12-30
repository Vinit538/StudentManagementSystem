package com.studentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studentManagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
//	@Query("SELECT MAX(stu.slno) FROM Student stu")
//    int findMaxslno();
}
