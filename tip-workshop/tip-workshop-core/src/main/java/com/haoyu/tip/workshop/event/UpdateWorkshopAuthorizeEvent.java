package com.haoyu.tip.workshop.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class UpdateWorkshopAuthorizeEvent extends ApplicationEvent{

	private static final long serialVersionUID = -7612953454851861315L;

	public UpdateWorkshopAuthorizeEvent(List<String> ids) {
		super(ids);
	}

}
