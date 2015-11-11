package org.sample.controller.service;


import java.util.ArrayList;
import java.util.Iterator;

import org.sample.model.Course;
import org.sample.model.Request;
import org.sample.model.User;
import org.sample.model.dao.RequestDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mirko
 *
 */
@Service
public class RequestService implements IRequestService{
	
	@Autowired 	RequestDao requestDao;
	@Autowired 	UserDao userDao;

	

	
	@Transactional
	public void saveRequest(String tutorEmail, String studentEmail, Course course) {
		Request oldRequest= requestDao.findByTutorAndStudentAndCourse(userDao.findByEmail(tutorEmail), userDao.findByEmail(studentEmail),course);
		
		Request request;
		if (oldRequest != null)
			request=oldRequest;
			else {
				request= new Request();
				
				request.setCourse(course);
				request.setStudent(userDao.findByEmail(studentEmail));
				request.setTutor(userDao.findByEmail(tutorEmail));
			}
				
		
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
        	if((request.getStudent().equals(principal))&&request.getIsActiv())
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
        	if((request.getTutor().equals(principal))&&request.getIsActiv())
        		requestList.add(request);
        }
		return requestList;
	}


	public void deleteRequest(User tutor, User student) {
		
		Request request=requestDao.findByTutorAndStudent(tutor, student);
		request.setIsDeleted(true);
		request.setIsActiv(false);
		requestDao.save(request);
	}


	public void acceptRequest(User tutor, User student) {
		Request request=requestDao.findByTutorAndStudent(tutor, student);
		request.setIsAccepted(true);
		request.setIsActiv(false);
		requestDao.save(request);
		
	}


	public void declineRequest(User tutor, User student) {
		Request request=requestDao.findByTutorAndStudent(tutor, student);
		request.setIsDeclined(true);
		request.setIsActiv(false);
		requestDao.save(request);
		
	}

}
