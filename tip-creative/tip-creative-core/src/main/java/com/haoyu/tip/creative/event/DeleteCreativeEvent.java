package com.haoyu.tip.creative.event;

import org.springframework.context.ApplicationEvent;

public class DeleteCreativeEvent extends ApplicationEvent{

	private static final long serialVersionUID = -7574898481096841427L;

	public DeleteCreativeEvent(Object source) {
		super(source);
	}

}
