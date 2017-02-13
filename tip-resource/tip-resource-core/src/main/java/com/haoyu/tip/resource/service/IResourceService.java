package com.haoyu.tip.resource.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.resource.entity.Resources;

public interface IResourceService {

	Response create(Resources obj);
	
	Response update(Resources obj);
	
	Response delete(String id);
	
	Resources get(String id);
		
	List<Resources> list(SearchParam searchParam, PageBounds pageBounds);
	
	List<Resources> list(Map<String, Object> param, PageBounds pageBounds);

	List<Resources> list(Map<String, Object> param, PageBounds pageBounds,boolean getFile);

	Response createResource(Resources resources);

	Response updateResource(Resources resource);

	Resources viewResource(Resources resource);
	
	Response updateByIds(Resources resources);
	
	Response deleteByIds(String ids);
	
}
