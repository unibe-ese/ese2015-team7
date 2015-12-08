package org.sample.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.IUserService;
import org.sample.controller.service.ISearchService;
import org.sample.controller.service.ISelectService;
import org.sample.model.Course;
import org.sample.model.Request;
import org.sample.model.UserCourseFormAttributeFactory;
import org.sample.model.dao.RequestDao;
import org.sample.model.dao.UserCourseDao;
import org.sample.model.Subject;
import org.sample.model.TimeSlot;
import org.sample.model.TimeSlotFactory;
import org.sample.model.University;
import org.sample.model.User;
import org.sample.model.UserCourse;
import org.sample.model.UserCourseFormAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This controller handles all requests concerning the editing and displaying of the profileData.
 * 
 * @author Team7
 *
 */

@Controller
public class ProfileController {
	
	@Autowired IUserService userService;
	@Autowired ISearchService searchService;
	@Autowired UserCourseDao userCourseDao;
	@Autowired RequestDao requestDao;
	@Autowired ISelectService selectService;
	
	/**
	 * displays the profile data of the principal if no user is entered.
	 * @param theUser used to determine whether theUser is visiting his profile or an other profile.
	 * @return the profile page.
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(@RequestParam(required=false,name="user") User theUser)
	{
    	ModelAndView model = new ModelAndView("profile");
    	User principal = userService.getPrincipalUser();
    	String principalEmail = principal.getEmail();
    	model.addObject("principalEmail", principalEmail);
    	
    	User user = principal;
    		if (theUser!=null)
    			user = theUser;
    	model.addObject(user);
    	
    	
    	ArrayList<UserCourse> userCourses = new ArrayList<UserCourse>(); 
        userCourses =  userCourseDao.findByUser(user);
        model.addObject("userCourses", userCourses);

        return model;
    }
	
	/**
	 * shows profile of the user belonging to tutorEmailaddress.
	 * @param userCourseId to determine which request has to be sent when visiting an other student's profile.
	 * @param studentsEmail of Tutor to be displayed 
	 * @return ModelView profile of the entered student that is teaching the course belonging to userCourseId.
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView postProfile(@RequestParam(required=false, defaultValue="0", name="userCourseId") long userCourseId, 
    		@RequestParam(required=false, name="studentsEmail") String studentsEmail)
	{
    	ModelAndView model = new ModelAndView("profile");
    	User principal=userService.getPrincipalUser();
    	User user = new User();
    	
    	if(studentsEmail!=null){
    		user = userService.getUserByEmail(studentsEmail);
    		userCourseId=0;
    	}
    	else if(userCourseId!=0)
    	{
	    	user = selectService.getUserFromUserCourseId(userCourseId);
	    	Request outgoingRequestWithUserCourse = requestDao.findByUserCourseIdAndStudent(userCourseId, principal);
	    	if(outgoingRequestWithUserCourse==null||(!outgoingRequestWithUserCourse.getIsAccepted()&&!outgoingRequestWithUserCourse.getIsActiv()))
	    	{
		    	Course selectedCourse = selectService.getCourseFromUserCourseId(userCourseId);
		    	model.addObject("selectedCourse", selectedCourse);
		    	
		    	Subject selectedSubject = selectService.getSubjectFromUserCourseId(userCourseId);
		    	model.addObject("selectedSubject", selectedSubject);
		    	
		    	University selectedUniversity = selectService.getUniversityFromUserCourseId(userCourseId);
		    	model.addObject("selectedUniversity", selectedUniversity);
	    	}
	    	else{
	    		userCourseId=0;
	    	}
    	}
    	
    	model.addObject("userCourseId", userCourseId);
    	model.addObject(user);
    	
    	String principalEmail=principal.getEmail();
    	model.addObject("principalEmail", principalEmail);
    	
    	ArrayList<UserCourse> userCourses = new ArrayList<UserCourse>(); 
        userCourses =  userCourseDao.findByUser(user);
        model.addObject("userCourses", userCourses);
    	
        return model;
    }
	
	/**
	 * Serve model with signupForm.
	 * @param principal the logged in principal.
	 * @return a signupForm with filled name and biography fields.
	 */
	@ModelAttribute("signupForm")
	public SignupForm getSignupForm(Principal principal)
	{
		User user = userService.getPrincipalUser();
    	
    	SignupForm form = new SignupForm();
    	form.setFirstName(user.getFirstName());
    	form.setLastName(user.getLastName());
    	form.setBiography(user.getBiography());
    	
    	try{
	    	AutoPopulatingList<UserCourseFormAttribute> userCourseList = new AutoPopulatingList<UserCourseFormAttribute>(new UserCourseFormAttributeFactory());
	    	Iterator<UserCourse> itr = userCourseDao.findByUser(user).iterator();
	    	while(itr.hasNext())
	    	{
	    		UserCourse tmpUserCourse = itr.next();
	    		UserCourseFormAttribute formAttr = new UserCourseFormAttribute();
	    		formAttr.setUniversity(tmpUserCourse.getCourse().getSubject().getUniversity().toString());
	    		formAttr.setSubject(tmpUserCourse.getCourse().getSubject().toString());
	    		formAttr.setCourse(tmpUserCourse.getCourse().toString());
	    		formAttr.setGrade( String.valueOf(tmpUserCourse.getGrade()) );
	    		formAttr.setTeaching( tmpUserCourse.isTeaching() );
	    		userCourseList.add(formAttr);
	    	}
			form.setUserCourseList(userCourseList);
		} 
    	catch(Exception e){}
		
    	try{
			AutoPopulatingList<TimeSlot> timeSlotList = new AutoPopulatingList<TimeSlot>(new TimeSlotFactory());
	    	ListIterator<TimeSlot> iter = user.getTimeSlots().listIterator();
	    	while(iter.hasNext())
	    	{
	    		timeSlotList.add(iter.next());
	    	}
			form.setTimeSlots(timeSlotList);
		} 
    	catch(Exception e){}
		
		return form;
	}
	
	/**
	 * Serve model with universities.
	 * @return universities from the database.
	 */
	@ModelAttribute("universities")
	public ArrayList<University> getUniversities()
	{
		ArrayList<University> universities = new ArrayList<University>(); 
        universities =  searchService.getUniversities();
		return universities;
	}
	
	/**
	 * Serve model with subjects.
	 * @return the subjects from the database.
	 */
	@ModelAttribute("subjects")
	public ArrayList<Subject> getSubjects()
	{
		ArrayList<Subject> subjects = new ArrayList<Subject>();
    	subjects = searchService.getSubjects();
		return subjects;
	}
	
	
	/**
	 * <p>Loads the editProfile.jsp page</p>
	 * @param principal the logged in principal.
	 * @return the editProfile page.
	 */
	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public ModelAndView editProfile(Principal principal)
	{
    	ModelAndView model = new ModelAndView("editProfile");
    	
    	User user = userService.getPrincipalUser();
    	model.addObject(user);
    	
     	ArrayList<Course> courses = searchService.getCourses();
     	ArrayList<Course> nonUserCourses = new ArrayList<Course>();
     	for(Course course : courses)
     	{
     		if(userCourseDao.findByUserAndCourse(user, course)==null)
     		{
     			nonUserCourses.add(course);
     		}
     	}
     	
     	model.addObject("courses", nonUserCourses);
    	
        return model;
    }
	
	/**
	 * Appends a new row to courses in editProfile.
	 * @param fieldId the id number of the next courses row.
	 * @param model the current model.
	 * @return the editProfile page with one more courses row.
	 */
	@RequestMapping(value="/editProfileAppend", method = RequestMethod.GET)
	protected String appendGradeField(@RequestParam Integer fieldId, ModelMap model)
	{
		model.addAttribute("gradeNumber", fieldId);
		return "template/coursesRow";
	}
	
	/**
	 * Appends a new row to the timeSlots in editProfile.
	 * @param fieldId the id number of the next timeSlot row.
	 * @param model the current model.
	 * @return the editProfile page with one more timeSlot row.
	 */
	@RequestMapping(value="/editProfileAttach", method = RequestMethod.GET)
	protected String attachTimeSlotField(@RequestParam Integer fieldId, ModelMap model)
	{	
		model.addAttribute("timeSlotNumber", fieldId);
		return "template/timeSlotRow";
	}
	
	/**
	 * Validates all changes and tries to save them to the database.
	 * @param principal the user that edited his profile.
	 * @param signupForm the form which contains all information about the principal.
	 * @param result contains error messages.
	 * @param redirectAttributes used to add some attributes to the model.
	 * @param session the current session especially containing the username.
	 * @return the profile page if validations succeeded else returns the editProfile page with attached errors.
	 */
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
    public ModelAndView postProfile(Principal principal, @Validated(SignupForm.SignupValidatorGroup.class) SignupForm signupForm,
    		BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) 
	{
		User user = userService.getPrincipalUser();
		signupForm.setEmail(user.getEmail());
		ModelAndView model;
	
		try {
		    if ( !signupForm.getPassword().equals(signupForm.getPasswordVerify()) ) {
			    redirectAttributes.addFlashAttribute("passwordVerifyError", "Your passwords do not match!");
			    redirectAttributes.addFlashAttribute("infoMessage", "Your passwords do not match!");
			    return new ModelAndView("redirect:/editProfile");
			}
		} 
		catch (Exception d) {}
			
	
		if (!result.hasErrors()) 
		{
		    try {
				User updatedUser = userService.saveFrom(signupForm, user);
				userService.createAndSaveUserCoursesFromForm(signupForm, user);
				redirectAttributes.addFlashAttribute("infoMessage", "You successfully edited your profile!");
				session.setAttribute("username", updatedUser.getWholeName());
		
				model = new ModelAndView("redirect:/profile");
			    } 
		    catch(InvalidUserException e) 
		    {
				model = new ModelAndView("editProfile");
				model.addObject("page_error", e.getMessage());
		    }
		} 
		else {
		    model = new ModelAndView("editProfile");
		    model.addObject("infoMessage", result.toString());
		    System.out.println("Form has errors:\n" + result.toString());
		}
		return model;
	}
	
}
