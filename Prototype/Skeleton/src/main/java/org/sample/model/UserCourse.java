package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;



/**
 * <p>This entity represents the connection between users and courses.</p>
 * It holds the information about who teaches what and who has what grades.
 * 
 * @author Team7
 *
 */
@Entity
@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"user", "course"})
	)
public class UserCourse
{
    @Id
    @GeneratedValue
    private long userCourseId;
    
    @NotNull
    @ManyToOne(targetEntity=Course.class,  fetch=FetchType.EAGER)
    @JoinColumn(name="course")
    private Course course;
    
    @NotNull
    @ManyToOne(targetEntity=User.class,  fetch=FetchType.EAGER)
    @JoinColumn(name="user")
	private User user;
    
    
    private float grade = 0;
    
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

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
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
	
	public University getUniversity(){
		return this.course.getSubject().getUniversity();
	}
	
	public Subject getSubject(){
		return this.course.getSubject();
	}
	

	@Override
	public String toString() {

		return "UserCourse [user=" + user + ", course=" + course + ", grade=" + grade + ", teaching=" + teaching + "]";
	}


	


}