package com.springtest.dev2.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.dev2.exception.ResourceNotFoundException;
import com.springtest.dev2.repository.StudentRepository;
import com.springtest.dev2.model.Student;

@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	StudentRepository studentRepository;

	// 01. Using Native Query
	@GetMapping("/student")
	public List<Student> getAllStudent() {
		// return studentRepository.findAll();
		return studentRepository.findByNativeQuery();
	}

	// 01. Using Native Query
	@GetMapping("/student/getByAgeGreaterThenEqual/{age}")
	public List<Student> getStudentByAgeGreaterThanEqual(@PathVariable(value = "age") Integer age) {
		return studentRepository.findByAgeGreaterThanEqual(age);
	}

	// 02. Using Java Stream Filtering and Native Query
	@GetMapping("/student/getAllStudentAgeEquals/{age}")
	public List<Student> getCountStudentAgeEquals(@PathVariable(value = "age") Integer age) {
		List<Student> studentList = (List<Student>) studentRepository.findByNativeQuery()
									.stream()
									.filter(Student -> Student.getAge().equals(age))
									.collect(Collectors.toList());
		return studentList;
	}

	@GetMapping("/student/getbyid/{id}")
	public Optional<Student> getStudentById(@PathVariable(value = "id") String id) {
		return studentRepository.findById(id);
	}

	@DeleteMapping("/student/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") String id) {
		Student student = studentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
		
		studentRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	// 03. Update Using Native Query
	@PutMapping("/student/updatebyId/{id}")
	public Student updateStudent(@PathVariable(value = "id") String id, @Valid @RequestBody Student sd){
		Student student = studentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

//		student.setName(sd.getName()+"_updatetest");
//        Student studentUpdate = studentRepository.save(student);
//        return studentUpdate;
		
		studentRepository.updateStudentById(sd.getName(), id);
		
		student = studentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
		return student;
	}
}
