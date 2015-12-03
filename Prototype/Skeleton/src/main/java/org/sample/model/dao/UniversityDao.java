package org.sample.model.dao;

import org.sample.model.University;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>This is the interface between the database and the services concerning Universities.</p>
 * It provides basic methods as storing, deleting, updating and getting entries in the database.
 * 
 * @author Team7
 *
 */
public interface UniversityDao extends CrudRepository<University,Long> {

	University findByUniversityName(String university);
}