package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import org.sample.model.pojos.UserCourseId;

/**
 * <p>This entity represents the connection between users and courses.</p>
 * It holds the information about who teaches what and who has what grades.
 * 
 * @author Team7
 *
 */
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
    
    @Id
    @GeneratedValue
    private long userCourseId;
    
    private int grade = 0;
    
    private boolean teaching = false;

	public long getUserCourseId() {
		return userCourseId;
	}

	public void setUserCourseId(long userCourseId) {
		this.userCourseId = userCourseId;
	}

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
	}

	@Override
	public String toString() {

		return "UserCourse [user=" + user + ", course=" + course + ", grade=" + grade + ", teaching=" + teaching + "]";
	}


	


}