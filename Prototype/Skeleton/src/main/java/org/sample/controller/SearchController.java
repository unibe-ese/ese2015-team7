package org.sample.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.service.SearchService;
import org.sample.controller.service.UserService;
import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.UserCourse;
import org.sample.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
@SessionAttributes({"username","searchedCourse"})
@Controller
public class SearchController {

    @Autowired
    SearchService searchService;
    
    @Autowired
    UserService	userService;	


    /**
     * Loads searchPage and all possible Universities/Subjects and Courses and 
     * adds the Searchform for user input
     * 
     * @return ModelView of searchPage
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchUniversities() {
    	
    	ModelAndView model = new ModelAndView("search");
        ArrayList<University> universities = searchService.getUniversities();
    	model.addObject("universities", universities);

    	ArrayList<Subject> subjects = searchService.getSubjects();
    	model.addObject("subjects", subjects);

    	ArrayList<Course> courses = searchService.getCourses();
    	model.addObject("courses", courses);

    	model.addObject("searchForm", new SearchForm());
    	
    	
    	String username = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getName(); //gets principal and loads user from Database and gets his name
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
            	

            	Course searchedCourse = searchService.getCourse(searchForm);
            	model.addObject("searchedCourse",searchedCourse);
            	
            } catch (InvalidUserException e) {
            	e.printStackTrace();
            }
    	return model;
    }
}