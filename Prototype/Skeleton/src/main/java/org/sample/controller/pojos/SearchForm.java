package org.sample.controller.pojos;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.sample.model.Tutor;

public class SearchForm 
{
	
    private Long id;
    @NotNull
    private String university;
    //@NotNull
    private String subject;
    //@NotNull
    private String course;
    
    private ArrayList<Tutor> tutors;

	public ArrayList<Tutor> getTutors() {
		return tutors;
	}

	public void setTutors(ArrayList<Tutor> tutors) {
		this.tutors = tutors;
	}

	public Long getId() {
        return id;
    }

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setId(Long id) {
        this.id = id;
    }
}
