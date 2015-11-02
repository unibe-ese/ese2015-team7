package org.sample.controller.service;


import java.util.ArrayList;
import java.util.Iterator;

import org.sample.model.Course;
import org.sample.model.Request;
import org.sample.model.Tutor;
import org.sample.model.User;
import org.sample.model.dao.RequestDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestService implements IRequestService{
	
	@Autowired
	RequestDao requestDao;
	
	@Autowired
	UserDao userDao;

	@Transactional
	public void saveRequest(String tutorEmail, String studentEmail) {
	
		Request request = new Request();
		
		request.setStudent(userDao.findByEmail(studentEmail));
		request.setTutor(userDao.findByEmail(tutorEmail));
		
		//Date date = new Date();
		//request.setDate(date);
		
		request.setIsActiv(true);
		
		request.setNewRequest(true);
		
		requestDao.save(request);
	}


	public ArrayList<Request> getAllMyRequests(User principal) {
	
		
        ArrayList<Request> myRequestList = new ArrayList<Request>();
        
        Iterator<Request> myRequestIter	= requestDao.findAll().iterator();
        while(myRequestIter.hasNext())
        {
        	Request request = myRequestIter.next();
        	if((request.getStudent().equals(principal))&&request.getIsActiv()&&!request.getIsAccepted()&&!request.getIsDeclined()&&!request.getIsDeleted())
        		myRequestList.add(request);
        	
        }
       
		return myRequestList;
	}

	public ArrayList<Request> getAllRequests(User principal) {
		ArrayList<Request> requestList = new ArrayList<Request>();
        Iterator<Request> requestIter	= requestDao.findAll().iterator();
        while(requestIter.hasNext())
        {
        	Request request = requestIter.next();
        	if((request.getTutor().equals(principal))&&request.getIsActiv()&&!request.getIsAccepted()&&!request.getIsDeclined()&&!request.getIsDeleted())
        		requestList.add(request);
        }
		return requestList;
	}




}
