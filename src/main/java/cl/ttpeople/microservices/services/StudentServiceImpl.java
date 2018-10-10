package cl.ttpeople.microservices.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.ttpeople.microservices.exceptions.NotFoundException;
import cl.ttpeople.microservices.models.Course;
import cl.ttpeople.microservices.models.Student;
import cl.ttpeople.microservices.repositories.CourseRepository;
import cl.ttpeople.microservices.repositories.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService {
	
	StudentRepository studentRepository;
	
	CourseRepository courseRepository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}
	
	@Override
	public Student create(Student student) throws NotFoundException {
		if(student.getCourse() != null && student.getCourse().getId() != null) {
			Course course = courseRepository.findById(student.getCourse().getId())
					.orElse(null);
			if(course == null)
				throw new NotFoundException("Course not found");
			student.setCourse(course);
		}
		else {
			throw new IllegalArgumentException();
		}
		return studentRepository.save(student);
	}

	@Override
	public List<Student> listAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student update(Integer id, Student student) {
		Student savedStudent = studentRepository.findById(id)
				.orElse(null);
		if(savedStudent != null) {
			if(student.getCourse() != null && student.getCourse().getId() != null) {
				Course course = courseRepository.findById(student.getCourse().getId())
								.orElse(null);
				if(course != null) {
					savedStudent.setCourse(course);
				}
				else {
					throw new NotFoundException(String.format("Course with ID %d not found!", student.getCourse().getId()));
				}
			}
			savedStudent.setRut(student.getRut());
			savedStudent.setAge(student.getAge());
			return studentRepository.save(savedStudent);
		}
		else {
			throw new NotFoundException(String.format("Student with ID %d not found!", id));
		}
	}

	@Override
	public void delete(Integer id) {
		Student found = studentRepository.findById(id)
						.orElse(null);
		if(found == null)
			throw new NotFoundException(String.format("Student with ID %d not found!", id));
		studentRepository.deleteById(id);
	}

	@Override
	public Page<Student> findAll(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	@Override
	public Student findById(Integer id) {
		Student found = studentRepository.findById(id)
						.orElse(null);
		if(found == null) {
			throw new NotFoundException(String.format("Student with ID %d not found!", id));
		}
		return found;
	}
	
	@Override
	public List<Student> findYounger() {
		List<Student> allStudents = studentRepository.findAll();
		List<Student> youngerStudents = new ArrayList<>();
		allStudents.forEach(s -> {if(s.getAge() >= 18 && s.getAge() <= 25) youngerStudents.add(s);});
		return youngerStudents;
	}
}
