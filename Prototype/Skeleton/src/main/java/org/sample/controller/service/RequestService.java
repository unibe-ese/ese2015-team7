package org.sample.controller.service;

import java.util.Date;

import org.sample.model.Request;
import org.sample.model.dao.RequestDao;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestService implements IRequestService{
	
	@Autowired
	RequestDao requestDao;

	public void saveRequest(String tutorEmail, String studentEmail) {
	
		Request request = new Request();
		
		request.setStudentEmail(studentEmail);
		request.setTutorEmail(tutorEmail);
		
		Date date = new Date();
		request.setDate(date);
		
		request.setIsActiv(true);

		request.setNewRequest(true);
		
		requestDao.save(request);
	}

}
