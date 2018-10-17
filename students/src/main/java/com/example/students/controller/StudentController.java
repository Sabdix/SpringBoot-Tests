package com.example.students.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.students.exception.ResourceNotFoundException;
import com.example.students.models.Student;
import com.example.students.repository.StudentRepository;

@RestController
@RequestMapping("/miapp")
public class StudentController {
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> getAllStudents() {
	    return studentRepository.findAll();
	}
	
	@PostMapping("/students")
	public Student createNote(@Valid @RequestBody Student std) {
	    return studentRepository.save(std);
	}
	
	@GetMapping("/students/{id}")
	public Student getStudentById(@PathVariable(value = "id") Long stdId) {
	    return studentRepository.findById(stdId)
	            .orElseThrow(() -> new ResourceNotFoundException("Student", "id", stdId));
	}
	
	@PutMapping("/students/{id}")
	public Student updateStudent(@PathVariable(value = "id") Long stdId,
	                                        @Valid @RequestBody Student stdDetails) {

	    Student std = studentRepository.findById(stdId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", stdId));

	    std.setName(stdDetails.getName());
	    std.setAge(stdDetails.getAge());

	    Student updatedStudent = studentRepository.save(std);
	    return updatedStudent;
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long stdId) {
	    Student std = studentRepository.findById(stdId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", stdId));

	    studentRepository.delete(std);

	    return ResponseEntity.ok().build();
	}
}
