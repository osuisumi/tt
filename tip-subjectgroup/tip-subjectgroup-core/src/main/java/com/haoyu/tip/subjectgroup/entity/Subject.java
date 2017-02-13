package com.haoyu.tip.subjectgroup.entity;

import com.haoyu.base.entity.BaseEntity;

public class Subject extends BaseEntity{
	
	private static final long serialVersionUID = -3733362462267705660L;

	private String id;
	
	private String name;
	
	private String imageUrl;
	
	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

}
