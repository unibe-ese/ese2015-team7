package org.sample.controller.service;

import java.util.ArrayList;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SearchForm;
import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.UserCourse;
import org.sample.model.University;

public interface ISearchService {
	
	 public ArrayList<University> getUniversities();
	 
	 public ArrayList<Subject> getSubjects();
	 
	 public ArrayList<Course> getCourses();
	 
	 public ArrayList<UserCourse> getTutorsFromSearchForm(SearchForm searchForm) throws InvalidUserException;
	 	 
	 public Course getCourse(SearchForm searchForm);
	 public Subject getSubject(SearchForm searchForm);
	 public University getUniversity(SearchForm searchForm);


}