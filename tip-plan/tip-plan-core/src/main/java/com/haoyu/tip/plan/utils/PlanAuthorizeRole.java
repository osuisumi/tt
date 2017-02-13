package com.haoyu.tip.plan.utils;

public enum PlanAuthorizeRole {

	MASTER("master"), MEMBER("member");
	
	private String role;
	
	private PlanAuthorizeRole(String role){
		this.role = role;
	}
	
	public String toString(){
		return role;
	}
}
