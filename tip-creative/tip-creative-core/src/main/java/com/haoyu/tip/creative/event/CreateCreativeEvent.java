package com.haoyu.tip.creative.event;

import org.springframework.context.ApplicationEvent;

public class CreateCreativeEvent extends ApplicationEvent{

	private static final long serialVersionUID = -6954789391231952123L;

	public CreateCreativeEvent(Object source) {
		super(source);
	}

}
