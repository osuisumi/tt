package com.haoyu.tip.schedule.event;

import org.springframework.context.ApplicationEvent;

public class CreateScheduleEvent extends ApplicationEvent{
	private static final long serialVersionUID = -7428633356073978777L;
	
	public CreateScheduleEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
}
