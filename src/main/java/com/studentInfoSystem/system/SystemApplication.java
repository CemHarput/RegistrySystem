package com.studentInfoSystem.system;

import com.studentInfoSystem.system.instructor.model.Instructor;
import com.studentInfoSystem.system.instructor.repository.InstructorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SystemApplication implements CommandLineRunner {


	private final InstructorRepository instructorRepository;

    public SystemApplication(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Instructor cemInstructor = new Instructor("Cem","Harputoglu",new ArrayList<>(),true);
		Instructor ahmetInstructor = new Instructor("Ahmet","TestSurname",new ArrayList<>(),true);
		instructorRepository.saveAll(Arrays.asList(cemInstructor,ahmetInstructor));
	}
}
