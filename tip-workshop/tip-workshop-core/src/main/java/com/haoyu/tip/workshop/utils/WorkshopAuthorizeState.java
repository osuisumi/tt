package com.haoyu.tip.workshop.utils;

public enum WorkshopAuthorizeState {

	APPLY("apply"), PASS("pass"), NOPASS("nopass"), NOPASSFOREVER("nopassForever"),INVITE("invite"),CANCEL("cancel");
	
	private String role;
	
	private WorkshopAuthorizeState(String role){
		this.role = role;
	}
	
	public String toString(){
		return role;
	}
}
