package cl.ttpeople.microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ttpeople.microservices.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

}
