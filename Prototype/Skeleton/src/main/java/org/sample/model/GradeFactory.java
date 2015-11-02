package org.sample.model;

import org.springframework.util.AutoPopulatingList.ElementFactory;
import org.springframework.util.AutoPopulatingList.ElementInstantiationException;

public class GradeFactory implements ElementFactory<Grade>{

	public Grade createElement(int arg0) throws ElementInstantiationException {
		return new Grade();
	}
	
}
