package com.studentInfoSystem.system.instructor.repository;

import com.studentInfoSystem.system.instructor.model.Instructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {

    @EntityGraph(attributePaths ={"students"} )
    Optional<Instructor> findById(Long id);
}
