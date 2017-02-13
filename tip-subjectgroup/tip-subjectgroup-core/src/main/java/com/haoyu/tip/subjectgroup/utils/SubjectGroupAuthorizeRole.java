package com.haoyu.tip.subjectgroup.utils;

public enum SubjectGroupAuthorizeRole {

	MASTER("master"), MEMBER("member"), BACKBONE("backbone");
	
	private String role;
	
	private SubjectGroupAuthorizeRole(String role){
		this.role = role;
	}
	
	public String toString(){
		return role;
	}
}
