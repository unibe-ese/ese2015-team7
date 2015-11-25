package org.sample.controller.service;

import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.University;
import org.sample.model.User;
import org.sample.model.UserCourse;

public interface ISelectService {
	
	public UserCourse getUserCourseFromUserCourseId(long userCourseId);
	public Course getCourseFromUserCourseId(long userCourseId);
	public Subject getSubjectFromUserCourseId(long userCourseId);
	public University getUniversityFromUserCourseId(long userCourseId);
	public User getUserFromUserCourseId(long userCourseId);
	

}
