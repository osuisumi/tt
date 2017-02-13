package com.haoyu.tip.creative.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;
import com.haoyu.tip.creative.entity.CreativeUser;

public interface ICreativeUserService {
	
	Response createCreativeUser(CreativeUser creativeUser);
	
	Response updateCreativeUser(CreativeUser creativeUser);
	
	Response deleteCreativeUser(CreativeUser creativeUser);
	
	CreativeUser findCreativeUserById(String id);
	
	List<CreativeUser> findCreativeUsers(CreativeUser creativeUser,PageBounds pageBounds);
	
	List<CreativeUser> findCreativeUsers(Map<String,Object> parameter,PageBounds pageBounds);

	Map<String, CreativeUser> getCreativeUserMap(Map<String, Object> param);
}
