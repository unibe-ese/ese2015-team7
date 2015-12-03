package org.sample.controller.service;

import org.sample.controller.pojos.SignupForm;
import org.sample.model.User;

/**
 * <p>This is the interface to work with user data.</p>
 * It provides storing and getting data from the database.
 * 
 * @author Team7
 *
 */
public interface IUserDataService {

	/**
	 * Creates a new user in the database based on the information of the signupForm.
	 * @param signupForm The completed form of which to take the information.
	 * @return the completed SignupForm.
	 */
	public SignupForm saveFrom(SignupForm signupForm);
	
	/**
	 * Updates or creates a new user in the database based on the information of the signupForm.
	 * If the param userToUpdate is null, it will create a new user.
	 * 
	 * @param signupForm The completed form of which to take the information.
	 * @param userToUpdate The user to update or null if a new one should be created.
	 * @return the completed SignupForm.
	 */
	public SignupForm saveFrom(SignupForm signupForm, User userToUpdate);
	
	/**
	 * Creates tutorLinks from the given information of the signupForm and saves them to database.
	 * <br>
	 * A tutorLink is one tutor-entry in the database.
	 * @param signupForm all information about the user.
	 * @param user the user to whom the signupForm belongs.
	 */
	public void createAndSaveUserCourseFromForm(SignupForm signupForm, User user);
	
	public User getUserById(Long userId);

	public User getUserByEmail(String email);
	
	/**
	 * returns the principal user using the getUserByEmail method.
	 * 
	 * @return the principal user.
	 */
	public User getPrincipalUser();
	
	/**
	 * Compares the two params and return true if they are literally equal.
	 * @param password The password of the user.
	 * @param passwordVerify The password of the user again.
	 * @return true if password is literally equal to passwordVerify.
	 */
	public boolean validatePassword(String password, String passwordVerify);


}
