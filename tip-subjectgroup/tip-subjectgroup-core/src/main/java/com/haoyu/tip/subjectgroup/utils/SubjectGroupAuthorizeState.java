package com.haoyu.tip.subjectgroup.utils;

public enum SubjectGroupAuthorizeState {

	APPLY("apply"), PASS("pass"), NOPASS("nopass"), NOPASSFOREVER("nopassForever"),INVITE("invite"),CANCEL("cancel");
	
	private String state;
	
	private SubjectGroupAuthorizeState(String state){
		this.state = state;
	}
	
	public String toString(){
		return state;
	}
}
