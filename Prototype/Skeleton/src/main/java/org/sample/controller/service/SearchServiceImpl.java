package org.sample.controller.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.model.Address;
import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.Tutor;
import org.sample.model.University;
import org.sample.model.User;
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
    public ArrayList<Tutor> getTutorsFromSearchForm(SearchForm searchForm) throws InvalidUserException{
    	
        String university = searchForm.getUniversity();

        if(!StringUtils.isEmpty(university) && "Select University".equalsIgnoreCase(university)) {
            throw new InvalidUserException("Sorry, Select University is not a valid name");   // throw exception
        }
        
        ArrayList<Tutor> tutorsList = new ArrayList<Tutor>();
		
        Tutor tutor = new Tutor();
        tutor.setTutorsName("Luke Skywalker");
        
        tutorsList.add(tutor);

        return tutorsList;
    }
    
    @Transactional
    public SearchForm saveFrom(SearchForm searchForm) throws InvalidUserException{

        String universityName = searchForm.getUniversity();

        if(!StringUtils.isEmpty(universityName) && "Select University".equalsIgnoreCase(universityName)) {
            throw new InvalidUserException("Sorry, Select University is not a valid name");   // throw exception
        }

        University university = new University();
        university.setUniversityName(universityName);
        
        university = universityDao.save(university);   // save object to DB
        
        
        // Iterable<Address> addresses = addDao.findAll();  // find all 
        // Address anAddress = addDao.findOne((long)3); // find by ID
        
        
        searchForm.setId(university.getUId());

        return searchForm;

    }
}
    