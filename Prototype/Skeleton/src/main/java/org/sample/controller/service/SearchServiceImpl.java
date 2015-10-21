package org.sample.controller.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SearchForm;
import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.Tutor;
import org.sample.model.University;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.CourseDao;
import org.sample.model.dao.SubjectDao;
import org.sample.model.dao.TutorDao;
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
    @Autowired	TutorDao tutorDao;
 
    
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
    
    /*
    @Transactional
    public ArrayList<Subject> getSubjectsFromUniversity(String university) throws InvalidUserException
    {
    	ArrayList<Subject> subjectsList = new ArrayList<Subject>();
    	Iterator<Subject> subjectsIter = subjectDao.findAll().iterator();
    	while(subjectsIter.hasNext())
    	{
    		Subject subject = subjectsIter.next();
    		if(subject.getUniversity().getUniversityName().equals(university))
    			subjectsList.add(subject);
    	}
    	return subjectsList;
    }
    */
    
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
    
    /*
    @Transactional
    public ArrayList<Course> getCourseFromSubject(Subject subject) throws InvalidUserException
    {
    	ArrayList<Course> coursesList = new ArrayList<Course>();
    	Iterator<Course> coursesIter = courseDao.findAll().iterator();
    	while(coursesIter.hasNext())
    	{
    		Course course = coursesIter.next();
    		if(course.getSubject().equals(subject))
    			coursesList.add(course);
    	}
    	return coursesList;
    }
    */
    
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
    public ArrayList<Tutor> getTutorsFromSearchForm(SearchForm searchForm) throws InvalidUserException
    {
    	
    	String courseName = searchForm.getCourse();

        if(!StringUtils.isEmpty(courseName) && "Select Course".equalsIgnoreCase(courseName)) {
            throw new InvalidUserException("Sorry, Select Course is not a valid name");
        }
        
        Course course = getCourseByName(courseName);

        ArrayList<Tutor> tutorsList = new ArrayList<Tutor>();
        Iterator<Tutor> tutorsIter	= tutorDao.findAll().iterator();
        while(tutorsIter.hasNext())
        {
        	Tutor tutor = tutorsIter.next();
        	if(tutor.getCourse().equals(course))
        		tutorsList.add(tutor);
        }

        return tutorsList;
    }
    
    @Transactional
    public University getUniversityByName(String universityName)
    {
    	University university = new University();
    	Iterator<University> universities = universityDao.findAll().iterator();
    	while(universities.hasNext())
    	{
    		University universityTmp = universities.next();
    		if(universityTmp.getUniversityName().equalsIgnoreCase(universityName))
    		{
    			university = universityTmp;
    			break;
    		}
    	}
    	return university;
    }
    
    @Transactional
    public Subject getSubjectByName(String subjectName)
    {
    	Subject subject = new Subject();
    	Iterator<Subject> subjects = subjectDao.findAll().iterator();
    	while(subjects.hasNext())
    	{
    		Subject subjectTmp = subjects.next();
    		if(subjectTmp.getSubjectName().equalsIgnoreCase(subjectName))
    		{
    			subject = subjectTmp;
    			break;
    		}
    	}
    	return subject;
    }
    
    @Transactional
    public Course getCourseByName(String courseName)
    {
    	Course course = new Course();
    	Iterator<Course> courses = courseDao.findAll().iterator();
    	while(courses.hasNext())
    	{
    		Course courseTmp = courses.next();
    		if(courseTmp.getCourseName().equalsIgnoreCase(courseName))
    		{
    			course = courseTmp;
    			break;
    		}
    	}
    	return course;
    }
}
    