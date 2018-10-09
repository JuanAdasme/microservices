package cl.ttpeople.microservices.services;

import java.util.List;

import cl.ttpeople.microservices.models.Course;

public interface CourseService {
	Course create(Course course);
	
	List<Course> listAll();
	
	Course update(Integer id, Course course);
	
	void delete(Integer id);
}
