package org.sample.model.dao;

import org.sample.model.Course;
import org.sample.model.Subject;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course,Long> {

	Course findByCourseNameAndSubject(String course, Subject subject);

	Course findByCourseName(String courseName);

	Course findById(long id);

}