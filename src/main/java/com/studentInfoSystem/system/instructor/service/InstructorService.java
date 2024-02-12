package com.studentInfoSystem.system.instructor.service;


import com.studentInfoSystem.system.instructor.dto.InstructorDto;
import com.studentInfoSystem.system.instructor.dto.InstructorWithoutStudentDetailsDto;
import com.studentInfoSystem.system.instructor.model.Instructor;
import com.studentInfoSystem.system.instructor.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<InstructorWithoutStudentDetailsDto> getAllInstructors(){
        return instructorRepository.findAll().stream().map(InstructorWithoutStudentDetailsDto::convertFromInstructor).toList();
    }
    public List<InstructorDto> getAnInstructorsWithDetails(Long instructorId){
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + instructorId));

        return Collections.singletonList(InstructorDto.convertFromInstructor(instructor));
    }




}
