package org.sample.tests;


import org.junit.runner.RunWith;
import org.sample.controller.SearchController;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.service.SearchService;
import org.sample.controller.service.UserService;
import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.University;
import org.sample.model.User;
import org.sample.model.UserCourse;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.CourseDao;
import org.sample.model.dao.SubjectDao;
import org.sample.model.dao.UniversityDao;
import org.sample.model.dao.UserCourseDao;
import org.sample.model.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/test/java/org/sample/tests/test/searchControllerTest.xml"})
public class SearchControllerTest {
	
    @Autowired	UserDao userDao;
    @Autowired	AddressDao addDao;
    @Autowired	UniversityDao universityDao;
    @Autowired	SubjectDao subjectDao;
    @Autowired	CourseDao courseDao;
    @Autowired	UserCourseDao userCourseDao;
    @Autowired  UserService	userService;	
    private University uni, uni2, uni3;
    private Subject sub, sub2, sub3;
    private Course course, course2, course3;
    private UserCourse userCourse, userCourse2, userCourse3;
    private User user, user2, user3, principal;
    @Autowired SearchController searchController;
    @Autowired SearchService searchService;
    
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
    	user.setFirstName("Darth");
    	user.setLastName("Vader");
    	user.setId(1l);
    	
    	user2 = new User();
    	user2.setFirstName("Luke");
    	user2.setLastName("Skywalker");
    	user2.setId(2l);	
    	
    	user3 = new User();
    	user3.setFirstName("Darth");
    	user3.setLastName("Sidius");
    	user3.setId(3l);
    	
    	userCourse = new UserCourse();
    	userCourse.setUser(user);
    	userCourse.setCourse(course);

    	userCourse2 = new UserCourse();
    	userCourse2.setUser(user2);
    	userCourse2.setCourse(course2);
    	
    	userCourse3 = new UserCourse();
    	userCourse3.setUser(user3);
    	userCourse3.setCourse(course);
    	
    	principal = new User();
    	principal.setFirstName("Hans");
    	principal.setLastName("Solo");
    	when(userService.getPrincipalUser()).thenReturn(principal);
    	
    	when(userDao.save(any(User.class))).then(returnsFirstArg());
    	
    	when(universityDao.findByUniversityName(any(String.class))).thenReturn(uni);
    	when(subjectDao.findBySubjectNameAndUniversity(any(String.class),any(University.class))).thenReturn(sub);
		when(courseDao.findByCourseNameAndSubject(any(String.class),any(Subject.class))).thenReturn(course);
    }
    
    
    
    @Test
    public void testSearchUniversitiesSubjectsAndCourses(){
    	
    	ArrayList<University> unis = new ArrayList<University>();
    	unis.add(uni2);
    	unis.add(uni3);
    	
    	when(searchService.getUniversities()).thenReturn(unis);

    	ArrayList<Subject> subs = new ArrayList<Subject>();
    	subs.add(sub2);
    	subs.add(sub3);
    	
    	when(searchService.getSubjects()).thenReturn(subs);
    	
    	ArrayList<Course> courses = new ArrayList<Course>();
    	courses.add(course2);
    	courses.add(course3);

    	when(searchService.getCourses()).thenReturn(courses);
    	
    	ModelAndView testModel = searchController.searchUniversitiesSubjectsAndCourses();
    	
    	assertEquals("search", testModel.getViewName());
    	assertEquals(unis, testModel.getModel().get("universities"));
    	assertEquals(subs, testModel.getModel().get("subjects"));
    	assertEquals(courses, testModel.getModel().get("courses"));
    }
    
    @Test
    public void testResults(){
    	
    	ArrayList<UserCourse> userCourses = new ArrayList<UserCourse>();
    	userCourses.add(userCourse);
    	userCourses.add(userCourse3);
    	    	
    	Course searchedCourse = course;
    	    	
    	SearchForm searchForm = new SearchForm();
    	searchForm.setCourse(searchedCourse.getCourseName());

    	when(searchService.getTutorsFromSearchForm(searchForm)).thenReturn(userCourses);
    	when(searchService.getCourse(searchForm)).thenReturn(searchedCourse);
    	
    	ModelAndView testModel = searchController.results(searchForm);
    	
    	assertEquals("results", testModel.getViewName());
    	assertEquals(userCourses, testModel.getModel().get("userCourses"));
    	assertEquals(searchedCourse, testModel.getModel().get("searchedCourse"));
    	
    	
    }

}
