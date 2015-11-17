
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
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test/searchControllerTest.xml"})
public class SearchControllerTest {
	
    @Autowired	UserDao userDao;
    @Autowired	AddressDao addDao;
    @Autowired	UniversityDao universityDao;
    @Autowired	SubjectDao subjectDao;
    @Autowired	CourseDao courseDao;
    @Autowired	UserCourseDao userCourseDao;
    @Autowired  UserService	userService;	
    private SearchForm searchForm;
    private University uni, uni2, uni3;
    private Subject sub, sub2, sub3;
    private Course course, course2, course3;
    private UserCourse tutor, tutor2;
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
    	    	    	
    	tutor = new UserCourse();
    	tutor.setTutorsName("Vader");
    	tutor.setCourse(course);

    	tutor2 = new UserCourse();
    	tutor2.setTutorsName("Darth");
    	tutor2.setCourse(course2);
    	
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
    	
    	User user = new User();
    	user.setFirstName("Hans");
    	user.setLastName("Solo");
    	when(userService.getPrincipalUser()).thenReturn(user);
    	
    	ModelAndView testModel = searchController.searchUniversitiesSubjectsAndCourses();
    	
    	ModelAndView model = new ModelAndView();
    	model.addObject("universities", unis);
    	model.addObject("subjects", subs);
    	model.addObject("courses", courses);
    	model.addObject("username", user.getWholeName());
    	
    	assertEquals(model.getModel().get("universities"), testModel.getModel().get("universities"));
    	assertEquals(model.getModel().get("subjects"), testModel.getModel().get("subjects"));
    	assertEquals(model.getModel().get("courses"), testModel.getModel().get("courses"));
    	assertEquals(model.getModel().get("username"), testModel.getModel().get("username"));
    }

}
