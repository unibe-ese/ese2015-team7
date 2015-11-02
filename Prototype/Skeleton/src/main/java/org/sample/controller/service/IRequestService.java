package org.sample.controller.service;

import java.util.ArrayList;

import org.sample.model.Request;
import org.sample.model.User;

public interface IRequestService {

	void saveRequest(String tutorEmail, String studentEmail);

	ArrayList<Request> getAllMyRequests(User principal);

	ArrayList<Request> getAllRequests(User principal);

	void deleteRequest(User user);

	void acceptRequest(User student);

	void declineRequest(User student);

	

}
