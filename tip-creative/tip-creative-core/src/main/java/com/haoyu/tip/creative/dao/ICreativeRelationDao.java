package com.haoyu.tip.creative.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.tip.creative.entity.CreativeRelation;

public interface ICreativeRelationDao {

	CreativeRelation selectCreativeRelationById(String id);

	int insertCreativeRelation(CreativeRelation creativeRelation);

	int updateCreativeRelation(CreativeRelation creativeRelation);

	int deleteCreativeRelationByLogic(CreativeRelation creativeRelation);

	int deleteCreativeRelationByPhysics(String id);

	List<CreativeRelation> findAll(Map<String, Object> parameter);

	List<CreativeRelation> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}