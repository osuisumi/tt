package com.haoyu.ncts.course.entity;

import java.math.BigDecimal;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

/**
 * 
 * 课程主题 courseTopic-course(1-n) train-courseTopic(1-n)
 */
public class CourseTopic extends BaseEntity {

	private static final long serialVersionUID = -5264274777990637793L;

	private String id;

	private String title;

	private BigDecimal studyHours;
	
	private Relation relation;

	private int courseNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getStudyHours() {
		return studyHours;
	}

	public void setStudyHours(BigDecimal studyHours) {
		this.studyHours = studyHours;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	

}
