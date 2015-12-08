package org.sample.controller.service;


import java.util.ArrayList;
import java.util.Iterator;

import org.sample.model.Request;
import org.sample.model.User;
import org.sample.model.UserCourse;
import org.sample.model.dao.CourseDao;
import org.sample.model.dao.RequestDao;
import org.sample.model.dao.UserCourseDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Team7
 *
 */
@Service
public class RequestService implements IRequestService{
	
	@Autowired	RequestDao requestDao;
	@Autowired	UserDao userDao;
	@Autowired CourseDao courseDao;
	@Autowired UserCourseDao userCourseDao;

	

	
	@Transactional
	public Request saveRequest(long userCourseId, String studentEmail) {
		
		User student = userDao.findByEmail(studentEmail);
		
		Request oldRequest;
		try{
			oldRequest= requestDao.findByUserCourseIdAndStudent(userCourseId, student);
		}catch (Exception e){
			oldRequest=null;
		}
		Request request;
		if (oldRequest != null){
			request=oldRequest;
			if (request.getIsDeleted()){
				request.setIsDeleted(false);
				request.setIsActiv(true);
			}
			if (request.getIsDeclined()){
				request.setIsDeclined(false);
				request.setIsActiv(true);
			}
				
			
		}else {
				request= new Request();
				
				UserCourse userCourse = userCourseDao.findByUserCourseId(userCourseId);
				request.setUserCourse(userCourse);
				request.setStudent(student);
			}
				
		request.setIsActiv(true);
		
		request.setNewRequest(true);
		
		request = requestDao.save(request);
		
		return request;
	}

	public ArrayList<Request> getAllOutgoingRequests(User principal) {
        ArrayList<Request> myRequestList = requestDao.findByStudent(principal);
		return myRequestList;
	}

	public ArrayList<Request> getAllIncomingRequests(User principal) {
		ArrayList<Request> requestList = new ArrayList<Request>();
        Iterator<Request> requestIter	= requestDao.findAll().iterator();
        while(requestIter.hasNext())
        {
        	Request request = requestIter.next();
        	User requestUser = request.getUserCourse().getUser();
        	if((requestUser.equals(principal)))
        		requestList.add(request);
        }
		return requestList;
	}

	public void deleteRequest(long id) {
		
		Request request=requestDao.findById(id);
		request.setIsDeleted(true);
		request.setIsActiv(false);
		requestDao.delete(request);
	}

	public void acceptRequest(long id) {
		Request request=requestDao.findById(id);
		request.setIsAccepted(true);
		request.setIsActiv(false);
		requestDao.save(request);
		
	}

	public void declineRequest(long id) {
		Request request=requestDao.findById(id);
		request.setIsDeclined(true);
		request.setIsActiv(false);
		requestDao.delete(request);
		
	}

	@Override
	public String getStateMessage(ArrayList<Request> requests) {
		Boolean acceptedNotExists=true;
		Boolean unanwserNotExists=true;
		String message=null;
    	for (Request request:requests){
    		if (request.getIsAccepted())
    			acceptedNotExists=false;
    		if (request.getIsActiv())
    			unanwserNotExists=false;
    	}
    	if (acceptedNotExists&&!unanwserNotExists)
    		message= "You have no Accepted Requests";
    	else if (!acceptedNotExists&&unanwserNotExists)
    		message= "You have no Unanwsered Requests";
    	else if (acceptedNotExists&&unanwserNotExists)
    		message= "You have no Accepted and no Unanwsered Requests";
    	return message;
	}
}
