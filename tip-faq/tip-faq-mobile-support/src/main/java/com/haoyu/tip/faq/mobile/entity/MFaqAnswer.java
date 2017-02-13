package com.haoyu.tip.faq.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.user.mobile.entity.MUser;

public class MFaqAnswer implements Serializable{

	private static final long serialVersionUID = 4818515412352056691L;

	private String id;
	
	private String content;
	
	private String questionId;
	
	private MUser creator;
	
	private long createTime;

	public MFaqAnswer() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MUser getCreator() {
		return creator;
	}

	public void setCreator(MUser creator) {
		this.creator = creator;
	}

}