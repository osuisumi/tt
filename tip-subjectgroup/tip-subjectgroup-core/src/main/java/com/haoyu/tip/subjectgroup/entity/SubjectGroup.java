package com.haoyu.tip.subjectgroup.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.follow.entity.Follow;

public class SubjectGroup extends BaseEntity{

	private static final long serialVersionUID = -1478217177477907519L;

	private String name;
	
	private String summary;
	
	private Subject subject;
	
	private String stage;
	
	private String state;
	
	private String imageUrl;
	
	private List<User> masters = Lists.newArrayList();
	
	private List<User> members = Lists.newArrayList();
	
	private List<SubjectGroupRelation> subjectGroupRelations = Lists.newArrayList();
	
	private SubjectGroupAuthorize subjectGroupAuthorize;
	
	private Follow follow;
	
	public Follow getFollow() {
		return follow;
	}

	public void setFollow(Follow follow) {
		this.follow = follow;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<SubjectGroupRelation> getSubjectGroupRelations() {
		return subjectGroupRelations;
	}

	public void setSubjectGroupRelations(List<SubjectGroupRelation> subjectGroupRelations) {
		this.subjectGroupRelations = subjectGroupRelations;
	}

	public SubjectGroupAuthorize getSubjectGroupAuthorize() {
		return subjectGroupAuthorize;
	}

	public void setSubjectGroupAuthorize(SubjectGroupAuthorize subjectGroupAuthorize) {
		this.subjectGroupAuthorize = subjectGroupAuthorize;
	}

	public List<User> getMasters() {
		return masters;
	}

	public void setMasters(List<User> masters) {
		this.masters = masters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

}
