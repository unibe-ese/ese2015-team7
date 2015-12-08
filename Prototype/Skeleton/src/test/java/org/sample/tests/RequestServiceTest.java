package org.sample.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.controller.service.IRequestService;
import org.sample.controller.service.UserService;
import org.sample.model.Course;
import org.sample.model.Request;
import org.sample.model.User;
import org.sample.model.UserCourse;
import org.sample.model.dao.RequestDao;
import org.sample.model.dao.UserCourseDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/test/java/org/sample/tests/test/test.xml"})
public class RequestServiceTest {

	@Autowired 	RequestDao requestDao;
	@Autowired 	UserDao userDao;
	@Autowired	UserCourseDao userCourseDao;
	@Autowired	UserService userService;
	@Autowired	IRequestService requestService;
	String tutorEmail, studentEmail;
	Course course;
	User tutor, student, otherUser, otherTutor;
	Request expectedRequest;
	UserCourse userCourse, otherUserCourse;
	ArrayList<Request> expectedRequestList;

	@Before
    public void setUp()
	{    		
    	student = new User();
    	student.setId(1l);
    	student.setFirstName("first");
    	student.setLastName("last");
    	studentEmail = "test@test.test";
    	student.setEmail(studentEmail);
    	when(userDao.findByEmail(studentEmail)).thenReturn(student);
    	
    	tutor = new User();
    	tutor.setId(2l);
    	tutor.setFirstName("tutorFirst");
    	tutor.setLastName("tutorLast");
    	tutorEmail = "test@test.test";
    	tutor.setEmail(tutorEmail);

    	course = new Course();
    	course.setCourseName("ESE");
    	course.setId(1l);
    	
    	userCourse = new UserCourse();
    	userCourse.setUser(tutor);
    	userCourse.setCourse(course);
    	userCourse.setUserCourseId(1l);
    	when(userCourseDao.findByUserCourseId(1l)).thenReturn(userCourse);
    	
    	expectedRequest = new Request();
    	expectedRequest.setStudent(student);
    	expectedRequest.setUserCourse(userCourse);
		expectedRequest.setNewRequest(true);
		expectedRequest.setIsActiv(true);
		
    	expectedRequestList = new ArrayList<Request>();
    	expectedRequestList.add(expectedRequest);
    	//expectedRequestList.add(new Request());
    	
    	when(userDao.save(any(User.class))).then(returnsFirstArg());
		when(requestDao.save(any(Request.class))).then(returnsFirstArg());
    	//when(requestDao.findAll().iterator()).thenReturn(testRequestList.iterator());
    }
	
	@Test
	public void saveNewRequestTest(){    
    	when(requestDao.findByUserCourseIdAndStudent(1l, student)).thenReturn(null);
    	
		Request testRequest = requestService.saveRequest(1l, studentEmail);
		assertEquals(expectedRequest, testRequest);
	}
	
	@Test
	public void saveOldDeletedRequestTest(){
		Request oldRequest = new Request();
		oldRequest.setStudent(student);
		oldRequest.setUserCourse(userCourse);
		oldRequest.setIsDeleted(true);
    	when(requestDao.findByUserCourseIdAndStudent(1l, student)).thenReturn(oldRequest);
    	
		Request testRequest = requestService.saveRequest(1l, studentEmail);
		assertEquals(expectedRequest, testRequest);
	}
	
	@Test
	public void saveOldDeclinedRequestTest(){
		Request oldRequest = new Request();
		oldRequest.setStudent(student);
		oldRequest.setUserCourse(userCourse);
		oldRequest.setIsDeclined(true);
    	when(requestDao.findByUserCourseIdAndStudent(1l, student)).thenReturn(oldRequest);
    	
		Request testRequest = requestService.saveRequest(1l, studentEmail);
		assertEquals(expectedRequest, testRequest);
	}
	
	@Test
	public void getAllOutgoingRequestsTest(){
		when(requestDao.findByStudent(student)).thenReturn(expectedRequestList);
		
		ArrayList<Request> testRequestList = requestService.getAllOutgoingRequests(student);
		assertEquals(expectedRequestList, testRequestList);
	}
	
	@Test
	public void getAllIncomingRequestsTest(){
		otherUser = new User();
		otherUser.setId(3l);
		otherUser.setEmail("otherUserEmail");
		otherUser.setFirstName("otherUserFirst");
		otherUser.setLastName("otherUserLast");
		
		otherTutor = new User();
		otherTutor.setId(4l);
		otherTutor.setEmail("otherTutorEmail");
		otherTutor.setFirstName("otherTutorFirst");
		otherTutor.setLastName("otherTutorLast");
		
		otherUserCourse = new UserCourse();
		otherUserCourse.setUserCourseId(2l);
		otherUserCourse.setCourse(course);
		otherUserCourse.setUser(otherTutor);
		
		Request otherRequest = new Request();
		otherRequest.setStudent(otherUser);
		otherRequest.setUserCourse(otherUserCourse);
		
		ArrayList<Request> givenRequestList = (ArrayList<Request>) expectedRequestList.clone();
		givenRequestList.add(otherRequest);
		when(requestDao.findAll()).thenReturn(givenRequestList);
		
		ArrayList<Request> testRequestList = requestService.getAllIncomingRequests(tutor);
		assertEquals(expectedRequestList, testRequestList);		
	}
}
