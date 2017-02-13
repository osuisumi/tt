package com.haoyu.tip.subjectgroup.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class SubjectGroupRelation extends BaseEntity{
	
	private static final long serialVersionUID = 8022844870784772061L;

	private SubjectGroup subjectGroup;
	
	private Relation relation;;
	
	private int memberNum;
	
	private int activityNum;
	
	private int planNum;

	public SubjectGroup getSubjectGroup() {
		return subjectGroup;
	}

	public void setSubjectGroup(SubjectGroup subjectGroup) {
		this.subjectGroup = subjectGroup;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public int getActivityNum() {
		return activityNum;
	}

	public void setActivityNum(int activityNum) {
		this.activityNum = activityNum;
	}

	public int getPlanNum() {
		return planNum;
	}

	public void setPlanNum(int planNum) {
		this.planNum = planNum;
	}

	public static String getId(String subjectGroupId, String relationId) {
		return DigestUtils.md5Hex(subjectGroupId+relationId);
	}

}
