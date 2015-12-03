package org.sample.model.dao;

import java.util.ArrayList;

import org.sample.model.Course;
import org.sample.model.User;
import org.sample.model.UserCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>This is the interface between the database and the services concerning UserCourses.</p>
 * It provides basic methods as storing, deleting, updating and getting entries in the database.
 * 
 * @author Team7
 *
 */
public interface UserCourseDao extends CrudRepository<UserCourse,Long> {
	
	UserCourse findByUserCourseId(long userCourseId);
	
	@Query("Select uc From UserCourse uc WHERE uc.user=?1")
	ArrayList<UserCourse> findByUser(User user);
	
	@Query("Select uc From UserCourse uc WHERE uc.user=?1 AND uc.course=?2")
	UserCourse findByUserAndCourse(User user, Course course);
	
	@Query("SELECT uc FROM UserCourse uc WHERE uc.course= ?1 AND uc.teaching= ?3 AND uc.grade>= ?2")
	ArrayList<UserCourse> findByCourseAndTeachingAndMinimumGrade(Course course, float grade, boolean teaching);
	
	@Query("SELECT uc FROM UserCourse uc WHERE uc.teaching= ?2 AND uc.grade>= ?1")
	ArrayList<UserCourse> findByTeachingAndMinimumGrade(float grade, boolean teaching);
}