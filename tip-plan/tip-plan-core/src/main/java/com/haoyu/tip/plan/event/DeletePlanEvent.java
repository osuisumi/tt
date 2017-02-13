package com.haoyu.tip.plan.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.tip.plan.entity.Plan;

public class DeletePlanEvent extends ApplicationEvent {

	private static final long serialVersionUID = 5986211960265659196L;

	public DeletePlanEvent(Plan plan) {
		super(plan);
	}

}
