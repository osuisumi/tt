package com.haoyu.ncts.clazz.entity;

import com.haoyu.ncts.course.entity.Course;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class Class extends BaseEntity{
	
	private static final long serialVersionUID = -3701737255190571637L;

	private String id;
	
	private String name;
	
	private Course course;
	
	private Relation relation;
	
	//以下非数据库字段
	private int personNum;

	public int getPersonNum() {
		return personNum;
	}

	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

}
