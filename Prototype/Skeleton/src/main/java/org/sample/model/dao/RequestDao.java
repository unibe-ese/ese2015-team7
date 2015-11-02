package org.sample.model.dao;

import org.sample.model.Course;
import org.sample.model.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestDao extends CrudRepository<Request,Long> {

}
