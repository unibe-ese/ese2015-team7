package org.sample.controller.service;

import org.sample.controller.pojos.SignupForm;
import org.sample.model.User;

public interface IUserDataService {

	SignupForm saveFrom(SignupForm signupForm);

	User getUserByEmail(String email);

	boolean validatePassword(String password, String passwordVarify);

}
