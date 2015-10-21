package org.sample.model.dao;

import org.sample.model.University;
import org.springframework.data.repository.CrudRepository;

public interface UniversityDao extends CrudRepository<University,Long> {
}