package com.studentInfoSystem.system.student.dto;

import com.studentInfoSystem.system.grade.dto.AddGradeRequestDto;
import com.studentInfoSystem.system.grade.model.Grade;
import com.studentInfoSystem.system.student.model.Student;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record StudentDto(Long id, String name, String surname, List<AddGradeRequestDto> grades) {

    public static StudentDto convertFromStudent(Student student){
        List<AddGradeRequestDto> gradeDtoList = student.getGrades().stream()
                .map(AddGradeRequestDto::convertFromGrade)
                .toList();
        return new StudentDto(student.getId(), student.getName(), student.getSurname(), gradeDtoList);
    }
}
