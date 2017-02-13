package com.haoyu.tip.plan.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.plan.entity.Plan;

import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface IPlanService {
	
	Response create(Plan obj);
	
	Response update(Plan obj);
	
	Response delete(String id);
	
	Plan get(String id);
	
	List<Plan> list(SearchParam searchParam, PageBounds pageBounds);

	Response createPlan(Plan plan);

	int getCount(Map<String, Object> param);

	Response updateByIdNotSelective(Plan plan);

	Response deletePlan(Plan plan);

	Response updatePlan(Plan plan);

	Plan viewPlan(Plan plan);

}
