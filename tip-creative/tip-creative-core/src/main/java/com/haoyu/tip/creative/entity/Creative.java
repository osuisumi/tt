package com.haoyu.tip.creative.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.tip.resource.entity.Resources;

public class Creative extends BaseEntity {

	private static final long serialVersionUID = 290351851147229815L;

	private String id;
	
	private String title;
	
	private String content;
	
	private String type;
	
	private String stage;
	
	private String subject;
	
	private String grade;
	
	private String state;
	
	private String claimType;
	
	private String claimState;
	
	private String claimRole;
	
	private CreativeUser creativeUser;
	
	private List<CreativeRelation> creativeRelations = Lists.newArrayList();

	private List<Resources> resources = Lists.newArrayList();
	
	public Creative() {
	}
	
	public Creative(String id) {
		this.id = id;
	}


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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getClaimState() {
		return claimState;
	}

	public void setClaimState(String claimState) {
		this.claimState = claimState;
	}

	public String getClaimRole() {
		return claimRole;
	}

	public void setClaimRole(String claimRole) {
		this.claimRole = claimRole;
	}

	public CreativeUser getCreativeUser() {
		return creativeUser;
	}

	public void setCreativeUser(CreativeUser creativeUser) {
		this.creativeUser = creativeUser;
	}

	public List<CreativeRelation> getCreativeRelations() {
		return creativeRelations;
	}

	public void setCreativeRelations(List<CreativeRelation> creativeRelations) {
		this.creativeRelations = creativeRelations;
	}

	public List<Resources> getResources() {
		return resources;
	}

	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}
	
}
