package com.studentInfoSystem.system.grade.model;

import com.studentInfoSystem.system.student.model.Student;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Grade {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "student_id",insertable = false,updatable = false)
    private Long studentId;
    @Column(precision = 12, scale = 2)
    private BigDecimal value;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
