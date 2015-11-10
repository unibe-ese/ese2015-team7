package org.sample.controller.service;

import java.util.ArrayList;

import org.sample.model.Course;
import org.sample.model.Request;
import org.sample.model.User;

public interface IRequestService {

	/**
	 * checks if Request already exists or creates a new one which will be saved in the Database
	 * @param tutorEmail
	 * @param studentEmail
	 * @param course
	 */
	void saveRequest(String tutorEmail, String studentEmail, Course course);

	/**¨
	 *gets all outgoing Requests of entered Principal
	 * @param principal
	 * @return
	 */
	ArrayList<Request> getAllMyRequests(User principal);

	/**
	 * gets all incoming Requests of entered principal
	 * @param principal
	 * @return
	 */
	ArrayList<Request> getAllRequests(User principal);

	
	void deleteRequest(User tutor, User student);

	
	void acceptRequest(User tutor, User student);

	
	void declineRequest(User tutor, User student);

	

}
