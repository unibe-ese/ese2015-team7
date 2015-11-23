package org.sample.model.dao;

import java.util.ArrayList;

import org.sample.model.Subject;
import org.sample.model.University;
import org.springframework.data.repository.CrudRepository;

public interface SubjectDao extends CrudRepository<Subject,Long> {

	Subject findBySubjectName(String subjectName);
	Subject findBySubjectNameAndUniversity(String subjectName, University university);
	ArrayList<Subject> findByUniversity(University university);
}