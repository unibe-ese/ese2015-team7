package org.sample.tests;

import org.junit.runner.RunWith;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.ISearchService;
import org.sample.controller.service.UserService;
import org.sample.model.UserCourseFormAttributeFactory;
import org.sample.model.UserRole;
import org.sample.model.TimeSlot;
import org.sample.model.TimeSlotFactory;
import org.sample.model.User;
import org.sample.model.UserCourseFormAttribute;
import org.sample.model.dao.UserCourseDao;
import org.sample.model.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.AutoPopulatingList;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/test/java/org/sample/tests/test/test.xml"})
public class UserServiceTest {
	@Autowired	UserDao userDao;
	@Autowired	UserCourseDao tutorDao;
	@Autowired	ISearchService searchService;
	@Autowired	UserService userService;
	private SignupForm signupForm;
	private User user, updatedUser;
	private ArrayList<TimeSlot> expectedTimeSlotArrayList;
	private ArrayList<UserCourseFormAttribute> expectedUserCourseFormAttributeArrayList;
	private BCryptPasswordEncoder passwordEncoder  = new BCryptPasswordEncoder();
	
	@Before
	public void setupSignup() 
	{		
		signupForm = new SignupForm();
		signupForm.setId((long) 1500);
		signupForm.setFirstName("Capitain");
		signupForm.setLastName("Awesome");
		signupForm.setEmail("CapAwe@CapAwe.awe");
		signupForm.setBiography("I try to be .......... awesome...");
		signupForm.setPassword("123456");
		signupForm.setTimeSlots( getNewTimeSlotList() );
		signupForm.setUserCourseList( getNewUserCourseFormAttributeList() );
	}
	
	@Before
	public void setupUser() 
	{
		user = new User();
		user.setId((long)1);
		user.setFirstName("Maria");
		user.setLastName("Magdalena");
		user.setEmail("maria.magdalena@movie.com");
		user.setBiography("Oh why, oh why. My biography is gonna change..");
		user.setPassword("654321");
		user.setEnabled(false);
		when(userDao.save(any(User.class))).then(returnsFirstArg());
	}
	
	@Before
	public void setupExpectedTimeSlotArrayList() {
		expectedTimeSlotArrayList = new ArrayList<TimeSlot>();
		expectedTimeSlotArrayList.addAll( getNewTimeSlotList() );
	}
	
	@Before
	public void setupExpectedUserCourseFormAttributeArrayList() {
		expectedUserCourseFormAttributeArrayList = new ArrayList<UserCourseFormAttribute>();
		expectedUserCourseFormAttributeArrayList.addAll( getNewUserCourseFormAttributeList() );
	}
	
	@Test
	public void testTheBasicUserServiceUserSaving()
	{
		updatedUser = userService.saveFrom(signupForm);
		Iterator<UserRole> roleItr = updatedUser.getUserRole().iterator();
		
		assertEquals("Capitain", updatedUser.getFirstName());
		assertEquals("Awesome", updatedUser.getLastName());
		assertEquals("CapAwe@CapAwe.awe", updatedUser.getEmail());
		assertEquals("I try to be .......... awesome...", updatedUser.getBiography());
		
		assertTrue (roleItr.hasNext());
		assertEquals( "ROLE_USER", roleItr.next().getRole() );
		assertFalse (roleItr.hasNext());
		
		assertTrue(updatedUser.isEnabled());
		assertTrue( passwordEncoder.matches("123456", updatedUser.getPassword()) );
		
		assertEquals(expectedTimeSlotArrayList.toString(), updatedUser.getTimeSlots().toString()); // don't compare references but string representation
	}
	
	@Test
	public void testTheBasicUserServiceUserUpdating()
	{
		updatedUser = userService.saveFrom(signupForm, user);
		Iterator<UserRole> roleItr = updatedUser.getUserRole().iterator();
		
		assertEquals("1", updatedUser.getId().toString());
		assertEquals("Capitain", updatedUser.getFirstName());
		assertEquals("Awesome", updatedUser.getLastName());
		assertEquals("CapAwe@CapAwe.awe", updatedUser.getEmail());
		assertEquals("I try to be .......... awesome...", updatedUser.getBiography());
		
		assertTrue (roleItr.hasNext());
		assertEquals( "ROLE_USER", roleItr.next().getRole() );
		assertFalse (roleItr.hasNext());
		
		assertTrue(updatedUser.isEnabled());
		assertTrue( passwordEncoder.matches("123456", updatedUser.getPassword()) );
		
		assertEquals(expectedTimeSlotArrayList.toString(), updatedUser.getTimeSlots().toString()); // don't compare references but string representation
	}
	
	@Test(expected = InvalidUserException.class)
	public void testEmptyName(){
		signupForm.setFirstName("a");
		signupForm.setLastName("asdf");
		userService.saveFrom(signupForm);
	}
	
	  ////////////////////
	 // Helper methods //
	////////////////////
	
	private AutoPopulatingList<TimeSlot> getNewTimeSlotList() 
	{
		AutoPopulatingList<TimeSlot> timeSlotList = new AutoPopulatingList<TimeSlot>(new TimeSlotFactory());
		TimeSlot expectedtimeSlot = new TimeSlot();
		expectedtimeSlot.setSemesterOrSemesterBreak("Autumn semester 2015");
		expectedtimeSlot.setDay("Monday");
		expectedtimeSlot.setStartTime("07:00");
		expectedtimeSlot.setEndTime("08:00");
		expectedtimeSlot.setRemove(false);
		timeSlotList.add( expectedtimeSlot );
		return timeSlotList;
	}
	
	private AutoPopulatingList<UserCourseFormAttribute> getNewUserCourseFormAttributeList() 
	{
		AutoPopulatingList<UserCourseFormAttribute> userCourseList = new AutoPopulatingList<UserCourseFormAttribute>(new UserCourseFormAttributeFactory());
		UserCourseFormAttribute userCourseOne = new UserCourseFormAttribute();
		userCourseOne.setUniversity("University of Bern");
		userCourseOne.setSubject("Computer Science");
		userCourseOne.setCourse("ESE");
		userCourseOne.setGrade("6");
		userCourseOne.setTeaching(true);
		userCourseList.add(userCourseOne);
		return userCourseList;
	}	
}
