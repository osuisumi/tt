package com.haoyu.tip.creative.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.creative.entity.Creative;

public interface ICreativeDao {

	Creative selectCreativeById(String id);

	int insertCreative(Creative creative);

	int updateCreative(Creative creative);

	int deleteCreativeByLogic(Creative creative);

	int deleteCreativeByPhysics(String id);

	List<Creative> findAll(Map<String, Object> parameter);

	List<Creative> findAll(Map<String, Object> parameter, PageBounds pageBounds);

	int getCount(Map<String, Object> param);

	List<Creative> selectByOp(Map<String, Object> param, PageBounds pageBounds);

	Creative selectByResourceId(String resourceId);

	int batchUpdateCreative(Map<String, Object> param);

	int batchDeleteCreative(Map<String, Object> param);

	Map<String, Map<String, Integer>> getAdviceUserNum(Map<String, Object> param);

	int getResourceCount(Map<String, Object> param);

	int getResourceCreatorCount(Map<String, Object> param);

}