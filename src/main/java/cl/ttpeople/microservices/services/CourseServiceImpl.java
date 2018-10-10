package cl.ttpeople.microservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.ttpeople.microservices.exceptions.NotFoundException;
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
		else {
			throw new NotFoundException(String.format("Course with ID %d not found!", id));
		}
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	@Override
	public Page<Course> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@Override
	public Course findById(Integer id) {
		Course found = repository.findById(id)
						.orElse(null);
		System.out.println("Course: " + found);
		if(found == null) 
			throw new NotFoundException(String.format("Course with ID %d not found!", id));
		return found;
	}
}
