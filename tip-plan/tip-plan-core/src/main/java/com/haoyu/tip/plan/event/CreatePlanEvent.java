package com.haoyu.tip.plan.event;

import org.springframework.context.ApplicationEvent;

public class CreatePlanEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;

	public CreatePlanEvent(Object source) {
		super(source);
	}

}
