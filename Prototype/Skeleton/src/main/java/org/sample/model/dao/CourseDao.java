package org.sample.model.dao;

import org.sample.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseDao extends CrudRepository<Course,Long> {

	Course findByCourseNameAndSubject(String course, String subject);
}