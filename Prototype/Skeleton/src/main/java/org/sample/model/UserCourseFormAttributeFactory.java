package org.sample.model;

import org.springframework.util.AutoPopulatingList.ElementFactory;
import org.springframework.util.AutoPopulatingList.ElementInstantiationException;

public class UserCourseFormAttributeFactory implements ElementFactory<UserCourseFormAttribute>{

	public UserCourseFormAttribute createElement(int arg0) throws ElementInstantiationException {
		return new UserCourseFormAttribute();
	}
	
}
