package org.sample.model.dao;

import java.util.ArrayList;

import org.sample.model.Subject;
import org.sample.model.University;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>This is the interface between the database and the services concerning Subjects.</p>
 * It provides basic methods as storing, deleting, updating and getting entries in the database.
 * @author Team7
 *
 */
public interface SubjectDao extends CrudRepository<Subject,Long> {

	Subject findBySubjectName(String subjectName);
	Subject findBySubjectNameAndUniversity(String subjectName, University university);
	ArrayList<Subject> findByUniversity(University university);
}