package com.studentInfoSystem.system.student.service;


import com.studentInfoSystem.system.grade.dto.AddGradeRequestDto;
import com.studentInfoSystem.system.grade.dto.UpdateGradeRequestDto;
import com.studentInfoSystem.system.grade.model.Grade;
import com.studentInfoSystem.system.grade.repository.GradeRepository;
import com.studentInfoSystem.system.instructor.model.Instructor;
import com.studentInfoSystem.system.instructor.repository.InstructorRepository;
import com.studentInfoSystem.system.student.dto.AddStudentRequestDto;
import com.studentInfoSystem.system.student.dto.StudentDto;
import com.studentInfoSystem.system.student.model.Student;
import com.studentInfoSystem.system.student.repository.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;


    private final InstructorRepository instructorRepository;

    public StudentService(StudentRepository studentRepository, GradeRepository gradeRepository, InstructorRepository instructorRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.instructorRepository = instructorRepository;
    }

    public void createStudent(AddStudentRequestDto addStudentRequestDto) {
        Student student = new Student();
        student.setName(addStudentRequestDto.name());
        student.setSurname(addStudentRequestDto.surname());
        Instructor instructor = instructorRepository.findById(Long.valueOf(addStudentRequestDto.instructorId()))
                .orElseThrow(() -> new RuntimeException("Instructor not found with ID: " + addStudentRequestDto.instructorId()));
        student.setInstructor(instructor);
        List<Grade> gradeList = new ArrayList<>();
        for (AddGradeRequestDto addGradeRequestDto : addStudentRequestDto.addGradeRequestDtoList()) {
            Grade grade = AddGradeRequestDto.convertFromDto(addGradeRequestDto);
            grade.setStudent(student);
            gradeList.add(grade);
        }
        student.setGrades(gradeList);
        studentRepository.save(student);
    }
    public void deleteGradeFromAStudent(Long studentId, Long gradeIdToBeDeleted) {
        Student student = findStudentById(studentId);

        List<Grade> grades = student.getGrades();
        Grade grade= findGradeById(student,gradeIdToBeDeleted);
        grades.removeIf(g -> g.getId().equals(gradeIdToBeDeleted));
        student.setGrades(grades);

        if (Objects.nonNull(grade)) {
            gradeRepository.delete(grade);
        }
    }
    public void updateGradeFromAStudent(Long studentId,Long gradeId,UpdateGradeRequestDto updateGradeRequestDto) {
        Student student = findStudentById(studentId);
        Grade grade = findGradeById(student, gradeId);

        grade.setValue(updateGradeRequestDto.value());
        studentRepository.save(student);
    }
    public void addAGradeToAStudent(Long studentId, AddGradeRequestDto addGradeRequestDto) {
        Student student = findStudentById(studentId);
        Grade grade = AddGradeRequestDto.convertFromDto(addGradeRequestDto);
        grade.setStudent(student);
        student.getGrades().add(grade);
        studentRepository.save(student);
    }
    private Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
    }

    private Grade findGradeById(Student student, Long gradeId) {
        return student.getGrades().stream()
                .filter(grade -> grade.getId().equals(gradeId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Grade not found with ID: " + gradeId));
    }
    public Page<StudentDto> getAllStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Student> studentPage = studentRepository.findAll(pageable);

        List<StudentDto> studentDtos = studentPage.getContent().stream()
                .map(StudentDto::convertFromStudent)
                .toList();

        return new PageImpl<>(studentDtos, pageable, studentPage.getTotalElements());
    }

}
