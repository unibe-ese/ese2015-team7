package org.sample.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.sample.model.Course;
import org.sample.model.User;
import org.sample.model.UserCourse;
import org.springframework.data.repository.CrudRepository;

public interface UserCourseDao extends CrudRepository<UserCourse,Long> {
	List<UserCourse> findByUser(User user);
	ArrayList<UserCourse> findByCourse(Course course);
	ArrayList<UserCourse> findByCourseAndGrade(Course course, int grade);
}