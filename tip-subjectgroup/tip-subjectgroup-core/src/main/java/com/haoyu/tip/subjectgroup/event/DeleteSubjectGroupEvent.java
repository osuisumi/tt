package com.haoyu.tip.subjectgroup.event;

import org.springframework.context.ApplicationEvent;

public class DeleteSubjectGroupEvent extends ApplicationEvent {

	private static final long serialVersionUID = 445468678532724139L;

	public DeleteSubjectGroupEvent(Object source) {
		super(source);
	}

}
