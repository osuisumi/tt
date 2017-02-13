package com.haoyu.tip.plan.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.plan.entity.Plan;
import com.haoyu.tip.plan.entity.PlanAuthorize;

public interface IPlanAuthorizeService {

	Response create(PlanAuthorize obj);
	
	Response update(PlanAuthorize obj);
	
	Response delete(String id);
	
	PlanAuthorize get(String id);

	List<PlanAuthorize> list(SearchParam searchParam, PageBounds pageBounds);

	Response saveMember(Plan plan);

	Response createPlan(PlanAuthorize planAuthorize);

}
