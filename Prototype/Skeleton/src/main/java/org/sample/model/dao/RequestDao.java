package org.sample.model.dao;

import org.sample.model.Course;
import org.sample.model.Request;
import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;

public interface RequestDao extends CrudRepository<Request,Long> {

	Request findByTutorAndStudent(User tutor, User student);

	Request findByTutorAndStudentAndCourse(User findByEmail, User findByEmail2, Course course);

}
