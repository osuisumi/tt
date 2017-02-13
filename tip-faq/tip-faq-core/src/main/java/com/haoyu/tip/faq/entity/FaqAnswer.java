package com.haoyu.tip.faq.entity;

import com.haoyu.base.entity.BaseEntity;

public class FaqAnswer extends BaseEntity{
	
	private static final long serialVersionUID = 1727427983640154394L;

	private String questionId;
	
	private String content;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
