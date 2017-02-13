package com.haoyu.tip.livestudio.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.tip.livestudio.entity.LiveStudio;

public interface ILiveStudioService{
	Response create(LiveStudio liveStudio);

	Response update(LiveStudio liveStudio);

	Response deleteByPhysics(String id);

	Response deleteByLogic(String id);

	LiveStudio get(String id);

	List<LiveStudio> list(SearchParam searchParam,PageBounds pageBounds);
	

}
