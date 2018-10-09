package cl.ttpeople.microservices.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ttpeople.microservices.models.Student;
import cl.ttpeople.microservices.services.StudentService;
import cl.ttpeople.microservices.validators.StudentValidator;
import javassist.NotFoundException;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	private StudentService studentService;
	
	private StudentValidator validator;
	
	@Autowired
	public StudentController(StudentService studentService, StudentValidator validator) {
		this.studentService = studentService;
		this.validator = validator;
	}
	
	@PostMapping
	public ResponseEntity<Student> create(@Validated @RequestBody Student student) throws NotFoundException {
		Student newStudent = studentService.create(student);
		return new ResponseEntity<>(newStudent,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/all")
	public List<Student> listAll() {
		return studentService.listAll();
	}
	
	@PutMapping("/{id}")
	public Student update(@PathVariable("id") Integer id,
											@Valid @RequestBody Student student) {
		return studentService.update(id, student);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		studentService.delete(id);
	}
}
