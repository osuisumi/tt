package com.haoyu.tip.workshop.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.workshop.entity.Workshop;
import com.haoyu.tip.workshop.entity.WorkshopAuthorize;

public interface IWorkshopAuthorizeService {

	Response create(WorkshopAuthorize obj);
	
	Response update(WorkshopAuthorize obj);
	
	Response delete(String id);
	
	WorkshopAuthorize get(String id);
	
	List<WorkshopAuthorize> list(SearchParam searchParam, PageBounds pageBounds);

	Response updateByIds(List<String> ids, WorkshopAuthorize workshopAuthorize);

	Response deleteByPhysics(WorkshopAuthorize workshopAuthorize);

	Response updateWorkshopAuthorize(WorkshopAuthorize workshopAuthorize);

	Response deleteWorkshopAuthorize(WorkshopAuthorize workshopAuthorize);

	Response createWorkshopAuthorize(Workshop workshop);

	Response deleteWorkshopAuthorizeBatch(WorkshopAuthorize workshopAuthorize);

	Response createWorkshopAuthorize(WorkshopAuthorize workshopAuthorize);

}
