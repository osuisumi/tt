package com.haoyu.tip.subjectgroup.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;

public class SubjectGroupAuthorize extends BaseEntity{
	
	private static final long serialVersionUID = -2877691859934970443L;

	private String subjectGroupId;
	
	private User user;
	
	private String role;
	
	private String state;

	public String getSubjectGroupId() {
		return subjectGroupId;
	}

	public void setSubjectGroupId(String subjectGroupId) {
		this.subjectGroupId = subjectGroupId;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public static String getId(String subjectGroupId, String userId){
		return DigestUtils.md5Hex(subjectGroupId+userId);
	}

}
