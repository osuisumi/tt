package com.haoyu.tip.resource.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.resource.entity.ResourceRelation;

public interface IResourceRelationService {

	Response create(ResourceRelation obj);

	Response update(ResourceRelation obj);

	Response delete(String id);

	ResourceRelation get(String id);

	List<ResourceRelation> list(SearchParam searchParam, PageBounds pageBounds);

	Response updateBrowseNum(ResourceRelation obj);
}
