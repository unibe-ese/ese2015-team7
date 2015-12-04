package org.sample.controller.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.model.Course;
import org.sample.model.Request;
import org.sample.model.TimeSlot;
import org.sample.model.UserCourse;
import org.sample.model.UserCourseFormAttribute;
import org.sample.model.User;
import org.sample.model.UserRole;
import org.sample.model.dao.CourseDao;
import org.sample.model.dao.RequestDao;
import org.sample.model.dao.UserCourseDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService implements IUserDataService{
	
	@Autowired	UserDao userDao;
	@Autowired	UserCourseDao userCourseDao;
	@Autowired	ISearchService searchService;
	@Autowired	CourseDao courseDao;
	@Autowired	RequestDao requestDao;

	public User saveFrom(SignupForm signupForm, User userToUpdate) {
		String firstName = signupForm.getFirstName();
		String lastName = signupForm.getLastName();
		
		if(StringUtils.isEmpty(firstName)||StringUtils.isEmpty(lastName)){
			throw new InvalidUserException("Sorry, no empty names"); // throw exception
		}
		else if (firstName.length()<2|| lastName.length()<2) {
		    throw new InvalidUserException("Sorry, please enter names with more than 2 letters"); // throw exception
		}
		

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		User user = (userToUpdate == null) ? new User() : userToUpdate;
		user.setFirstName(signupForm.getFirstName());
		user.setLastName(signupForm.getLastName());
		user.setEmail(signupForm.getEmail());
		
		user.setBiography(signupForm.getBiography());
		
		ArrayList<TimeSlot> timeSlotList = new ArrayList<TimeSlot>();
		ListIterator<TimeSlot> iter = signupForm.getTimeSlots().listIterator();
		while(iter.hasNext())
		{
			TimeSlot tmp = iter.next();
			if(!tmp.isRemove())
				timeSlotList.add(tmp);
		}
		user.setTimeSlots(timeSlotList);

		String password = signupForm.getPassword();
		if(password.isEmpty())
			user.setPassword(user.getPassword());
		else
			user.setPassword(passwordEncoder.encode(password));

		user.setEnabled(true);

		Set<UserRole> userRole = new HashSet<UserRole>();
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRole.add(role);

		user.setUserRole(userRole);

		user = userDao.save(user); // save object to DB

		return user;
	}
	
	public User saveFrom(SignupForm signupForm){
		return saveFrom(signupForm, null);
	}
	
	public void createAndSaveUserCoursesFromForm(SignupForm signupForm, User user) {		
		for( UserCourseFormAttribute userCourseFormAttribute : signupForm.getUserCourseList() ) {
			if(userCourseFormAttribute.getCourse() == null || userCourseFormAttribute.getCourse().equals("None"))
				continue;
			
			Course course =  courseDao.findByCourseName(userCourseFormAttribute.getCourse());
			if(course==null)
			{
				continue;
			}
			UserCourse givenUserCourse = userCourseDao.findByUserAndCourse(user, course);
			boolean givenUserCourseExists = (givenUserCourse!=null);
			
			if(!userCourseFormAttribute.isRemove()&&!givenUserCourseExists){
				givenUserCourse = new UserCourse();
				givenUserCourse.setUser(user);
				givenUserCourse.setCourse(course);
				givenUserCourse.setGrade( getGradeFromString(userCourseFormAttribute.getGrade()));
				givenUserCourse.setTeaching(userCourseFormAttribute.isTeaching());
				userCourseDao.save(givenUserCourse); // save object to DB
			}
			else if(!userCourseFormAttribute.isRemove()&&givenUserCourseExists){
				givenUserCourse.setGrade( getGradeFromString(userCourseFormAttribute.getGrade()));
				givenUserCourse.setTeaching(userCourseFormAttribute.isTeaching());
				userCourseDao.save(givenUserCourse); // save object to DB
			}
			else if(userCourseFormAttribute.isRemove()&&givenUserCourseExists) {
				ArrayList<Request> relatedRequests = requestDao.findByUserCourseId(givenUserCourse.getUserCourseId());
				requestDao.delete(relatedRequests);
				userCourseDao.delete(givenUserCourse);
			}
		}
	}
	
	public User getUserById(Long userId) {
		return userDao.findOne(userId);
	    }

    public User getUserByEmail(String email) {
    	return userDao.findByEmail(email);
    }
    
    public User getPrincipalUser(){
    	return getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    public float getGradeFromString(String grade){
    	return Float.parseFloat(grade);
    }
    
}
