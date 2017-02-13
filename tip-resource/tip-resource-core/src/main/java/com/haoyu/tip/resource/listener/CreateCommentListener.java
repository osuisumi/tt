package com.haoyu.tip.resource.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;

import com.haoyu.sip.comment.entity.Comment;
import com.haoyu.sip.comment.event.CreateCommentEvent;
import com.haoyu.sip.comment.utils.RelationType;
import com.haoyu.tip.resource.entity.ResourceRelation;
import com.haoyu.tip.resource.entity.Resources;
import com.haoyu.tip.resource.service.IResourceRelationService;
import com.haoyu.tip.resource.service.IResourceService;

public class CreateCommentListener implements ApplicationListener<CreateCommentEvent>{

	@Resource
	private IResourceService resourceService;
	@Resource
	private IResourceRelationService resourceRelationService;
	
	@Override
	public void onApplicationEvent(CreateCommentEvent event) {
		Comment comment = (Comment) event.getSource();
		if (RelationType.RESOURCE.equals(comment.getRelation().getType())) {
			Resources resource = resourceService.get(comment.getRelation().getId());
			ResourceRelation resourceRelation = resource.getResourceRelations().get(0);
			resourceRelation.setReplyNum(1);
			resourceRelationService.update(resourceRelation);
		}
	}

}
