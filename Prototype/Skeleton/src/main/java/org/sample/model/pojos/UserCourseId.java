package org.sample.model.pojos;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.sample.model.Course;
import org.sample.model.User;

/**
 * This class represents the primary-key for the class UserCourse.
 * 
 * @author Team7
 *
 */
@Embeddable
public class UserCourseId implements Serializable{
	private static final long serialVersionUID = -1732951049318741876L;
	

	
	private long userCourseId;
    @NotNull
	@ManyToOne
	private User user;
    @NotNull
	@ManyToOne
	private Course course;
	
	public UserCourseId() {}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

	public long getUserCourseId() {
		return userCourseId;
	}

	public void setUserCourseId(long userCourseId) {
		this.userCourseId = userCourseId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCourseId other = (UserCourseId) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}