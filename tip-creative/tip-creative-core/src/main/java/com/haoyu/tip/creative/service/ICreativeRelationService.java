package com.haoyu.tip.creative.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.creative.entity.CreativeRelation;

public interface ICreativeRelationService {
	
	Response createCreativeRelation(CreativeRelation creativeRelation);
	
	Response updateCreativeRelation(CreativeRelation creativeRelation);
	
	Response deleteCreativeRelation(CreativeRelation creativeRelation);
	
	CreativeRelation findCreativeRelationById(String id);
	
	List<CreativeRelation> findCreativeRelations(CreativeRelation creativeRelation,PageBounds pageBounds);
	
	List<CreativeRelation> findCreativeRelations(Map<String,Object> parameter,PageBounds pageBounds);

	Response updateBrowseNum(CreativeRelation creativeRelation);
}
