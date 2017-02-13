package com.haoyu.tip.plan.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.haoyu.sip.file.entity.FileRelation;
import com.haoyu.sip.file.event.DownloadFileEvent;
import com.haoyu.sip.file.utils.FileRelationType;
import com.haoyu.tip.plan.entity.PlanResult;
import com.haoyu.tip.plan.service.IPlanResultService;

@Component
public class DownloadPlanResultFileListener implements ApplicationListener<DownloadFileEvent>{

	@Resource
	private IPlanResultService planResultService;
	
	@Override
	public void onApplicationEvent(DownloadFileEvent event) {
		FileRelation fileRelation = (FileRelation) event.getSource();
		if (FileRelationType.PLAN_RESULT.toString().equals(fileRelation.getType())) {
			PlanResult planResult = new PlanResult();
			planResult.setId(fileRelation.getRelation().getId());
			planResult.setDownloadNum(1);
			planResultService.update(planResult);
		}
	}
}
