package com.haoyu.tip.creative.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.creative.dao.ICreativeDao;
import com.haoyu.tip.creative.entity.Creative;

@Repository
public class CreativeDao extends MybatisDao implements ICreativeDao {

	@Override
	public Creative selectCreativeById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertCreative(Creative creative) {
		creative.setDefaultValue();
		return super.insert(creative);
	}

	@Override
	public int updateCreative(Creative creative) {
		creative.setUpdateValue();
		return super.update(creative);
	}

	@Override
	public int deleteCreativeByLogic(Creative creative) {
		creative.setUpdateValue();
		return super.deleteByLogic(creative);
	}

	@Override
	public int deleteCreativeByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<Creative> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	@Override
	public List<Creative> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}

	@Override
	public List<Creative> selectByOp(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("selectByOp", param,pageBounds);
	}

	@Override
	public Creative selectByResourceId(String resourceId) {
		return selectOne("selectByResourceId", resourceId);
	}

	@Override
	public int batchUpdateCreative(Map<String, Object> param) {
		return super.update("batchUpdate",param);
	}

	@Override
	public int batchDeleteCreative(Map<String, Object> param) {
		return super.update("batchDelete",param);
	}

	@Override
	public Map<String, Map<String, Integer>> getAdviceUserNum(Map<String, Object> param) {
		return super.selectMap("getAdviceUserNum", param,"creativeId");
	}

	@Override
	public int getResourceCount(Map<String, Object> param) {
		return selectOne("getResourceCount", param);
	}

	@Override
	public int getResourceCreatorCount(Map<String, Object> param) {
		return selectOne("getResourceCreatorCount", param);
	}
	
}
