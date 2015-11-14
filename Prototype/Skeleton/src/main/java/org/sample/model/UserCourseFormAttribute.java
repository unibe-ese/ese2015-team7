package org.sample.model;

public class UserCourseFormAttribute {
	
	private String user;
	private String course;
	private String grade;
	private boolean teaching;
	private boolean remove = false;
	
	private String university;
	private String subject;
	
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public boolean isTeaching() {
		return teaching;
	}
	public void setTeaching(boolean teaching) {
		this.teaching = teaching;
	}
	public boolean isRemove() {
		return remove;
	}
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	
}