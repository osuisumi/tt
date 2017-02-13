package com.haoyu.tip.resource.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.tip.resource.dao.IResourceDao;
import com.haoyu.tip.resource.entity.Resources;

@Repository
public class ResourceDao extends MybatisDao implements IResourceDao{

	@Override
	public int updateByIds(Map<String, Object> param) {
		return update("updateByIds", param);
	}

	@Override
	public int deleteByIds(List<String> ids) {
		return super.update("deleteByIds", ids);
	}
	
	@Override
	public List<Resources> select(Map<String, Object> param, PageBounds pageBounds) {
		return super.selectList("select", param, pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}

}
