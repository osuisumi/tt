package com.haoyu.tip.announcement.mobile.entity;

import java.io.Serializable;

public class MAnnouncementUser implements Serializable{

	private static final long serialVersionUID = -8717848395730873773L;
	
	private String id;

	public MAnnouncementUser() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
