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
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


/**
 * The SearchController handles all requests in relationship with a search action. 
 * 
 * 
 * @author Jannis
 *
 */
@SessionAttributes({"username"})
@Controller
public class SearchController {

    @Autowired
    ISearchService searchService;
    
    @Autowired
    UserService	userService;	


    /**
     * Loads searchPage and all possible Universities/Subjects and Courses and 
     * adds the Searchform for user input
     * 
     * @return ModelView of searchPage
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchUniversitiesSubjectsAndCourses() {
    	
    	ModelAndView model = new ModelAndView("search");
        ArrayList<University> universities = searchService.getUniversities();
    	model.addObject("universities", universities);

    	ArrayList<Subject> subjects = searchService.getSubjects();
    	model.addObject("subjects", subjects);

    	ArrayList<Course> courses = searchService.getCourses();
    	model.addObject("courses", courses);

    	model.addObject("searchForm", new SearchForm());
    	
    	User principal=userService.getPrincipalUser();
    	String username = principal.getWholeName();
    	model.addObject("username", username);
    	
        return model;
    }
        

    /**
     * shows all Results for the specified SearchRequest in Searchform.
     * adds all Tutors to the model and specifies the searched Course in SessionAttribut
     * 
     * @param searchForm holds the University, Subject and Course of the SearchRequest.
     * @return MOdelView with all possible Tutors.
     */
    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public ModelAndView results(@Valid SearchForm searchForm) {
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
    	
    	User principal=userService.getPrincipalUser();
    	String username = principal.getWholeName();
    	model.addObject("username", username);
    	
    	model.addObject("searchForm", new SearchForm());
    	
    	return model;
    }
}