package com.studentInfoSystem.system.instructor.controller;

import com.studentInfoSystem.system.instructor.dto.InstructorDto;
import com.studentInfoSystem.system.instructor.dto.InstructorWithoutStudentDetailsDto;
import com.studentInfoSystem.system.instructor.service.InstructorService;
import com.studentInfoSystem.system.student.controller.StudentController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class InstructorController {

    private static final Logger logger = LoggerFactory.getLogger(InstructorController.class);
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("instructors")
    public ResponseEntity<List<InstructorWithoutStudentDetailsDto>> getAllInstructors() {
        logger.info("getAllInstructors is started");
        List<InstructorWithoutStudentDetailsDto> instructors = instructorService.getAllInstructors();
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<List<InstructorDto>> getAnInstructorsWithDetails(@PathVariable Long instructorId) {
        try {
            logger.info("getAnInstructorsWithDetails is started");
            List<InstructorDto> instructor = instructorService.getAnInstructorsWithDetails(instructorId);
            return new ResponseEntity<>(instructor, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.info("getAnInstructorsWithDetails is failed");
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
    }




}
