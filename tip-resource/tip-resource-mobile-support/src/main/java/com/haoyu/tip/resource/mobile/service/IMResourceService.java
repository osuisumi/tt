package com.haoyu.tip.resource.mobile.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.resource.entity.Resources;

public interface IMResourceService {

	Response listResource(Resources resource, PageBounds pageBounds);

	Response createResource(Resources resource);

}
