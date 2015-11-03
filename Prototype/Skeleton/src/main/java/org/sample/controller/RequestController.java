package org.sample.controller;



import java.util.ArrayList;

import org.sample.controller.pojos.SearchForm;
import org.sample.controller.service.IRequestService;
import org.sample.controller.service.UserService;
import org.sample.model.Request;
import org.sample.model.User;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RequestController {
	
	
	@Autowired 
	UserService userService;
	
	@Autowired
	IRequestService iRequestService;
	
	@Autowired
	UserDao userDao;

	
	@RequestMapping(value = "/myRequests", method = RequestMethod.POST)
    public ModelAndView myRequestPost( @RequestParam("requestedUser") String tutorEmail){
    	ModelAndView model = new ModelAndView("myRequests");
    	String principalEmail =SecurityContextHolder.getContext().getAuthentication().getName();
    	iRequestService.saveRequest(tutorEmail, SecurityContextHolder.getContext().getAuthentication().getName());
    	User principal = userDao.findByEmail(principalEmail);
    	ArrayList<Request> myRequests = iRequestService.getAllMyRequests(principal);
    	model.addObject(myRequests);
    	
    	String username = userService.getUserByEmail(principalEmail).getName(); //gets principal and loads user from Database and gets his name
    	model.addObject("username", username); 
        return model;
    }
	
	
	@RequestMapping(value = "/myRequests", method = RequestMethod.GET)
    public ModelAndView myRequestGet(){
    	ModelAndView model = new ModelAndView("myRequests");
    	String principalEmail =SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	User principal = userDao.findByEmail(principalEmail);
    	
    	ArrayList<Request> myRequests = iRequestService.getAllMyRequests(principal);
    	model.addObject("myRequests", myRequests);
    	
    	model.addObject("searchForm", new SearchForm());
    	
    	String username = principal.getName(); //gets principal and loads user from Database and gets his name
    	model.addObject("username", username);
    	
        return model;
    }
	
	@RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ModelAndView requestGet(){
    	ModelAndView model = new ModelAndView("requests");
    	String principalEmail =SecurityContextHolder.getContext().getAuthentication().getName();
    	User principal = userDao.findByEmail(principalEmail);
    	
    	ArrayList<Request> requests = iRequestService.getAllRequests(principal);
    	model.addObject("requests", requests);
    	
    	String username = userService.getUserByEmail(principalEmail).getName(); //gets principal and loads user from Database and gets his name
    	model.addObject("username", username);
    	
        return model;
    }
	
	@RequestMapping(value = "/myRequests/action", method = RequestMethod.POST)
    public ModelAndView myRequestAction(@RequestParam("deleteRequest") String tutorEmail){
    	ModelAndView model = new ModelAndView("redirect:/myRequests");
    	String principalEmail =SecurityContextHolder.getContext().getAuthentication().getName();
    	User principal = userDao.findByEmail(principalEmail);
	
    	iRequestService.deleteRequest(userDao.findByEmail(tutorEmail));
  
    	ArrayList<Request> requests = iRequestService.getAllRequests(principal);
    	model.addObject("requests", requests);
    	
    	String username = userService.getUserByEmail(principalEmail).getName(); //gets principal and loads user from Database and gets his name
    	model.addObject("username", username);
    	
        return model;
    }
	
	@RequestMapping(value = "/requests/action", method = RequestMethod.POST)
    public ModelAndView requestAction(@RequestParam(required=false,name="visitProfile") String studentEmail, @RequestParam(required=false,name="acceptRequest") String acceptStudentEmail,@RequestParam(required=false,name="declineRequest") String declineStudentEmail,RedirectAttributes redirectAttrs){
    	ModelAndView model = new ModelAndView("redirect:/requests");
    	String principalEmail =SecurityContextHolder.getContext().getAuthentication().getName();
    	User principal = userDao.findByEmail(principalEmail);
    	
    	
    	if (studentEmail != null){
        	User user = userService.getUserByEmail(studentEmail);
        	redirectAttrs.addFlashAttribute("user", user);
            return new ModelAndView("redirect:/profile");
    	}
    	else if (acceptStudentEmail != null){
    		iRequestService.acceptRequest(userDao.findByEmail(acceptStudentEmail));
    	}
    	else if (declineStudentEmail != null){
    		iRequestService.declineRequest(userDao.findByEmail(declineStudentEmail));
    	}
    	
  
    	ArrayList<Request> requests = iRequestService.getAllRequests(principal);
    	model.addObject("requests", requests);
    	
    	String username = userService.getUserByEmail(principalEmail).getName(); //gets principal and loads user from Database and gets his name
    	model.addObject("username", username);
    	
        return model;
    }
}
