package com.haoyu.tip.plan.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class UpdatePlanAuthorizeEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1462667303452452813L;

	public UpdatePlanAuthorizeEvent(List<String> ids) {
		super(ids);
	}

}
