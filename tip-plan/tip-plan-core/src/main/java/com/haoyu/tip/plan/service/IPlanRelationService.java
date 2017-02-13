package com.haoyu.tip.plan.service;

import com.haoyu.tip.plan.entity.PlanRelation;
import com.haoyu.sip.core.service.Response;

public interface IPlanRelationService {
	
	Response create(PlanRelation obj);
	
	Response update(PlanRelation obj);
	
	Response delete(String id);
	
	PlanRelation get(String id);

}
