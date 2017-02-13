package com.haoyu.tip.plan.utils;

public enum PlanAuthorizeState {

	APPLY("apply"), PASS("pass"), NOPASS("nopass"), NOPASSFOREVER("nopassForever"),INVITE("invite"),CANCEL("cancel");
	
	private String state;
	
	private PlanAuthorizeState(String state){
		this.state = state;
	}
	
	public String toString(){
		return state;
	}
}
