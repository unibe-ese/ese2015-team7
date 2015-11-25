package org.sample.controller.service;

import java.util.ArrayList;

import org.sample.model.Request;
import org.sample.model.User;

public interface IRequestService {


	/**
	 * checks if Request already exists or creates a new one which will be saved in the Database
	 * @param tutorEmail
	 * @param studentEmail
	 * @param course
	 */
	void saveRequest(long userCourseId, String studentEmail);

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

	
	void deleteRequest(long id);

	
	void acceptRequest(long id);

	
	void declineRequest(long id);

	String getStateMessage(ArrayList<Request> requests);

	

}
