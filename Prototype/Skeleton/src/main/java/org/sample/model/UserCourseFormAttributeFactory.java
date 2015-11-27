package org.sample.model;

import org.springframework.util.AutoPopulatingList.ElementFactory;
import org.springframework.util.AutoPopulatingList.ElementInstantiationException;

/**
 * This factory creates new UserCourseFormAttribute instances and implements ElementFactory<UserCourseFormAttribute>.
 * 
 * @author Team7
 *
 */
public class UserCourseFormAttributeFactory implements ElementFactory<UserCourseFormAttribute>{

	public UserCourseFormAttribute createElement(int arg0) throws ElementInstantiationException {
		return new UserCourseFormAttribute();
	}
	
}
