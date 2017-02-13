package com.haoyu.tip.workshop.utils;

public enum WorkshopAuthorizeRole {

	MASTER("master"),MEMBER("member"),ASSIST("assist");
	
	private String role;
	
	private WorkshopAuthorizeRole(String role){
		this.role = role;
	}
	
	public String toString(){
		return role;
	}
}
