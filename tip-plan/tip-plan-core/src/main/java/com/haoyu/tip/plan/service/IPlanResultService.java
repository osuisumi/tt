package com.haoyu.tip.plan.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.plan.entity.PlanResult;

public interface IPlanResultService {

	Response create(PlanResult obj);

	Response update(PlanResult obj);

	Response delete(String id);

	PlanResult get(String id);
	
	List<PlanResult> list(SearchParam searchParam, PageBounds pageBounds);

	Response createPlanResult(PlanResult planResult);

	PlanResult viewPlanResult(PlanResult planResult);

}
