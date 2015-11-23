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
public class SearchService implements ISearchService {

    @Autowired	UserDao userDao;
    @Autowired	AddressDao addDao;
    @Autowired	UniversityDao universityDao;
    @Autowired	SubjectDao subjectDao;
    @Autowired	CourseDao courseDao;
    @Autowired	UserCourseDao userCourseDao;
 
    
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
    	String universityName = searchForm.getUniversity();
    	String subjectName = searchForm.getSubject();    	
    	String courseName = searchForm.getCourse();
        int grade = searchForm.getGrade();
    	ArrayList<UserCourse> userCourseList = new ArrayList<UserCourse>();

        if(!StringUtils.isEmpty(courseName) && !"Select Course".equalsIgnoreCase(courseName)) {
        	Course course = getCourse(searchForm);
            userCourseList = userCourseDao.findByCourseAndTeachingAndMinimumGrade(course, grade, true);
        }
        else if(!StringUtils.isEmpty(subjectName) && !"Select Subject".equalsIgnoreCase(subjectName)){
        	Subject subject = getSubject(searchForm);
        	ArrayList<Course> coursesList = courseDao.findBySubject(subject);
            for(Course course : coursesList){
                userCourseList.addAll(userCourseDao.findByCourseAndTeachingAndMinimumGrade(course, grade, true));
            }
        }
        else if(!StringUtils.isEmpty(universityName) && !"Select University".equalsIgnoreCase(universityName)){
        	University university = getUniversity(searchForm);
        	ArrayList<Subject> subjectsList = subjectDao.findByUniversity(university);
        	for(Subject subject : subjectsList){
	        	ArrayList<Course> coursesList = courseDao.findBySubject(subject);
	            for(Course course : coursesList){
	                userCourseList.addAll(userCourseDao.findByCourseAndTeachingAndMinimumGrade(course, grade, true));
	            }
        	}
        }  
        else{
        	userCourseList = userCourseDao.findByTeachingAndMinimumGrade(grade, true);
        }
        return userCourseList;
    }

	public University getUniversity(SearchForm searchForm) {
    	University university = universityDao.findByUniversityName(searchForm.getUniversity());		
    	return university;
	}
	
	public Subject getSubject(SearchForm searchForm) {
    	University university = getUniversity(searchForm);
		Subject subject = subjectDao.findBySubjectNameAndUniversity(searchForm.getSubject(),university);
		return subject;
	}
	
	public Course getCourse(SearchForm searchForm) {
    	Subject subject  = getSubject(searchForm);
		Course course = courseDao.findByCourseNameAndSubject(searchForm.getCourse(),subject);
		return course;
	}
}
    