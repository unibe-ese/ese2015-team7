package org.sample.controller.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SearchForm;
import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.UserCourse;
import org.sample.model.University;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.CourseDao;
import org.sample.model.dao.SubjectDao;
import org.sample.model.dao.UserCourseDao;
import org.sample.model.dao.UniversityDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class SearchServiceImpl implements SearchService {

    @Autowired	UserDao userDao;
    @Autowired	AddressDao addDao;
    @Autowired	UniversityDao universityDao;
    @Autowired	SubjectDao subjectDao;
    @Autowired	CourseDao courseDao;
    @Autowired	UserCourseDao tutorDao;
 
    
    @Transactional
    public ArrayList<University> getUniversities()
    {
    	ArrayList<University> universityList = new ArrayList<University>();
    	Iterator<University> universitiesIter = universityDao.findAll().iterator();
    	while(universitiesIter.hasNext())
    	{
        	universityList.add(universitiesIter.next());
    	}
    	return universityList;
    }
    
    @Transactional
    public ArrayList<Subject> getSubjects()
    {
    	ArrayList<Subject> subjectsList = new ArrayList<Subject>();
    	Iterator<Subject> subjectsIter = subjectDao.findAll().iterator();
    	while(subjectsIter.hasNext())
    	{
    			subjectsList.add(subjectsIter.next());
    	}
    	return subjectsList;
    }
        
    @Transactional
    public ArrayList<Course> getCourses()
    {
    	ArrayList<Course> coursesList = new ArrayList<Course>();
    	Iterator<Course> coursesIter = courseDao.findAll().iterator();
    	while(coursesIter.hasNext())
    	{
    			coursesList.add(coursesIter.next());
    	}
    	return coursesList;
    }
        
    @Transactional
    public ArrayList<UserCourse> getTutorsFromSearchForm(SearchForm searchForm) throws InvalidUserException
    {
    	
    	String courseName = searchForm.getCourse();

        if(!StringUtils.isEmpty(courseName) && "Select Course".equalsIgnoreCase(courseName)) {
            throw new InvalidUserException("You have not selected a course yet!");
        }
        
        Course course = courseDao.findByCourseName(courseName);

        ArrayList<UserCourse> tutorsList = new ArrayList<UserCourse>();
        Iterator<UserCourse> tutorsIter	= tutorDao.findAll().iterator();
        while(tutorsIter.hasNext())
        {
        	UserCourse tutor = tutorsIter.next();
        	if(tutor.getCourse().equals(course))
        		tutorsList.add(tutor);
        }

        return tutorsList;
    }
        
    @Transactional

	public Course getCourse(SearchForm searchForm) {
    	University university = universityDao.findByUniversityName(searchForm.getUniversity());
    	Subject subject  = subjectDao.findBySubjectNameAndUniversity(searchForm.getSubject(),university);

		Course course = courseDao.findByCourseNameAndSubject(searchForm.getCourse(),subject);
		return course;
	}
}
    