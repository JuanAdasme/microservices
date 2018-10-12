package cl.ttpeople.microservices.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new StudentValidator());
	}
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@PostMapping
	public Student create(@Valid @RequestBody Student student) throws NotFoundException {
		return studentService.create(student);
	}
	
	
	@GetMapping("/all")
	public List<Student> listAll() {
		return studentService.listAll();
	}
	
	@GetMapping
	public Page<Student> findAll(Pageable pageable) {
		return studentService.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public Student findById(@PathVariable Integer id) {
		return studentService.findById(id);
	}
	
	@PutMapping("/{id}")
	public Student update(@PathVariable("id") Integer id,
											@Valid @RequestBody Student student) {
		return studentService.update(id, student);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		studentService.delete(id);
	}
	
	@GetMapping("/younger")
	public List<Student> findYounger() {
		return studentService.findYounger();
	}
	
	@GetMapping("/range")
	public List<List<Student>> findByRange() {
		return studentService.findByRange();
	}
}
