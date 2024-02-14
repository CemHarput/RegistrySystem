package com.studentInfoSystem.system.grade.dto;

import com.studentInfoSystem.system.grade.model.Grade;

import java.math.BigDecimal;

public record AddGradeRequestDto(BigDecimal value) {

    public static Grade convertFromDto(AddGradeRequestDto addGradeRequestDto) {
        Grade grade = new Grade();
        grade.setValue(addGradeRequestDto.value());
        return grade;
    }
}
