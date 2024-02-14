package com.studentInfoSystem.system.instructor.dto;

import com.studentInfoSystem.system.instructor.model.Instructor;
import com.studentInfoSystem.system.student.dto.StudentDto;
import com.studentInfoSystem.system.student.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public record InstructorDto( String name, String surname, List<StudentDto> students) {

    public static InstructorDto convertFromInstructor(Instructor instructor){
        List<StudentDto> studentDtos = instructor.getStudents().stream()
                .map(InstructorDto::convertToDto)
                .toList();

        return new InstructorDto(instructor.getName(), instructor.getSurname(), studentDtos);
    }

    private static StudentDto convertToDto(Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getSurname(),null ,null);
    }

}
