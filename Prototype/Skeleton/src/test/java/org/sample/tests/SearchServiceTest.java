package org.sample.tests;

import org.junit.runner.RunWith;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.service.ISearchService;
import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.UserCourse;
import org.sample.model.University;
import org.sample.model.User;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.CourseDao;
import org.sample.model.dao.SubjectDao;
import org.sample.model.dao.UserCourseDao;
import org.sample.model.dao.UniversityDao;
import org.sample.model.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/test/java/org/sample/tests/test/test.xml"})
public class SearchServiceTest {
	
    @Autowired	UserDao userDao;
    @Autowired	AddressDao addDao;
    @Autowired	UniversityDao universityDao;
    @Autowired	SubjectDao subjectDao;
    @Autowired	CourseDao courseDao;
    @Autowired	UserCourseDao userCourseDao;
    @Autowired	ISearchService searchService;
    private SearchForm searchForm;
    private University uni, uni2, uni3;
    private Subject sub, sub2, sub3;
    private Course testCourse, course, course2, course3;
    private UserCourse userCourse, userCourse2;
    private User user, user2;
    
    @Before
    public void setUp(){
    	uni = new University();
    	uni.setUniversityName("Uni Bern");
    	
    	uni2 = new University();
    	uni2.setUniversityName("Uni Athens");

    	uni3 = new University();
    	uni3.setUniversityName("Uni Tanzania");
    	    	
    	sub = new Subject();
    	sub.setSubjectName("Info");
    	sub.setUniversity(uni);
    	
    	sub2 = new Subject();
    	sub2.setSubjectName("Greek");
    	sub2.setUniversity(uni2);
    	
    	sub3 = new Subject();
    	sub3.setSubjectName("");
    	sub3.setUniversity(uni3);
    	
    	course = new Course();
    	course.setCourseName("ESE");
    	course.setSubject(sub);

    	course2 = new Course();
    	course2.setCourseName("Flying");
    	course2.setSubject(sub2);
    	

    	course3 = new Course();
    	course3.setCourseName("Swirling");
    	course3.setSubject(sub3);

    	
    	user = new User();
    	user.setId(1l);
    	user.setFirstName("Darth");
    	
    	user2 = new User();
    	user.setId(2l);
    	user2.setFirstName("Vader");
    	
    	userCourse = new UserCourse();
    	userCourse.setUser(user);
    	userCourse.setCourse(course);
    	userCourse.setTeaching(true);
    	
    	userCourse2 = new UserCourse();
    	userCourse2.setUser(user2);
    	userCourse2.setCourse(course2);
    	userCourse2.setTeaching(true);

    	
    	searchForm = new SearchForm();
    	searchForm.setCourse("ESE");
    	searchForm.setSubject("Info");
    	searchForm.setUniversity("Uni Bern");
    	
    	when(userDao.save(any(User.class))).then(returnsFirstArg());
    	
    	when(universityDao.findByUniversityName(any(String.class))).thenReturn(uni);
    	when(subjectDao.findBySubjectNameAndUniversity(any(String.class),any(University.class))).thenReturn(sub);
		when(courseDao.findByCourseNameAndSubject(any(String.class),any(Subject.class))).thenReturn(course);
    }
    
    
    
    @Test
    public void testGetUniversities(){
    	
    	ArrayList<University> unis = new ArrayList<University>();
    	unis.add(uni2);
    	unis.add(uni3);
    	
    	when(universityDao.findAll()).thenReturn(unis);
    	ArrayList<University> testUnis = searchService.getUniversities();
    	
    	assertEquals(unis, testUnis);    	
    }
    

    @Test
    public void testGetSubjects(){
    	ArrayList<Subject> subs = new ArrayList<Subject>();
    	subs.add(sub2);
    	subs.add(sub3);
    	
    	when(subjectDao.findAll()).thenReturn(subs);
    	ArrayList<Subject> testSubs = searchService.getSubjects();
    	
    	assertEquals(subs, testSubs);    	
    }
    

    @Test
    public void testGetCourses(){
    	ArrayList<Course> courses = new ArrayList<Course>();
    	courses.add(course2);
    	courses.add(course3);
    	
    	when(courseDao.findAll()).thenReturn(courses);
    	ArrayList<Course> testCourses = searchService.getCourses();
    	
    	assertEquals(courses, testCourses);    	
    }
    

	@Test(expected = InvalidUserException.class)
    public void testCourseNotSelected(){
		searchForm.setCourse("Select Course");
		searchService.getTutorsFromSearchForm(searchForm);
	}
	
	@Test
    public void testGetTutorsFromSearchForm(){
    	Course otherCourse = new Course();
    	otherCourse.setCourseName("Flying");
    	otherCourse.setSubject(new Subject());
    	
    	
    	ArrayList<UserCourse> userCourseList = new ArrayList<UserCourse>();
    	userCourseList.add(userCourse);
    	
    	//when(userCourseDao.findByCourseAndTeaching(course, true)).thenReturn(userCourseList);
    	when(courseDao.findByCourseName("ESE")).thenReturn(course);
    	when(courseDao.findByCourseName("Flying")).thenReturn(otherCourse);
    	
		ArrayList<UserCourse> tutors = searchService.getTutorsFromSearchForm(searchForm);
		
		ArrayList<UserCourse> testTutors = new ArrayList<UserCourse>();
		testTutors.add(userCourse);
		assertEquals(testTutors, tutors);
    }
    
    
    @Test
    public void testGetCourse(){
    	testCourse = new Course();
    	testCourse = searchService.getCourse(searchForm);
    	
    	assertEquals(course, testCourse);
    	
    }

}
