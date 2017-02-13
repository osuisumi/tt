package com.haoyu.ncts.course.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.ncts.clazz.entity.Class;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.User;

public class CourseRegister extends BaseEntity {
	private static final long serialVersionUID = 185262342941137626L;

	private String id;

	private Relation relation;

	private Course course;

	private User user;

	private String state;

	private String type;
	
	private Class clazz;

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object others){
		if(others == null){
			return false;
		}
		CourseRegister cr = (CourseRegister)others;
		if(this.id == null || cr.getId() == null){
			if(this.user == null || this.course == null || this.relation == null){
				return false;
			}
			if(cr.getUser() == null || cr.getCourse() == null || cr.getRelation() == null){
				return false;
			}
			if(this.user.getId().equals(cr.getUser().getId())&&this.course.getId().equals(cr.getCourse().getId())&&this.relation.getId().equals(cr.getRelation().getId()) ){
				return true;
			}
			return false;
		}else{
			return this.id.equals(cr.getId());
		}
	}
	
	public static String getId(String courseId,String userId){
		return DigestUtils.md5Hex(courseId + userId);
	}

}
