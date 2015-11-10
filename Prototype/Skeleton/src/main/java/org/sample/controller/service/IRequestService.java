package org.sample.controller.service;

import java.util.ArrayList;

import org.sample.model.Course;
import org.sample.model.Request;
import org.sample.model.User;

public interface IRequestService {

	void saveRequest(String tutorEmail, String studentEmail, Course course);

	ArrayList<Request> getAllMyRequests(User principal);

	ArrayList<Request> getAllRequests(User principal);

	void deleteRequest(User tutor, User student);

	void acceptRequest(User tutor, User student);

	void declineRequest(User tutor, User student);

	

}
