package com.haoyu.tip.workshop.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.workshop.entity.WorkshopRelation;

public interface IWorkshopRelationService {

	Response create(WorkshopRelation obj);
	
	Response update(WorkshopRelation obj);
	
	Response delete(String id);
	
	WorkshopRelation get(String id);
	
	List<WorkshopRelation> list(SearchParam searchParam, PageBounds pageBounds);

	Response updateMemberNum(WorkshopRelation workshopRelation);

	Response updateFollowNum(WorkshopRelation workshopRelation);

	Response updateResourceNum(WorkshopRelation workshopRelation);
	
	Response updatePlanNum(WorkshopRelation workshopRelation);

}
