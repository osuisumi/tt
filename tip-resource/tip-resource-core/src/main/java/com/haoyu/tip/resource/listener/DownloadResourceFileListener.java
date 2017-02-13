package com.haoyu.tip.resource.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.event.DownloadFileEvent;
import com.haoyu.sip.file.utils.FileRelationType;
import com.haoyu.tip.resource.entity.ResourceRelation;
import com.haoyu.tip.resource.entity.Resources;
import com.haoyu.tip.resource.service.IResourceRelationService;
import com.haoyu.tip.resource.service.IResourceService;

@Component
public class DownloadResourceFileListener implements ApplicationListener<DownloadFileEvent>{

	@Resource
	private IResourceService resourceService;
	@Resource
	private IResourceRelationService resourceRelationService;
	
	@Override
	public void onApplicationEvent(DownloadFileEvent event) {
		FileRelation fileRelation = (FileRelation) event.getSource();
		if (FileRelationType.RESOURCES.toString().equals(fileRelation.getType())) {
			Resources resource = resourceService.get(fileRelation.getRelation().getId());
			ResourceRelation resourceRelation = resource.getResourceRelations().get(0);
			resourceRelation.setDownloadNum(1);
			resourceRelationService.update(resourceRelation);
		}
	}
}
