package com.haoyu.tip.faq.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.follow.mobile.entity.MFollow;
import com.haoyu.sip.user.mobile.entity.MUser;

public class MFaqQuestion implements Serializable{

	private static final long serialVersionUID = -6028028770578372858L;

	private String id;
	
	private String content;
		
	private MUser creator ;
	
	private long createTime;
	
	private int faqAnswerCount;
	
	private MFollow follow;
	
	private int followNum;
	
	public MFaqQuestion() {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MFollow getFollow() {
		return follow;
	}

	public void setFollow(MFollow follow) {
		this.follow = follow;
	}

	public int getFaqAnswerCount() {
		return faqAnswerCount;
	}

	public void setFaqAnswerCount(int faqAnswerCount) {
		this.faqAnswerCount = faqAnswerCount;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public MUser getCreator() {
		return creator;
	}

	public void setCreator(MUser creator) {
		this.creator = creator;
	}
	
}