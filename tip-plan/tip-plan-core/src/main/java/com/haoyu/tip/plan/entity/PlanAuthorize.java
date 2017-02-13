package com.haoyu.tip.plan.entity;

import java.math.BigDecimal;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;

public class PlanAuthorize extends BaseEntity {

	private static final long serialVersionUID = 8687753191146163090L;

	private String planId;

	private User user;

	private String role;

	private String state;
	
	private BigDecimal score;

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
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

	public static String getId(String planId, String userId) {
		return DigestUtils.md5Hex(planId + userId);
	}

}
