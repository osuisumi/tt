package com.haoyu.tip.workshop.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.workshop.entity.Workshop;

public interface IWorkshopService {

	Response create(Workshop obj);
	
	Response update(Workshop obj);
	
	Response delete(String id);
	
	Workshop get(String id);
	
	List<Workshop> list(SearchParam searchParam, PageBounds pageBounds);
	
	List<Workshop> listByRole(SearchParam searchParam, PageBounds pageBounds);

	int getCount(Map<String, Object> paramMap);

	Response createWorkshop(Workshop workshop);

	Response updateWorkshop(Workshop workshop);

	Workshop viewWorkshop(Workshop workshop);

	Response deleteWorkshop(Workshop workshop);

}
