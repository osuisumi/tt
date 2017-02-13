package com.haoyu.tip.train.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.core.entity.TimePeriod;

public class MTrain implements Serializable{
	
	private static final long serialVersionUID = 1997737183350347266L;

	private String id;
	
	private String name;
	
	private TimePeriod trainingTime;

	public TimePeriod getTrainingTime() {
		return trainingTime;
	}

	public void setTrainingTime(TimePeriod trainingTime) {
		this.trainingTime = trainingTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
