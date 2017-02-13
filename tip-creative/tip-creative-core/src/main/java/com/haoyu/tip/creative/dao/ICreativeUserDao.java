package com.haoyu.tip.creative.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.creative.entity.CreativeUser;

public interface ICreativeUserDao {

	CreativeUser selectCreativeUserById(String id);

	int insertCreativeUser(CreativeUser creativeUser);

	int updateCreativeUser(CreativeUser creativeUser);

	int deleteCreativeUserByLogic(CreativeUser creativeUser);

	int deleteCreativeUserByPhysics(String id);

	List<CreativeUser> findAll(Map<String, Object> parameter);

	List<CreativeUser> findAll(Map<String, Object> parameter, PageBounds pageBounds);
	
	Map<String, CreativeUser> findAllForMap(Map<String, Object> parameter);
}