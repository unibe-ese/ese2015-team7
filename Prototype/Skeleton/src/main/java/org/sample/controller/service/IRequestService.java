package org.sample.controller.service;

import java.util.ArrayList;

import org.sample.model.Request;
import org.sample.model.User;

/**
 * <p>This is the interface to work with requests.</p>
 * It provides storing, deleting and getting data from the database.
 * 
 * @author Team7
 *
 */
public interface IRequestService {


	/**
	 * Checks if request already exists and if not creates a new one which will be saved in the database.
	 * @param userCourseId the primary key of the userCourse to save.
	 * @param studentEmail the email of the student to whom the request belongs.
	 * @return 
	 */
	Request saveRequest(long userCourseId, String studentEmail);

	/**
	 * Gets all outgoing requests of entered principal.
	 * @param principal the user to get all outgoing requests from.
	 * @return a list of all requests belonging to the principal.
	 */
	ArrayList<Request> getAllOutgoingRequests(User principal);

	/**
	 * Gets all incoming Requests of entered principal
	 * @param principal the user to get all incoming requests from.
	 * @return the incoming requests of the principal.
	 */
	ArrayList<Request> getAllIncomingRequests(User principal);

	/**
	 * Deletes the request in the database with the given id.
	 * @param id primary key of the request.
	 */
	void deleteRequest(long id);

	/**
	 * Sets the flag accepted and the flag active to false of the request with the given id.
	 * @param id primary key of the request.
	 */
	void acceptRequest(long id);

	/**
	 * Sets the flag declined and the flag active to false of the request with the given id.
	 * @param id primary key of the request.
	 */
	void declineRequest(long id);

	/**
	 * Gets a message that tells whether there are no requests of a certain type.
	 * @param requests a list of request belonging to a user.
	 * @return the message that describes whether there are no requests of a certain type.
	 */
	String getStateMessage(ArrayList<Request> requests);

	

}
