package com.studentInfoSystem.system.grade.repository;

import com.studentInfoSystem.system.grade.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade,Long> {
}
