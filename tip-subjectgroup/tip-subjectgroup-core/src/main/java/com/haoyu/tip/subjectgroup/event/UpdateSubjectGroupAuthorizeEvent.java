package com.haoyu.tip.subjectgroup.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class UpdateSubjectGroupAuthorizeEvent extends ApplicationEvent {

	private static final long serialVersionUID = -1189240458682083230L;

	public UpdateSubjectGroupAuthorizeEvent(List<String> ids) {
		super(ids);
	}

}
