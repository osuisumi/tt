package com.haoyu.ncts.course.entity;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;

public class CourseAuthorize extends BaseEntity{
	
	private static final long serialVersionUID = 2100639121580861372L;

	private String id;
	
	private Course course;
	
	private User user;
	
	private String role;
	
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
    public boolean equals(Object obj) {
    	if (obj instanceof CourseAuthorize) {
			obj = (CourseAuthorize)obj;
			if (StringUtils.isNotEmpty(this.getId()) && StringUtils.isNotEmpty(((CourseAuthorize) obj).getId())) {
				if (((CourseAuthorize) obj).getId().equals(this.getId())) {
					return true;
				}
			}
		}
    	return false;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

}
