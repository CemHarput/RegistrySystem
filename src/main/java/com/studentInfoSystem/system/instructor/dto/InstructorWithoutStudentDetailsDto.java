package com.studentInfoSystem.system.instructor.dto;

import com.studentInfoSystem.system.instructor.model.Instructor;
import com.studentInfoSystem.system.student.dto.StudentDto;

import java.util.stream.Collectors;

public record InstructorWithoutStudentDetailsDto(String name, String surname) {

    public static InstructorWithoutStudentDetailsDto convertFromInstructor(Instructor instructor){
        return new InstructorWithoutStudentDetailsDto(instructor.getName(), instructor.getSurname());
    }
}
