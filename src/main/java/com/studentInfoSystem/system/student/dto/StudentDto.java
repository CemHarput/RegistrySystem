package com.studentInfoSystem.system.student.dto;

import com.studentInfoSystem.system.grade.dto.AddGradeRequestDto;
import com.studentInfoSystem.system.grade.dto.UpdateGradeRequestDto;
import com.studentInfoSystem.system.student.model.Student;
import java.util.List;

public record StudentDto(Long id, String name, String surname, List<UpdateGradeRequestDto> grades,String instructorName) {

    public static StudentDto convertFromStudent(Student student){
        List<UpdateGradeRequestDto> gradeDtoList = student.getGrades().stream()
                .map(UpdateGradeRequestDto::convertFromGrade)
                .toList();
        return new StudentDto(student.getId(), student.getName(), student.getSurname(), gradeDtoList,student.getInstructor().getName());
    }
}
