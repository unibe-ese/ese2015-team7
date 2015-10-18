package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    private String subjectName;
    private String degreeLevel; 
    
    private University university;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

	public String getDegreeLevel() {
		return degreeLevel;
	}

	public void setDegreeLevel(String degreeLevel) {
		this.degreeLevel = degreeLevel;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
}