package org.sample.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.sample.model.Course;
import org.sample.model.User;
import org.sample.model.UserCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserCourseDao extends CrudRepository<UserCourse,Long> {
	List<UserCourse> findByUser(User user);
	ArrayList<UserCourse> findByCourse(Course course);
	ArrayList<UserCourse> findByCourseAndGrade(Course course, int grade);
	ArrayList<UserCourse> findByCourseAndTeaching(Course course, boolean teaching);
	ArrayList<UserCourse> findByCourseAndGradeAndTeaching(Course course, int grade, boolean teaching);
	
	@Query("SELECT uc FROM UserCourse uc WHERE uc.course= ?1 AND uc.teaching= ?3 AND uc.grade>= ?2")
	ArrayList<UserCourse> findByCourseAndTeachingAndMinimumGrade(Course course, int grade, boolean teaching);
	
	@Query("SELECT uc FROM UserCourse uc WHERE uc.teaching= ?2 AND uc.grade>= ?1")
	ArrayList<UserCourse> findByTeachingAndMinimumGrade(int grade, boolean teaching);
}