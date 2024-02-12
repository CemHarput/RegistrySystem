package com.studentInfoSystem.system.student.repository;

import com.studentInfoSystem.system.grade.model.Grade;
import com.studentInfoSystem.system.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentRepository extends JpaRepository<Student,Long> {


}
