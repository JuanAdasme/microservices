package cl.ttpeople.microservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ttpeople.microservices.models.Course;
import cl.ttpeople.microservices.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	private CourseService service;
	
	@Autowired
	public CourseController(CourseService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Course> create(@RequestBody Course course) {
		Course createdCourse = service.create(course);
		return new ResponseEntity<>(createdCourse,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> listAll() {
		List<Course> courses = service.listAll();
		return new ResponseEntity<>(courses,HttpStatus.OK);
	}
	
	@GetMapping
	public Page<Course> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public Course findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> update(@PathVariable Integer id, 
									@RequestBody Course course) {
		Course newCourse = service.update(id, course);
		return new ResponseEntity<>(newCourse,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		service.delete(id);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
}
