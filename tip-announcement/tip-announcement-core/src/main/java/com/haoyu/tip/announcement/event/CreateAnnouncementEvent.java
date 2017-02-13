package com.haoyu.tip.announcement.event;

import org.springframework.context.ApplicationEvent;

public class CreateAnnouncementEvent extends ApplicationEvent{

	private static final long serialVersionUID = 3594640428577707782L;

	public CreateAnnouncementEvent(Object source) {
		super(source);
	}
	

}
