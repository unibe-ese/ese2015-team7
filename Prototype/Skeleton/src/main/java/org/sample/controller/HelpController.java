package org.sample.controller;

import org.sample.controller.service.IUserDataService;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This Controller maps the URI "/help" and loads the help.jsp page.
 * 
 * @author Team7
 *
 */

@Controller
public class HelpController {
	
@Autowired
IUserDataService userService;
	
	/**
	 * This method attachs the logged in user to the model and loads the help page.
	 * @return the help page.
	 */
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public ModelAndView help() {
		ModelAndView model = new ModelAndView("help");
    	User principal = userService.getPrincipalUser();
		User user = principal;
		model.addObject(user);
		return model;
	}
	
}
