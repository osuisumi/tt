package com.haoyu.tip.creative.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.creative.dao.ICreativeRelationDao;
import com.haoyu.tip.creative.entity.CreativeRelation;

@Repository
public class CreativeRelationDao extends MybatisDao implements ICreativeRelationDao {

	@Override
	public CreativeRelation selectCreativeRelationById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertCreativeRelation(CreativeRelation creativeRelation) {
		creativeRelation.setDefaultValue();
		return super.insert(creativeRelation);
	}

	@Override
	public int updateCreativeRelation(CreativeRelation creativeRelation) {
		creativeRelation.setUpdateValue();
		return super.update(creativeRelation);
	}

	@Override
	public int deleteCreativeRelationByLogic(CreativeRelation creativeRelation) {
		creativeRelation.setUpdateValue();
		return super.deleteByLogic(creativeRelation);
	}

	@Override
	public int deleteCreativeRelationByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<CreativeRelation> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	@Override
	public List<CreativeRelation> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	
}
