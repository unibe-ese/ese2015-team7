package org.sample.controller;

import java.util.ArrayList;

import org.sample.controller.service.IRequestService;
import org.sample.controller.service.UserService;
import org.sample.model.Request;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequestController {
	
	
	@Autowired 
	UserService userService;
	
	@Autowired
	IRequestService iRequestService;

	
	@RequestMapping(value = "myRequests", method = RequestMethod.POST)
    public ModelAndView postProfile( @RequestParam("requestedUser") String tutorEmail){
    	ModelAndView model = new ModelAndView("myRequests");

    	User user = userService.getUserByEmail(tutorEmail);
    	model.addObject(user);
    	
    	iRequestService.saveRequest(tutorEmail, SecurityContextHolder.getContext().getAuthentication().getName());
    	
    	//ArrayList<Request> myRequests = iRequestService.getAllMyRequests();
    	
    	
    	String username = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getName(); //gets principal and loads user from Database and gets his name
    	model.addObject("username", username); 
        return model;
    }
	
}
