package com.haoyu.tip.resource.event;

import org.springframework.context.ApplicationEvent;

public class DeleteResourceEvent extends ApplicationEvent {

	private static final long serialVersionUID = 5029396434820165882L;

	public DeleteResourceEvent(Object source) {
		super(source);
	}

}
