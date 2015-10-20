package org.sample.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.sample.controller.pojos.SearchForm;
import org.sample.controller.service.SampleService;
import org.sample.controller.service.SampleServiceImpl;
import org.sample.controller.service.SearchService;
import org.sample.model.Course;
import org.sample.model.Subject;
import org.sample.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SearchController {

    @Autowired
    SearchService searchService;


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
    
    @RequestMapping(value = "/createSearch", method = RequestMethod.POST)
    public ModelAndView create(@Valid SearchForm searchForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;
    	if (!result.hasErrors()) {
            try {
            	//((SampleServiceImpl) sampleService).saveFrom(searchForm);
            	model = new ModelAndView(new RedirectView("result"));
            } catch (InvalidUserException e) {
            	model = new ModelAndView("search");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("search");
        }   	
    	return model;
    }
    */
}