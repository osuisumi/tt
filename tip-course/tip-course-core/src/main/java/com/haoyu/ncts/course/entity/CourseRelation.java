package com.haoyu.ncts.course.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;

public class CourseRelation extends BaseEntity {

	private static final long serialVersionUID = 795569624319151034L;

	private String id;

	private Course course;

	private Relation relation;

	private TimePeriod timePeriod;

	private int participateNum;

	private Object relationEntity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public int getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(int participateNum) {
		this.participateNum = participateNum;
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

	public static String getId(String courseId, String relationId) {
		return DigestUtils.md5Hex(courseId + relationId);
	}

	public Object getRelationEntity() {
		return relationEntity;
	}

	public void setRelationEntity(Object relationEntity) {
		this.relationEntity = relationEntity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseRelation other = (CourseRelation) obj;
		if (id == null || other.getId() == null) {
			if (this.relation == null || other.getRelation() == null) {
				return false;
			}
			if (this.course == null || other.course == null) {
				return false;
			}
			if (!this.relation.getId().equals(other.getRelation().getId())) {
				return false;
			}
			if (!this.course.getId().equals(other.getCourse().getId())) {
				return false;
			}
			return true;
		}
		return this.id.equals(other.getId());
	}

}
