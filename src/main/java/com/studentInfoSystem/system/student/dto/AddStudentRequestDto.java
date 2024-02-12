package com.studentInfoSystem.system.student.dto;

import com.studentInfoSystem.system.grade.dto.AddGradeRequestDto;
import com.studentInfoSystem.system.student.model.Student;

import java.util.List;

public record AddStudentRequestDto(String name, String surname, List<AddGradeRequestDto> addGradeRequestDtoList,String instructorId) {

}
