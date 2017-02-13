package com.haoyu.tip.faq.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.follow.entity.Follow;

public class FaqQuestion extends BaseEntity{
	
	private static final long serialVersionUID = -8614911688448215580L;

	private Relation relation;
	
	private String content;
	
	private List<FaqAnswer> faqAnswers = Lists.newArrayList();
	
	private Follow follow;

	public List<FaqAnswer> getFaqAnswers() {
		return faqAnswers;
	}

	public void setFaqAnswers(List<FaqAnswer> faqAnswers) {
		this.faqAnswers = faqAnswers;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Follow getFollow() {
		return follow;
	}

	public void setFollow(Follow follow) {
		this.follow = follow;
	}
	
}
