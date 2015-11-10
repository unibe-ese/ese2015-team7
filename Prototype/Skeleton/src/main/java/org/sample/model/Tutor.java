package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;

import org.sample.model.pojos.TutorId;

@IdClass(TutorId.class)
@Entity
public class Tutor
{
	@Id
	@OneToOne(targetEntity=User.class)
	private User user;
    
    @Id
    @OneToOne(targetEntity=Course.class)
    private Course course;
    
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString(){
		return user.getName();
	}
	

}