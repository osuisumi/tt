package com.haoyu.tip.resource.service;

import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.resource.entity.ResourceExtend;

public interface IResourceExtendService {

	Response createResourceExtend(ResourceExtend resourceExtend);
	
	Response updateResourceExtend(ResourceExtend resourceExtend);

}
