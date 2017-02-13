package com.haoyu.tip.announcement.event;

import org.springframework.context.ApplicationEvent;

public class DeleteAnnouncementEvent extends ApplicationEvent {

	private static final long serialVersionUID = 6730793672133024129L;

	public DeleteAnnouncementEvent(Object source) {
		super(source);
	}

}
