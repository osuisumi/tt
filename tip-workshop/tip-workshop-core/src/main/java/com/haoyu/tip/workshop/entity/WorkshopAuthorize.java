package com.haoyu.tip.workshop.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;

public class WorkshopAuthorize extends BaseEntity{

	private static final long serialVersionUID = -8117273660326138866L;
	
	private Workshop workshop;
	
	private WorkshopRelation workshopRelation;
	
	private User user;
	
	private String role;
	
	private String state;

	public WorkshopRelation getWorkshopRelation() {
		return workshopRelation;
	}

	public void setWorkshopRelation(WorkshopRelation workshopRelation) {
		this.workshopRelation = workshopRelation;
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

	public Workshop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(Workshop workshop) {
		this.workshop = workshop;
	}

	public static String getId(String workshopId, String userId) {
		return DigestUtils.md5Hex(workshopId+userId);
	}
	
}
