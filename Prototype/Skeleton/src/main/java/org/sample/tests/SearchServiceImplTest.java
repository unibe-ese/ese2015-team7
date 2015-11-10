package org.sample.tests;

import org.junit.runner.RunWith;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.SearchService;
import org.sample.controller.service.SearchServiceImpl;
import org.sample.controller.service.UserService;
import org.sample.model.Course;
import org.sample.model.Grade;
import org.sample.model.GradeFactory;
import org.sample.model.Subject;
import org.sample.model.University;
import org.sample.model.User;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.CourseDao;
import org.sample.model.dao.SubjectDao;
import org.sample.model.dao.TutorDao;
import org.sample.model.dao.UniversityDao;
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
import static org.mockito.Matchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/test/test.xml"})
public class SearchServiceImplTest {
	
    @Autowired	UserDao userDao;
    @Autowired	AddressDao addDao;
    @Autowired	UniversityDao universityDao;
    @Autowired	SubjectDao subjectDao;
    @Autowired	CourseDao courseDao;
    @Autowired	TutorDao tutorDao;
    @Autowired	SearchServiceImpl searchServiceImpl;
    private SearchForm searchForm;
    private University uni;
    private Subject sub;
    private Course course, testCourse;
    
    @Before
    public void setUp(){
    	uni = new University();
    	sub = new Subject();
    	course = new Course();
    	uni.setUniversityName("Uni Bern");
    	uni.setUId(100L);
    	sub.setSubjectName("Info");
    	sub.setId(150L);
    	sub.setUniversity(uni);
    	course.setCourseName("ESE");
    	course.setSubject(sub);
    	
    	searchForm = new SearchForm();
    	searchForm.setCourse("ESE");
    	searchForm.setSubject("Info");
    	searchForm.setUniversity("Uni Bern");
    	
    	when(userDao.save(any(User.class))).then(returnsFirstArg());
    	
    	when(universityDao.findByUniversityName(any(String.class))).then(returnsFirstArg());
    	when(subjectDao.findBySubjectNameAndUniversity(any(String.class),any(University.class))).then(returnsFirstArg());
		when(courseDao.findByCourseNameAndSubject(any(String.class),any(Subject.class))).then(returnsFirstArg());
    }
    
    @Test
    public void testGetCourse(){
    	testCourse = new Course();
    	testCourse = searchServiceImpl.getCourse(searchForm);
    	
    	assertEquals(testCourse, course);
    	
    }

}
