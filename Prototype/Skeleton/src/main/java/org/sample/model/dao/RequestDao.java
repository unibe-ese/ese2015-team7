package org.sample.model.dao;

import java.util.ArrayList;

import org.sample.model.Request;
import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>This is the interface between the database and the services concerning Requests.</p>
 * It provides basic methods as storing, deleting, updating and getting entries in the database.
 * @author Team7
 *
 */
public interface RequestDao extends CrudRepository<Request,Long> {
	
	Request findByUserCourseIdAndStudent(long userCourseId, User student);
	Request findById(long id);
	ArrayList<Request> findByStudent(User student);
	ArrayList<Request> findByUserCourseId(long userCourseId);

}
