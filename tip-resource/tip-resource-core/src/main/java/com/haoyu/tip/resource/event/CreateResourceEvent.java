package com.haoyu.tip.resource.event;

import org.springframework.context.ApplicationEvent;

public class CreateResourceEvent extends ApplicationEvent {

	private static final long serialVersionUID = 8173812567184891132L;

	public CreateResourceEvent(Object source) {
		super(source);
	}

}
