package com.haoyu.tip.creative.event;

import org.springframework.context.ApplicationEvent;

public class BatchDeleteCreativeEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	
	public BatchDeleteCreativeEvent(Object source) {
		super(source);
	}

}
