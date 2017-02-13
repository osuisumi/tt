package com.haoyu.tip.faq.event;

import org.springframework.context.ApplicationEvent;

public class CreateFaqAnswerEvent extends ApplicationEvent {
	private static final long serialVersionUID = 3021103529913072693L;

	public CreateFaqAnswerEvent(Object source) {
		super(source);
	}

}
