package org.sample.controller.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.model.TimeSlot;
import org.sample.model.UserCourse;
import org.sample.model.UserCourseFormAttribute;
import org.sample.model.User;
import org.sample.model.UserRole;
import org.sample.model.dao.CourseDao;
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

	public SignupForm saveFrom(SignupForm signupForm, User userToUpdate) {
		String firstName = signupForm.getFirstName();
		String lastName = signupForm.getLastName();
		
		if(StringUtils.isEmpty(firstName)||StringUtils.isEmpty(lastName)){
			throw new InvalidUserException("Sorry, no empty names"); // throw exception
		}
		else if (firstName.length()<2|| lastName.length()<2) {
		    throw new InvalidUserException("Sorry, Please Enter Names with more than 2 Letters"); // throw exception
		}
		

		BCryptPasswordEncoder password = new BCryptPasswordEncoder();

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

		user.setPassword(password.encode(signupForm.getPassword()));

		user.setEnabled(true);

		Set<UserRole> userRole = new HashSet<UserRole>();
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRole.add(role);

		user.setUserRole(userRole);

		user = userDao.save(user); // save object to DB

		signupForm.setId(user.getId());

		return signupForm;
	}
	
	public SignupForm saveFrom(SignupForm signupForm){
		return saveFrom(signupForm, null);
	}
	
	public void createAndSaveUserCourseFromForm(SignupForm signupForm, User user) {		
		for( UserCourseFormAttribute userCourseFormAttribute : signupForm.getUserCourseList() ) {
			if(userCourseFormAttribute.getCourse() == null || userCourseFormAttribute.getCourse().equals("None"))
				break;
			
			UserCourse tmpUserCourse = new UserCourse();
			tmpUserCourse.setUser(user);
			tmpUserCourse.setCourse( courseDao.findByCourseName(userCourseFormAttribute.getCourse()) );
			tmpUserCourse.setGrade( Integer.parseInt(userCourseFormAttribute.getGrade()) );
			tmpUserCourse.setTeaching(userCourseFormAttribute.isTeaching() );
			
			if(!userCourseFormAttribute.isRemove() && (userCourseFormAttribute.isTeaching() || !userCourseFormAttribute.getGrade().equals("0")) ){
				userCourseDao.save(tmpUserCourse); // save object to DB
			} else {
				userCourseDao.delete(tmpUserCourse);
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

	public boolean validatePassword(String password, String passwordVerify) {
		if (password.equals(passwordVerify))
			return true;
		else
		return false;
	}
    
}
