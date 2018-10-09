package cl.ttpeople.microservices.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.ttpeople.microservices.models.Student;
import javassist.NotFoundException;

public interface StudentService {
	Student create(Student student) throws NotFoundException;
	
	List<Student> listAll();
	
	Student update(Integer id, Student student);
	
	void delete(Integer id);
	
	Page<Student> findAll(Pageable pageable);
	
	Student findById(Integer id);
}
