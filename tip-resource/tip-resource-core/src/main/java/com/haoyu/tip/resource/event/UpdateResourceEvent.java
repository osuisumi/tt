package com.haoyu.tip.resource.event;

import org.springframework.context.ApplicationEvent;

public class UpdateResourceEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	public UpdateResourceEvent(Object source) {
		super(source);
	}

}
