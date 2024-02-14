package com.studentInfoSystem.system.grade.dto;

import com.studentInfoSystem.system.grade.model.Grade;

import java.math.BigDecimal;

public record UpdateGradeRequestDto(String id,BigDecimal value) {
    public static UpdateGradeRequestDto convertFromGrade(Grade grade) {
        return new UpdateGradeRequestDto(grade.getId().toString(), grade.getValue());
    }
}
