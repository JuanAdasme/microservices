package cl.ttpeople.microservices.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.ttpeople.microservices.models.Course;

public interface CourseService {
	Course create(Course course);
	
	List<Course> listAll();
	
	Course update(Integer id, Course course);
	
	void delete(Integer id);
	
	Page<Course> findAll(Pageable pageable);
	
	Course findById(Integer id);
}
