package com.studentInfoSystem.system.student.controller;


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

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<Page<StudentDto>> getAllBooks(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        logger.info("getAllBooks is started");
        Page<StudentDto> studentDtoPage = studentService.getAllStudents(page, size);
        return ResponseEntity.ok(studentDtoPage);
    }
    @DeleteMapping("/students/{studentId}/grades/{gradeId}")
    public ResponseEntity<String> deleteGradeFromAStudent(@PathVariable Long studentId, @PathVariable Long gradeId) {
        try {
            logger.info("deleteGradeFromAStudent is started");
            studentService.deleteGradeFromAStudent(studentId, gradeId);
            return new ResponseEntity<>("Grade deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("deleteGradeFromAStudent is failed");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/students/{studentId}/grades")
    public ResponseEntity<String> updateGradeFromAStudent(@PathVariable Long studentId, @RequestBody UpdateGradeRequestDto updateGradeRequestDto) {
        try {
            logger.info("updateGradeFromAStudent is started");
            studentService.updateGradeFromAStudent(studentId, updateGradeRequestDto);
            return new ResponseEntity<>("Grade updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("updateGradeFromAStudent is failed");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addStudent")
    public ResponseEntity<String> createStudent(@RequestBody AddStudentRequestDto addStudentRequestDto) {
        try {
            studentService.createStudent(addStudentRequestDto);
            return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
