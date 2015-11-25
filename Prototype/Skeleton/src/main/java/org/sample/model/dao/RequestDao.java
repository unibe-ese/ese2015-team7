package org.sample.model.dao;

import java.util.ArrayList;

import org.sample.model.Request;
import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;

public interface RequestDao extends CrudRepository<Request,Long> {
	
	Request findByUserCourseIdAndStudent(long userCourseId, User student);
	Request findById(long id);
	ArrayList<Request> findByStudent(User student);

}
