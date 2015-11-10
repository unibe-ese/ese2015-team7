package org.sample.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.service.SearchService;
import org.sample.controller.service.UserService;
import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.Tutor;
import org.sample.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes({"username","searchedCourse"})
@Controller
public class SearchController {

    @Autowired
    SearchService searchService;
    
    @Autowired
    UserService	userService;	


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchUniversities() {
    	
    	ModelAndView model = new ModelAndView("search");
        ArrayList<University> universities = new ArrayList<University>(); 
        universities =  searchService.getUniversities();
    	model.addObject("universities", universities);

    	ArrayList<Subject> subjects = new ArrayList<Subject>();
    	subjects = searchService.getSubjects();
    	model.addObject("subjects", subjects);

    	ArrayList<Course> courses = new ArrayList<Course>();
    	courses = searchService.getCourses();
    	model.addObject("courses", courses);

    	model.addObject("searchForm", new SearchForm());
    	
    	
    	String username = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getName(); //gets principal and loads user from Database and gets his name
    	model.addObject("username", username);
    	
        return model;
    }
    
    /*
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView validate(@Valid String university, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;
    	if (!result.hasErrors()) {
            try {
            	model = new ModelAndView(new RedirectView("searchSubjects"));
            } catch (InvalidUserException e) {
            	model = new ModelAndView("search");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("search");
        }   	
    	return model;
    }
    
    @RequestMapping(value = "/searchSubjects", method = RequestMethod.GET)
    public ModelAndView searchSubjects(@RequestParam("university") String university, @RequestParam("model") ModelAndView model) {
    	ArrayList<Subject> subjects = new ArrayList<Subject>();
    	subjects = ((SampleServiceImpl) sampleService).getSubjectsFromUniversity(university);
    	model.addObject("subjects", subjects);

    	model.addObject("searchForm", new SearchForm());
    	
        return model;
    }
    
    
    
    @RequestMapping(value = "/searchCourses", method = RequestMethod.GET)
    public ModelAndView searchCourses(@RequestParam("subject") Subject subject) {
    	ModelAndView model = new ModelAndView("search");
    	ArrayList<Course> courses = new ArrayList<Course>();
    	courses = ((SampleServiceImpl) sampleService).getCourseFromSubject(subject);
    	model.addObject("courses", courses);

    	model.addObject("searchForm", new SearchForm());
    	
        return model;
    }
    */
    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public ModelAndView results(@Valid SearchForm searchForm) {
    	ModelAndView model = new ModelAndView();
            try {
            	model = new ModelAndView("results");
            	ArrayList<Tutor> tutors = searchService.getTutorsFromSearchForm(searchForm); 	
            	model.addObject("tutors", tutors);
            	
            	
            	Course searchedCourse = searchService.getCourse(searchForm);
            	model.addObject("searchedCourse",searchedCourse);
            	
            } catch (InvalidUserException e) {
            	e.printStackTrace();
            }
    	return model;
    }
}