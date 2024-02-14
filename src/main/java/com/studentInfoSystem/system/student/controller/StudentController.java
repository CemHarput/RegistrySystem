package com.studentInfoSystem.system.student.controller;


import com.studentInfoSystem.system.grade.dto.AddGradeRequestDto;
import com.studentInfoSystem.system.grade.dto.UpdateGradeRequestDto;
import com.studentInfoSystem.system.student.dto.AddStudentRequestDto;
import com.studentInfoSystem.system.student.dto.StudentDto;
import com.studentInfoSystem.system.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentDto>> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        logger.info("getAllBooks is started");
        Page<StudentDto> studentDtoPage = studentService.getAllStudents(page, size);
        return ResponseEntity.ok(studentDtoPage.getContent());
    }
    @DeleteMapping("/student/{studentId}/grades/{gradeId}")
    public ResponseEntity<String> deleteGradeFromAStudent(@PathVariable String studentId, @PathVariable String gradeId) {
        try {
            logger.info("deleteGradeFromAStudent is started");
            studentService.deleteGradeFromAStudent(studentId, gradeId);
            logger.info("Grade deleted successfully");
            return new ResponseEntity<>("Grade deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("deleteGradeFromAStudent is failed");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/student/{studentId}/grades/{gradeId}")
    public ResponseEntity<String> updateGradeFromAStudent(@PathVariable Long studentId,@PathVariable Long gradeId, @RequestBody UpdateGradeRequestDto updateGradeRequestDto) {
        try {
            logger.info("updateGradeFromAStudent is started");
            studentService.updateGradeFromAStudent(studentId,gradeId,updateGradeRequestDto);
            logger.info("Grade updated successfully");
            return new ResponseEntity<>("Grade updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("updateGradeFromAStudent is failed");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addStudent")
    public ResponseEntity<String> createStudent(@RequestBody AddStudentRequestDto addStudentRequestDto) {
        try {
            logger.info("createStudent is started");
            studentService.createStudent(addStudentRequestDto);
            logger.info("Student created successfully");
            return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("createStudent is failed ");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/student/{studentId}")
    public ResponseEntity<String> addAGradeToAStudent(@PathVariable Long studentId,@RequestBody AddGradeRequestDto addGradeRequestDto) {
        try {
            logger.info("addAGradeToAStudent is started");
            studentService.addAGradeToAStudent(studentId,addGradeRequestDto);
            logger.info("Grade added successfully");
            return new ResponseEntity<>("Grade added successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            logger.error("addAGradeToAStudent is failed ");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
