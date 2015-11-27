package org.sample.model.dao;

import org.sample.model.University;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Team7
 *
 */
public interface UniversityDao extends CrudRepository<University,Long> {

	University findByUniversityName(String university);
}