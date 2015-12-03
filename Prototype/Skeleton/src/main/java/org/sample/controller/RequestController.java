package org.sample.controller;



import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.service.IRequestService;
import org.sample.controller.service.ISelectService;
import org.sample.controller.service.UserService;
import org.sample.model.Request;
import org.sample.model.User;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles all requests which belong to a user.
 * 
 * @author Team7
 *
 */
@SessionAttributes({"username"})
@Controller
public class RequestController {
	
	
	@Autowired 
	UserService userService;
	
	@Autowired
	IRequestService iRequestService;
	
	@Autowired
	ISelectService selectService;
	
	@Autowired
	UserDao userDao;

	
	/**
	 * Gets searchedCourse from Session, saves the Request to the Database and deletes searchedCourse.
	 * 
	 * @param tutorEmail Email of Tutor which was requested.
	 * @param session holds the current searchedCourse.
	 * @return the ModelAndView to new myRequestPage.
	 */
	@RequestMapping(value = "/myRequests", method = RequestMethod.POST)
    public ModelAndView myRequestPost( @RequestParam("userCourseId") long userCourseId){
    	ModelAndView model = new ModelAndView("myRequests");
    	User principal = userService.getPrincipalUser();
    	String principalEmail = principal.getEmail();
    	
    	iRequestService.saveRequest(userCourseId, principalEmail);
    	
    	ArrayList<Request> myRequests = iRequestService.getAllOutgoingRequests(principal);
    	model.addObject("myRequests",myRequests);
    	model.addObject("message",iRequestService.getStateMessage(myRequests));
 
    	String username = principal.getWholeName();
    	model.addObject("username", username); 
        return model;
    }
	
	
	/**
	 * Gets all myRequests (outgoing Requests).
	 * @param session  --if available
	 * @return the ModelAndView with all outgoing requests.
	 */
	@RequestMapping(value = "/myRequests", method = RequestMethod.GET)
    public ModelAndView myRequestGet( HttpSession session){
    	ModelAndView model = new ModelAndView("myRequests");
    	User principal = userService.getPrincipalUser();    	
    	
    	ArrayList<Request> myRequests = iRequestService.getAllOutgoingRequests(principal);
    	model.addObject("myRequests", myRequests);
    	model.addObject("message",iRequestService.getStateMessage(myRequests));
    	
    	model.addObject("searchForm", new SearchForm());
    	String username = principal.getWholeName();
    	session.setAttribute("username", username);
    	
        return model;
    }
	
	/**
	 * Gets all requests ( Incoming Requests).
	 * @param session --if available searchedCourse.
	 * @return the ModelAndView with all incoming Requests.
	 */
	@RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ModelAndView requestGet(HttpSession session){
    	ModelAndView model = new ModelAndView("requests");
    	User principal = userService.getPrincipalUser();
    	
    	ArrayList<Request> requests = iRequestService.getAllIncomingRequests(principal);
    	model.addObject("requests", requests);
    	model.addObject("message",iRequestService.getStateMessage(requests));
    	String username = principal.getWholeName();
    	session.setAttribute("username", username);
    	
        return model;
    }
	
	/**
	 * Handles action on outgoing requests.
	 * @param tutorEmail the Email of the Tutor which should be deleted.
	 * @return the ModelAndView with updated list of outgoing requests.
	 */
	@RequestMapping(value = "/myRequests/action", method = RequestMethod.POST)
    public ModelAndView myRequestAction(@RequestParam("deleteRequest") long id,RedirectAttributes redirect){
    	ModelAndView model = new ModelAndView("redirect:/myRequests");
    	
    	User principal = userService.getPrincipalUser();
	
    	iRequestService.deleteRequest(id);
    	redirect.addFlashAttribute("infoMessage", "You successfully deleted the Request!");
    	
    	ArrayList<Request> requests = iRequestService.getAllIncomingRequests(principal);
    	redirect.addFlashAttribute("myRequests", requests);
    	redirect.addFlashAttribute("message",iRequestService.getStateMessage(requests));
    	
    	String username = principal.getWholeName();
    	redirect.addFlashAttribute("username", username);
    	
        return model;
    }
	
	/**
	 * Handles action of requestsPage depended of the user action.
	 * @param studentEmail if not empty visit Profile of Email.
	 * @param acceptStudentEmail if not empty accept Student and store to Database.
	 * @param declineStudentEmail if not empty decline Student and store to Database.
	 * @param redirectAttrs 
	 * @return the ModelAndView with updated requestPage.
	 */
	@RequestMapping(value = "/requests/action", method = RequestMethod.POST)
    public ModelAndView requestAction(@RequestParam(required=false, defaultValue="0",name="acceptRequest") long acceptedId,@RequestParam(required=false, defaultValue="0", name="declineRequest") long declinedId,RedirectAttributes redirect){
    	ModelAndView model = new ModelAndView("redirect:/requests");
    	User principal = userService.getPrincipalUser();
    	
    try{  	
   
    	if (acceptedId!=0){
    		iRequestService.acceptRequest(acceptedId);
    		redirect.addFlashAttribute("infoMessage", "You successfully accepted the Request!");
    	}
    	else if (declinedId!=0){
    		iRequestService.declineRequest(declinedId);
    		redirect.addFlashAttribute("infoMessage", "You successfully declined the Request!");
    	}
    	

    	ArrayList<Request> requests = iRequestService.getAllIncomingRequests(principal);
    	redirect.addFlashAttribute("requests", requests);
    	redirect.addFlashAttribute("message",iRequestService.getStateMessage(requests));
    	
	} catch (InvalidUserException e) {
    	e.printStackTrace();
    }
    
		String username = principal.getWholeName();
    	redirect.addFlashAttribute("username", username);
    	
        return model;
    }



}
