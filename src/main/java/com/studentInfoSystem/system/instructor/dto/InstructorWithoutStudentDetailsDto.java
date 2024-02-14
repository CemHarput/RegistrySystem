package com.studentInfoSystem.system.instructor.dto;

import com.studentInfoSystem.system.instructor.model.Instructor;
import com.studentInfoSystem.system.student.dto.StudentDto;

import java.util.stream.Collectors;

public record InstructorWithoutStudentDetailsDto(String id,String name, String surname,String active) {

    public static InstructorWithoutStudentDetailsDto convertFromInstructor(Instructor instructor){
        return new InstructorWithoutStudentDetailsDto(instructor.getId().toString(),instructor.getName(), instructor.getSurname(),Boolean.toString(instructor.isActive()));
    }
}
