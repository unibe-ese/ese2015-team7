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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
	 * Loads signupPage to enter Personal Information.
	 * 
	 * @param message informs user about mistakes he did.
	 * @return the signUp page.
	 */
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView register(@ModelAttribute("infoMessage") String message) {
	ModelAndView model = new ModelAndView("signUp");
	model.addObject("signupForm", new SignupForm());
	model.addObject("infoMessage", message);
	return model;
    }
	
	/**
	 * Checks if all parameters are valid and creates then a user account on the specific parameters and does an autologin for the user.
	 * 
	 * @param signupForm holds the Input of the User to create the new Account
	 * @param result contains all errors.
	 * @param redirectAttributes contains the attributes to redirect.
	 * @return the signUp page with the infoMessage if invalid input is entered, else loads the login page.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid SignupForm signupForm, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) {
	ModelAndView model= new ModelAndView("signUp");
	
	if (!result.hasErrors()) {
		
	    model = checkValidityAndRegistersNewUser(signupForm, redirectAttributes, model);
	    model = authenticateUserAndSetSession(signupForm, request, redirectAttributes, model);
	    
	} else {
	    model = new ModelAndView("signUp");
	}
	
	return model;

    }

	/**
	 * Checks the validity of the signupForm and registers a new user.
	 * @param signupForm holds the information of the new user.
	 * @param redirectAttributes contains the attributes to redirect.
	 * @param model contains all information of the page.
	 * @return the signUp page if there are errors in the form else returns the model.
	 */
	private ModelAndView checkValidityAndRegistersNewUser(SignupForm signupForm, RedirectAttributes redirectAttributes,
			ModelAndView model) {
		try {
	    	if ( !signupForm.getPassword().equals(signupForm.getPasswordVerify()) ) {
			    redirectAttributes.addFlashAttribute("infoMessage", "Your passwords do not match");
			    return new ModelAndView("redirect:/signUp");
			}
	    
	    	if ( userService.getUserByEmail(signupForm.getEmail()) != null) {
	    		redirectAttributes.addFlashAttribute("infoMessage", "A user with this email exists already!");
	    		return new ModelAndView("redirect:/signUp");
		}
	
		userService.saveFrom(signupForm);
		redirectAttributes.addFlashAttribute("infoMessage", "You have successfuly registerd. Welcome!");
		return model;
		
	    } catch (InvalidUserException e) {
		model = new ModelAndView("signUp");
		model.addObject("page_error", e.getMessage());
		return model;
	    }
	}

	/**
	 * Authenticates an user and sets up a new session.
	 * @param signupForm holds the information of a newly registering user.
	 * @param request contains and the session.
	 * @param model contains all information of the page.
	 * @return the help page if the authentication was successful else returns the msignUp page.
	 */
	private ModelAndView authenticateUserAndSetSession(SignupForm signupForm, HttpServletRequest request,RedirectAttributes redirectAttributes,
			ModelAndView model) {
			// perform login authentication
		
		    User principal = userService.getUserByEmail(signupForm.getEmail());
		    String password = signupForm.getPassword();
		    
		    try {
		      
		      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal.getEmail(), password);
		      
		      request.getSession();
		      auth.setDetails(new WebAuthenticationDetails(request));
		      Authentication authenticatedUser = authMgr.authenticate(auth);
		 
		      // redirect to secured main page if authentication successful
		      if(authenticatedUser.isAuthenticated()) {
		    	 model =  new ModelAndView("redirect:/help");
		    	 model.addObject("username", principal.getWholeName());
		    	 redirectAttributes.addFlashAttribute("username", principal.getWholeName());
		        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		        
		        return model;
		      }
		      else{
		    	  model = new ModelAndView("signUp");
		    	  model.addObject("page_error", "...uups there is a bug in the Autologin, please contact an administator of the site: chuck.norris@gmail.com");
		    	  return model;
		      }
		    } catch (Exception e) {
		    	model = new ModelAndView("signUp");
				model.addObject("page_error", e.getMessage());
				model.addObject("page_error",e.getStackTrace());
				return model;
		    }
	}
}
