package cl.ttpeople.microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cl.ttpeople.microservices.models.Student;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student,Integer> {

}
