package com.haoyu.tip.course.mobile.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class MSection implements Serializable{
	
	private static final long serialVersionUID = -207160690155214306L;

	private String id;
	
	private String title;
	
	private String completeState;
	
	private List<MSection> childMSections = Lists.newArrayList();

	public List<MSection> getChildMSections() {
		return childMSections;
	}

	public void setChildMSections(List<MSection> childMSections) {
		this.childMSections = childMSections;
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

	public String getCompleteState() {
		return completeState;
	}

	public void setCompleteState(String completeState) {
		this.completeState = completeState;
	}

}
