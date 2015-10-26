package org.sample.controller.service;

import org.sample.controller.pojos.SignupForm;
import org.sample.model.User;

public interface IUserDataService {

	public SignupForm saveFrom(SignupForm signupForm);
	
	public User getUserById(Long userId);

	public User getUserByEmail(String email);
	
	public boolean validatePassword(String password, String passwordVarify);

}
