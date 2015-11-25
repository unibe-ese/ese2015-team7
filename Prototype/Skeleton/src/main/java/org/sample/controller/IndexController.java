package org.sample.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.sample.controller.pojos.LoginForm;
import org.sample.controller.service.IRequestService;
import org.sample.controller.service.UserService;
import org.sample.model.Request;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author mirko
 *
 *This Controller handles all requests about to Login page which is the landing Page of this project.
 */
@Controller
public class IndexController {

    @Autowired
    UserService	userService;	
    @Autowired
    IRequestService requestService;
    
    /**
     * loads Login Page named index
     * 
     * @return ModelView of login Page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("index");
    	model.addObject("loginForm", new LoginForm());
        return model;
    }
    


    /**
     * catches security errors and redirects them to login page
     * @param redirectAttributes 
     * @return loginPage
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
    	ArrayList<Request> requests = requestService.getAllRequests(principal);
    	for (Request request:requests){
    		if (request.getIsActiv())
    			return "redirect:/requests";
    	}
    	return "redirect:/search";
    }
}