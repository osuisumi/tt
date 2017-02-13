package com.haoyu.tip.subjectgroup.event;

import org.springframework.context.ApplicationEvent;

public class CreateSubjectGroupEvent extends ApplicationEvent{

	private static final long serialVersionUID = 2157481102230811922L;

	public CreateSubjectGroupEvent(Object source) {
		super(source);
	}

}
