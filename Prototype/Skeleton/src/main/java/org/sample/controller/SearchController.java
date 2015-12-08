package org.sample.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.service.ISearchService;
import org.sample.controller.service.UserService;
import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.UserCourse;
import org.sample.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * This controller handles all requests related to a search action.
 * 
 * @author Team7
 *
 */

@Controller
public class SearchController {

    @Autowired
    ISearchService searchService;
    
    @Autowired
    UserService	userService;	

    /**
     * Loads the searchPage and all possible Universities/Subjects and Courses and adds the Searchform for user input.
     * 
     * @return the ModelAndView of the searchPage.
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchUniversitiesSubjectsAndCourses() 
    {
    	
    	ModelAndView model = new ModelAndView("search");
        ArrayList<University> universities = searchService.getUniversities();
    	model.addObject("universities", universities);

    	ArrayList<Subject> subjects = searchService.getSubjects();
    	model.addObject("subjects", subjects);

    	ArrayList<Course> courses = searchService.getCourses();
    	model.addObject("courses", courses);

    	model.addObject("searchForm", new SearchForm());
    	
        return model;
    }

    /**
     * <p>Shows all Results for the specified SearchRequest in searchForm.</p>
     * Adds all Tutors to the model and specifies the searched Course in SessionAttribut.
     * 
     * @param searchForm holds the University, Subject and Course of the SearchRequest.
     * @return the ModelAndView with all possible Tutors.
     */
    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public ModelAndView results(@Valid SearchForm searchForm) 
    {
    	ModelAndView model = new ModelAndView();
    	
            try {
            	model = new ModelAndView("results");
            	ArrayList<UserCourse> userCourses = searchService.getTutorsFromSearchForm(searchForm); 	
            	model.addObject("userCourses", userCourses);
            	
            	University searchedUniversity = searchService.getUniversity(searchForm);
            	if(searchedUniversity==null){
            		searchedUniversity = new University();
            		searchedUniversity.setUniversityName("All");
            	}
            	model.addObject("searchedUniversity", searchedUniversity);
            	
            	Subject searchedSubject = searchService.getSubject(searchForm);
            	if(searchedSubject==null){
            		searchedSubject = new Subject();
            		searchedSubject.setSubjectName("All");
            	}
            	model.addObject("searchedSubject", searchedSubject);

            	Course searchedCourse = searchService.getCourse(searchForm);
            	if(searchedCourse==null){
            		searchedCourse = new Course();
            		searchedCourse.setCourseName("All");
            	}
            	model.addObject("searchedCourse", searchedCourse);
            	
            } catch (InvalidUserException e) {
            	e.printStackTrace();
            }

        ArrayList<University> universities = searchService.getUniversities();
    	model.addObject("universities", universities);

    	ArrayList<Subject> subjects = searchService.getSubjects();
    	model.addObject("subjects", subjects);

    	ArrayList<Course> courses = searchService.getCourses();
    	model.addObject("courses", courses);
    	model.addObject("searchForm", new SearchForm());
    	
    	return model;
    }
    
}