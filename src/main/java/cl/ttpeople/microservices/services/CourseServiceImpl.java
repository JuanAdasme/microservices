package cl.ttpeople.microservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ttpeople.microservices.models.Course;
import cl.ttpeople.microservices.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	private CourseRepository repository;
	
	@Autowired
	public CourseServiceImpl(CourseRepository repository) {
		this.repository = repository;
	}

	@Override
	public Course create(Course course) {
		return repository.save(course);
	}

	@Override
	public List<Course> listAll() {
		return repository.findAll();
	}

	@Override
	public Course update(Integer id, Course course) {
		Course savedCourse = repository.findById(id)
								.orElse(null);
		if(savedCourse != null) {
			savedCourse.setName(course.getName());
			return repository.save(savedCourse);
		}
		return null;
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
		
	}
}
