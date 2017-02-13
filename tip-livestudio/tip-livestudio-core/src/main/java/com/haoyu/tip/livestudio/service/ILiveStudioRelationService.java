package com.haoyu.tip.livestudio.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.livestudio.entity.LiveStudio;
import com.haoyu.tip.livestudio.entity.LiveStudioRelation;

public interface ILiveStudioRelationService {
	
	Response create(LiveStudioRelation liveStudioRelation);

	Response update(LiveStudioRelation liveStudioRelation);

	Response deleteByPhysics(String id);

	Response deleteByLogic(String id);

	LiveStudio get(String id);

	List<LiveStudioRelation> list(LiveStudioRelation liveStudio,PageBounds pageBounds);

}
