package com.haoyu.tip.announcement.mobile.entity;

import java.io.Serializable;

public class MAnnouncement implements Serializable{

	private static final long serialVersionUID = -7658109916266589208L;

	private String id;
	
	private String title;
	
	private String content;
	
	private long createTime;
	
	private boolean hadView;

	public MAnnouncement() {
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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public boolean isHadView() {
		return hadView;
	}

	public void setHadView(boolean hadView) {
		this.hadView = hadView;
	}

}