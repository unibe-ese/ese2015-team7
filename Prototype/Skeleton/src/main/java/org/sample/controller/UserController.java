package org.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.IUserDataService;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * @author Team7
 *
 */

@Controller
public class UserController {
	
	@Autowired
	IUserDataService userService;
	
	@Autowired @Qualifier("authMgr") private AuthenticationManager authMgr;
	
	/**
	 * loads signupPage to enter Personal Information.
	 * 
	 * @param message informs User about mistakes he did (not equal passwords)
	 * @return ModelView signupPage
	 */
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("infoMessage") String message) {
	ModelAndView model = new ModelAndView("signUp");
	model.addObject("signupForm", new SignupForm());
	model.addObject("infoMessage", message);
	return model;
    }
	
	/**
	 * checks if all parameters are valid and 
	 * creates then a user accout on the specific parameters.
	 * 
	 * @param signupForm hold the Input of the User to create the new Account
	 * @param result 
	 * @param redirectAttributes 
	 * @return ModelView if invalid input is entered it loads new signupPage with infoMessage else goes to LoginPage
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid SignupForm signupForm, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) {
	ModelAndView model= new ModelAndView("signUp");
	
	if (!result.hasErrors()) {
	    try {
	    	if (!userService.validatePassword(signupForm.getPassword(), signupForm.getPasswordVerify())) {
			    redirectAttributes.addFlashAttribute("infoMessage", "Your passwords do not match");
			    return new ModelAndView("redirect:/signUp");
			}
	    
	    	if ( userService.getUserByEmail(signupForm.getEmail()) != null) {
	    		redirectAttributes.addFlashAttribute("infoMessage", "A user with this email exists already!");
	    		return new ModelAndView("redirect:/signUp");
		}
	
		userService.saveFrom(signupForm);
		redirectAttributes.addFlashAttribute("infoMessage", "You have successfuly registerd. Welcome!");
		
	    } catch (InvalidUserException e) {
		model = new ModelAndView("signUp");
		model.addObject("page_error", e.getMessage());
		return model;
	    }
	    
	 // perform login authentication
	    User principal = userService.getUserByEmail(signupForm.getEmail());
	    String password = signupForm.getPassword();
	    
	    try {
	      
	      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, password);
	      authMgr.authenticate(auth);
	 
	      // redirect into secured main page if authentication successful
	      if(auth.isAuthenticated()) {
	        SecurityContextHolder.getContext().setAuthentication(auth);
	        return new ModelAndView("redirect:/search");
	      }
	    } catch (Exception e) {
	    	model = new ModelAndView("signUp");
			model.addObject("page_error", e.getMessage());
			return model;
	    }
	    
	} else {
	    model = new ModelAndView("signUp");
	}
	
	return model;

    }
	
	

	
	
}
