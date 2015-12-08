package org.sample.controller.service;

import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.University;
import org.sample.model.User;
import org.sample.model.UserCourse;
import org.sample.model.dao.UserCourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SelectService implements ISelectService {
	
	@Autowired UserCourseDao userCourseDao;

	@Override
	@Transactional
	public UserCourse getUserCourseFromUserCourseId(long userCourseId) {
		UserCourse userCourse = userCourseDao.findByUserCourseId(userCourseId);
		return userCourse;
	}

	@Override
	public Course getCourseFromUserCourseId(long userCourseId) {
		UserCourse userCourse = getUserCourseFromUserCourseId(userCourseId);
		Course course = userCourse.getCourse();
		return course;
	}

	@Override
	public Subject getSubjectFromUserCourseId(long userCourseId) {
		Course course = getCourseFromUserCourseId(userCourseId);
		Subject subject = course.getSubject();
		return subject;
	}

	@Override
	public University getUniversityFromUserCourseId(long userCourseId) {
		Subject subject = getSubjectFromUserCourseId(userCourseId);
		University university = subject.getUniversity();
		return university;
	}

	@Override
	public User getUserFromUserCourseId(long userCourseId) {
		UserCourse userCourse = getUserCourseFromUserCourseId(userCourseId);
		User user = userCourse.getUser();
		return user;
	}

}
