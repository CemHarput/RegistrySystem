package com.studentInfoSystem.system.student.service;


import com.studentInfoSystem.system.grade.dto.AddGradeRequestDto;
import com.studentInfoSystem.system.grade.dto.UpdateGradeRequestDto;
import com.studentInfoSystem.system.grade.model.Grade;
import com.studentInfoSystem.system.instructor.model.Instructor;
import com.studentInfoSystem.system.instructor.repository.InstructorRepository;
import com.studentInfoSystem.system.student.dto.AddStudentRequestDto;
import com.studentInfoSystem.system.student.dto.StudentDto;
import com.studentInfoSystem.system.student.model.Student;
import com.studentInfoSystem.system.student.repository.StudentRepository;
import org.hibernate.sql.Update;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;

    public StudentService(StudentRepository studentRepository, InstructorRepository instructorRepository) {
        this.studentRepository = studentRepository;
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

        List<Grade> updatedGrades = student.getGrades().stream()
                .filter(grade -> !grade.getId().equals(gradeIdToBeDeleted))
                .collect(Collectors.toList());

        student.setGrades(updatedGrades);
        studentRepository.save(student);
    }
    public void updateGradeFromAStudent(Long studentId, UpdateGradeRequestDto updateGradeRequestDto) {
        Student student = findStudentById(studentId);
        Grade grade = findGradeById(student, updateGradeRequestDto.id());

        grade.setValue(updateGradeRequestDto.value());
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
    public Page<StudentDto> getAllStudents(int page,int size){
        Sort sortByIdDesc = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sortByIdDesc);

        Page<Student> studentPage = studentRepository.findAll(pageable);

        List<StudentDto> studentDtos = studentPage.getContent().stream().map(StudentDto::convertFromStudent).toList();

        return new PageImpl<>(studentDtos,pageable,studentPage.getTotalElements());

    }

}
