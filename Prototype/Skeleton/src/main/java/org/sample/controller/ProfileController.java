package org.sample.controller;

import java.security.Principal;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.IUserDataService;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ProfileController {
	
	@Autowired
    IUserDataService userService;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(Principal principal){
    	ModelAndView model = new ModelAndView("profile");
    	
    	User user = userService.getUserByEmail(principal.getName());
    	model.addObject(user);
        return model;
    }
	
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView postProfile(Principal principal, @RequestParam("itemUser") String tutorEmail){
    	ModelAndView model = new ModelAndView("profile");
    	User user = userService.getUserByEmail(tutorEmail);
    	model.addObject(user);
    	
        return model;
    }
	
}

/*

	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String username = auth.getName(); 
	model.addObject("username", username);
	

@RequestMapping(value = "/myprofile", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView myProfile(Principal principal, @ModelAttribute("infoMessage") String message) {

	User currentUser = userService.getUserByEmail(principal.getName());

	ModelAndView model = new ModelAndView("myProfile");
	model.addObject("user", currentUser);
	model.addObject("infoMessage", message);
	return model;
    }

*/