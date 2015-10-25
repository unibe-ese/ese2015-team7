package org.sample.controller;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.IUserDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This Controller handles all actions with the User object.
 * 
 * @author mirko
 *
 */
@Controller
public class UserController {
	
	@Autowired
	IUserDataService userService;
	
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("infoMessage") String message) {
	ModelAndView model = new ModelAndView("signUp");
	model.addObject("signupForm", new SignupForm());
	model.addObject("message", message);
	return model;
    }
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
	
	ModelAndView model;
	
	
	try {
	    if (userService.validatePassword(signupForm.getPassword(), signupForm.getPasswordVerify()) == false) {
		    redirectAttributes.addFlashAttribute("infoMessage", "Deine Passwörter stimmen nicht überein");
		    return new ModelAndView("redirect:/signup");
		}
	} catch (Exception d) {}
		

	if (!result.hasErrors()) {
	    try {
	
	    
		if ( userService.getUserByEmail(signupForm.getEmail()) != null) {
		    redirectAttributes.addFlashAttribute("infoMessage", "Ein User mit dieser E-Mail-Adressse existiert schon!");
		    return new ModelAndView("redirect:/signup");
		}
		
	
		userService.saveFrom(signupForm);
		redirectAttributes.addFlashAttribute("infoMessage", "Du hast dich erfolgreich registriert. Du kannst dich nun einloggen.");

		model = new ModelAndView("redirect:/");
	    } catch (InvalidUserException e) {
		model = new ModelAndView("signup");
		model.addObject("page_error", e.getMessage());
	    }
	} else {
	    model = new ModelAndView("signup");
	}
	return model;
    }
	
	

	
	
}
