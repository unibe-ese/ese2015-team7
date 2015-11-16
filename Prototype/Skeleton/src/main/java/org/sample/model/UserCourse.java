package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.sample.model.pojos.UserCourseId;

@IdClass(UserCourseId.class)
@Entity
public class UserCourse
{
	@Id
	@ManyToOne(targetEntity=User.class)
	private User user;
    
    @Id
    @ManyToOne(targetEntity=Course.class)
    private Course course;
    
    private int grade = 0;
    
    private boolean teaching = false;
    
    private String tutorsName; 
    
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public boolean isTeaching() {
		return teaching;
	}

	public void setTeaching(boolean teaching) {
		this.teaching = teaching;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.setTutorsName(this.user.getName().substring(0, this.user.getName().length()));
	}

	@Override
	public String toString() {
		return "Tutor [user=" + user + ", course=" + course + ", tutorsName=" + tutorsName + "]";
	}

	public String getTutorsName() {
		return tutorsName;
	}

	public void setTutorsName(String tutorsName) {
		this.tutorsName = tutorsName;
	}
	

}