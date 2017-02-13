package com.haoyu.tip.schedule.entity;


import com.haoyu.sip.core.entity.BaseEntity;

public class Schedule extends BaseEntity {
	private static final long serialVersionUID = -1696824127299367183L;
	private String id;

	private String title;

	private String summary;

	private String state;

	private String type;

	private String url;

	private ScheduleRelation scheduleRelation;

	public Schedule() {
		super();
	}

	public Schedule(String id) {
		super();
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ScheduleRelation getScheduleRelation() {
		return scheduleRelation;
	}

	public void setScheduleRelation(ScheduleRelation scheduleRelation) {
		this.scheduleRelation = scheduleRelation;
	}


}
