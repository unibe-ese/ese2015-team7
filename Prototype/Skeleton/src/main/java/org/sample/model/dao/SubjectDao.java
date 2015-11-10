package org.sample.model.dao;

import org.sample.model.Subject;
import org.sample.model.University;
import org.springframework.data.repository.CrudRepository;

public interface SubjectDao extends CrudRepository<Subject,Long> {

	Subject findBySubjectNameAndUniversity(String subject, University university);
}