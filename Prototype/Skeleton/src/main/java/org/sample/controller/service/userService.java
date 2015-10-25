package org.sample.controller.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.model.User;
import org.sample.model.UserRole;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class userService implements IUserDataService{
	
	@Autowired
	UserDao userDao;

	public SignupForm saveFrom(SignupForm signupForm) {
		String name = signupForm.getName();

		if (!StringUtils.isEmpty(name) && "ESE".equalsIgnoreCase(name)) {
		    throw new InvalidUserException("Sorry, ESE is not a valid name"); // throw exception
		}

		BCryptPasswordEncoder password = new BCryptPasswordEncoder();

		User user = new User();
		user.setName(signupForm.getName());
		user.setEmail(signupForm.getEmail());

		user.setPassword(password.encode(signupForm.getPassword()));

		user.setEnabled(true);

		Set<UserRole> userRole = new HashSet<UserRole>();
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRole.add(role);

		user.setUserRole(userRole);

		user = userDao.save(user); // save object to DB

		signupForm.setId(user.getId());

		return signupForm;
		
	}

	public User getUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

}
