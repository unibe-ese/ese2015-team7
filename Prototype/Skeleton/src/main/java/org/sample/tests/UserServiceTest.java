
package org.sample.tests;

import org.junit.runner.RunWith;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.SearchService;
import org.sample.controller.service.UserService;
import org.sample.model.Grade;
import org.sample.model.GradeFactory;
import org.sample.model.User;
import org.sample.model.dao.TutorDao;
import org.sample.model.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.AutoPopulatingList;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test/test.xml"})
public class UserServiceTest {
	@Autowired	UserDao userDao;
	@Autowired	TutorDao tutorDao;
	@Autowired	SearchService searchService;
	@Autowired	UserService userService;
	private SignupForm signupForm, testSignupForm;
	private Grade grade;
	private AutoPopulatingList<Grade> grades;
	
	@Before
	public void setupSignup(){
		grade = new Grade();
		grade.setId((long) 1000);
		grade.setUniversity("Uni Bern");
		grade.setSubject("IT");
		grade.setCourse("ESE");
		grade.setGrade("6");
		
		grades = new AutoPopulatingList<Grade>(new GradeFactory());
		grades.add(grade);
		
		signupForm = new SignupForm();
		signupForm.setId((long) 1500);
		signupForm.setName("Capitain Awesome");
		signupForm.setEmail("CapAwe@CapAwe.awe");
		signupForm.setBiography("I try to be .......... awesome...");
		signupForm.setPassword("123456");
		signupForm.setGrades(grades);
		
		when(userDao.save(any(User.class))).then(returnsFirstArg());
	}
	
	@Test
	public void testTheBasicUserServiceSaving(){
		testSignupForm = userService.saveFrom(signupForm, null);
		
		assertEquals(testSignupForm.getName(),"Capitain Awesome");
		assertEquals(testSignupForm.getEmail(),"CapAwe@CapAwe.awe");
		assertEquals(testSignupForm.getBiography(),"I try to be .......... awesome...");
		assertEquals(testSignupForm.getPassword(),"123456");
	}
	
	@Test
	public void testTheSavingOfGrades(){
		testSignupForm = userService.saveFrom(signupForm, null);
		AutoPopulatingList<Grade> testList = new AutoPopulatingList<Grade>(new GradeFactory());
		testList.add(grade);
		assertEquals(testSignupForm.getGrades(), testList);
	}
	
	
	@Test(expected = InvalidUserException.class)
	public void testEmptyName(){
		signupForm.setName("");
		userService.saveFrom(signupForm);
		
	}
	
}
