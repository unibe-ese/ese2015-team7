package org.sample.model.dao;

import org.sample.model.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectDao extends CrudRepository<Subject,Long> {
}