package com.haoyu.tip.creative.event;

import org.springframework.context.ApplicationEvent;

public class UpdateCreativeEvent extends ApplicationEvent{

	private static final long serialVersionUID = -4587826408309557468L;

	public UpdateCreativeEvent(Object source) {
		super(source);
	}

}
