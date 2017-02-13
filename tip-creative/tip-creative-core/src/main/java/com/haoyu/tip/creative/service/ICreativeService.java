package com.haoyu.tip.creative.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.creative.entity.Creative;

public interface ICreativeService {
	
	Response createCreative(Creative creative);
	
	Response updateCreative(Creative creative);
	
	Response deleteCreative(Creative creative);
	
	Creative findCreativeById(String id);
		
	List<Creative> findCreatives(Creative creative,PageBounds pageBounds);
	
	List<Creative> findCreatives(Map<String,Object> parameter,PageBounds pageBounds);
	
	int getCount(Map<String, Object> param);

	List<Creative> findCreativeByOp(Map<String, Object> parameters, PageBounds pageBoundsForOP);

	Creative findCreativeByResourceId(String resourceId);

	Response batchUpdateCreative(Creative creative);

	Response batchDeleteCreative(Creative creative);

	Map<String, Integer> getAdviceUserNum(Map<String, Object> param);

	Response updateCreativeAgreement(Creative creative);

	int getResourceCount(Map<String, Object> param);
	
	int getResourceCreatorCount(Map<String, Object> param);
}
