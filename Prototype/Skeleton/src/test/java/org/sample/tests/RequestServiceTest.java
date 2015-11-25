package org.sample.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.sample.controller.service.RequestService;
import org.sample.model.Course;
import org.sample.model.Request;
import org.sample.model.User;
import org.sample.model.UserCourse;
import org.sample.model.dao.RequestDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/test/java/org/sample/tests/test/test.xml"})
public class RequestServiceTest {

	@Autowired 	RequestDao requestDao;
	@Autowired 	UserDao userDao;
	String tutorEmail;
	String studentEmail;
	Course course;
	User principal;
	User tutor;
	User student;
	Request request;
	User testUser;
	ArrayList<Request> testRequestList;
	RequestService requestService;


	
	
	@Before
    public void setUp(){
		requestService = new RequestService();
    	tutorEmail = "test@test.test";
    	studentEmail = "test@test.test";
    	course = new Course();
    	principal = new User();
    	tutor = new User();
    	student = new User();
    	testUser = new User();
    	testRequestList = new ArrayList<Request>();
    	request = new Request();
    	
    	 
    	testRequestList.add(request);
    	testRequestList.add(new Request());
    	course.setCourseName("ESE");
    	
    	when(userDao.save(any(User.class))).then(returnsFirstArg());
    	
    	when(requestDao.findByUserCourseIdAndStudent(any(int.class), any(User.class))).thenReturn(request);
    	when(requestDao.findByUserCourseIdAndStudent(any(int.class), any(User.class))).thenReturn(request);
    	//when(requestDao.findAll().iterator()).thenReturn(testRequestList.iterator());
    }
	
	
	
	@Test
	public void saveRequestTest(){
	}
	@Test
	public void getAllMyRequestsTest(){
	}
	@Test
	public void getAllRequestsTest(){
	}
	@Test
	public void deleteRequestTest(){
	}
	
	// needs to be updated
	@Test
	public void acceptRequestTest(){
		Request req=requestDao.findByUserCourseIdAndStudent(userCourseId, student);
		assertEquals(new Request(), req);
	}
	
	// needs to be updated
	@Test
	public void declineRequestTest(){
		
		ArgumentCaptor<Request> argument = ArgumentCaptor.forClass(Request.class);
		
		
		//requestService.acceptRequest(tutor, student);
		
		
		verify(requestDao).save(argument.capture());
		
		assertEquals(true, argument.getValue().getIsDeclined());
		   
	}
	
}
