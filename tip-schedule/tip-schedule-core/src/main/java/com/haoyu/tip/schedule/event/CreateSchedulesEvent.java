package com.haoyu.tip.schedule.event;

import org.springframework.context.ApplicationEvent;

public class CreateSchedulesEvent extends ApplicationEvent{
	private static final long serialVersionUID = -4253582594784230462L;
	public CreateSchedulesEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
}
