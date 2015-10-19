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
public class SampleServiceImpl implements SampleService {

    @Autowired	UserDao userDao;
    @Autowired	AddressDao addDao;
    @Autowired	UniversityDao universityDao;
    @Autowired	SubjectDao subjectDao;
    @Autowired	CourseDao courseDao;
    @Autowired	TutorDao tutorDao;
    
    /*
    @Transactional
    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException{

        String firstName = signupForm.getFirstName();

        if(!StringUtils.isEmpty(firstName) && "ESE".equalsIgnoreCase(firstName)) {
            throw new InvalidUserException("Sorry, ESE is not a valid name");   // throw exception
        }


        Address address = new Address();
        address.setStreet("TestStreet-foo");
        
        User user = new User();
        user.setFirstName(signupForm.getFirstName());
        user.setEmail(signupForm.getEmail());
        user.setLastName(signupForm.getLastName());
        user.setTeam(signupForm.getTeam());
        user.setAddress(address);
        
        user = userDao.save(user);   // save object to DB
        
        
        // Iterable<Address> addresses = addDao.findAll();  // find all 
        // Address anAddress = addDao.findOne((long)3); // find by ID
        
        
        signupForm.setId(user.getId());

        return signupForm;

    }
    */
    
    @Transactional
    public ArrayList<University> getUniversities()
    {
    	ArrayList<University> universityList = new ArrayList<University>();
    	/*
    	Iterator<University> universitiesIter = universityDao.findAll().iterator();
    	while(universitiesIter.hasNext())
    	{
        	universityList.add(universitiesIter.next());
    	}
    	*/
    	University uni = new University();
    	uni.setUniversityName("Bern");
    	uni.setUId(1l);
    	universityList.add(uni);
    	return universityList;
    }
    
    @Transactional
    public ArrayList<Subject> getSubjectsFromUniversity(University university) throws InvalidUserException
    {
    	Iterable<Subject> subjects = subjectDao.findAll();
    	ArrayList<Subject> subjectsList = new ArrayList<Subject>();
    	Iterator<Subject> subjectsIter = subjects.iterator();
    	while(subjectsIter.hasNext())
    	{
    		Subject subject = subjectsIter.next();
    		if(subject.getUniversity().equals(university))
    			subjectsList.add(subject);
    	}
    	return subjectsList;
    }
    
    @Transactional
    public ArrayList<Course> getCourseFromSubject(Subject subject) throws InvalidUserException
    {
    	return null;
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
    
	public User getUserById(long id) {
		return userDao.findOne(id);
	}
}
