package com.haoyu.tip.project.mobile.entity;

import java.io.Serializable;

public class MProjectStat implements Serializable{
	
	private static final long serialVersionUID = -2417632113406175349L;

	private int registerHeadcount;
	
	private int participateHeadcount;
	
	private int passHeadcount;

	public int getRegisterHeadcount() {
		return registerHeadcount;
	}

	public void setRegisterHeadcount(int registerHeadcount) {
		this.registerHeadcount = registerHeadcount;
	}

	public int getParticipateHeadcount() {
		return participateHeadcount;
	}

	public void setParticipateHeadcount(int participateHeadcount) {
		this.participateHeadcount = participateHeadcount;
	}

	public int getPassHeadcount() {
		return passHeadcount;
	}

	public void setPassHeadcount(int passHeadcount) {
		this.passHeadcount = passHeadcount;
	}

}
