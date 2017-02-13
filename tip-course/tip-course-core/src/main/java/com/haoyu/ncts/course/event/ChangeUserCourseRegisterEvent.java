package com.haoyu.ncts.course.event;

import org.springframework.context.ApplicationEvent;

public class ChangeUserCourseRegisterEvent extends ApplicationEvent {

	private static final long serialVersionUID = 8859547183360887856L;

	public ChangeUserCourseRegisterEvent(Object source) {
		super(source);
	}

}
