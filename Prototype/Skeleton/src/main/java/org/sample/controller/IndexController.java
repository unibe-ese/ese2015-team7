package org.sample.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.sample.controller.pojos.LoginForm;
import org.sample.controller.service.IRequestService;
import org.sample.controller.service.UserService;
import org.sample.model.Request;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This Controller handles all requests about to Login page which is the landing Page of this project.
 * 
 * @author Team7
 *
 */
@Controller
public class IndexController {

    @Autowired
    UserService	userService;	
    @Autowired
    IRequestService requestService;
    
    /**
     * loads login page named index.jsp if not logged in else goes to loginningIn Page
     * 
     * @return the ModelView of login Page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if ((authentication instanceof AnonymousAuthenticationToken)) {
    		model = new ModelAndView("index");
    		model.addObject("loginForm", new LoginForm());
    		return model;
    		
    	}
    	else{
    		model = new ModelAndView("redirect:/loginningIn");
    		return model;
        }
    	
    }
    


    /**
     * catches security errors and redirects them to the login page
     * @param redirectAttributes holds all the redirected attributes.
     * @return the loginPage
     */
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("infoMessage", "You do have no permission to do that!");
        return "redirect:/";
    }
    
    @RequestMapping(value = "/loginningIn", method = RequestMethod.GET)
    public String loginningIn(HttpSession session) {
    	User principal = userService.getPrincipalUser();
    	session.setAttribute("username", principal.getWholeName());
    	ArrayList<Request> requests = requestService.getAllIncomingRequests(principal);
    	for (Request request:requests){
    		if (request.getIsActiv())
    			return "redirect:/requests";
    	}
    	return "redirect:/search";
    }
}