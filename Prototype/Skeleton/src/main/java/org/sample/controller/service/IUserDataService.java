package org.sample.controller.service;

import org.sample.controller.pojos.SignupForm;
import org.sample.model.User;

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
	
	public User getUserById(Long userId);

	public User getUserByEmail(String email);
	
	/**
	 * Compares the two params and return true if they are literally equal.
	 * @param password The password of the user.
	 * @param passwordVerify The password of the user again.
	 * @return true if password is literally equal to passwordVerify.
	 */
	public boolean validatePassword(String password, String passwordVerify);

}
